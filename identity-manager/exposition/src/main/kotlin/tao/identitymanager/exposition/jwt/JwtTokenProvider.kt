package tao.identitymanager.exposition.jwt

import com.nimbusds.jose.*
import com.nimbusds.jose.crypto.MACSigner
import com.nimbusds.jose.crypto.MACVerifier
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.text.ParseException
import java.util.*
import java.util.stream.Collectors


@Component
class JwtTokenProvider {
    @Value("\${jwt.secret}")
    private val secret: String = "testsecretQeThWmYq3t6w9z@a&F)J@N"

    companion object{
        const val AUTHENTICATED_KEY: String = "authenticated"
        const val AUTHORITIES_KEY: String = "authorities"
    }

    fun createToken(aud: String, subject: String, expirationMillis: Long,  authorities: Array<String>): String {
        val header = JWSHeader(JWSAlgorithm.HS256)
        val claimsSet = JWTClaimsSet.Builder()
                .subject(subject)
                .issuer("tao.berich")
                .expirationTime(Date(System.currentTimeMillis() + expirationMillis))
                .audience(aud)
                .claim(AUTHENTICATED_KEY, true)
                .claim(AUTHORITIES_KEY, authorities)
                .build()
        val signedJWT = SignedJWT(header, claimsSet)

        val signer: JWSSigner = MACSigner(secret.toByteArray())
        signedJWT.sign(signer)
        return signedJWT.serialize()
    }

    @Throws(InvalidJwtException::class)
    fun parseJwtWithSignatureVerification(jwt: String) : JwtUserInfo {
        val signedJWT: SignedJWT
        try {
            signedJWT = SignedJWT.parse(jwt);
            verifySignature(signedJWT)
        } catch (e1: InvalidJwtSignatureException){
            throw InvalidJwtException("Invalid jwt signature", e1)
        } catch (e2: JOSEException) {
            throw InvalidJwtException("Fail to verify jwt", e2)
        } catch (e3: ParseException){
            throw InvalidJwtException("Fail to parse jwt", e3)
        }

        val payload = signedJWT.jwtClaimsSet
        val subject = payload.subject
        val authorities = (payload.getClaim(AUTHORITIES_KEY) as Collection<String> ).stream()
                .map { SimpleGrantedAuthority(it) }
                .collect(Collectors.toList())
        return JwtUserInfo(subject, authorities)
    }

    @Throws(InvalidJwtSignatureException::class, JOSEException::class)
    private fun verifySignature(signedJWT: SignedJWT) {
        val verifier: JWSVerifier = MACVerifier(secret.toByteArray())
        val verificationResult = signedJWT.verify(verifier);
        if (!verificationResult) throw InvalidJwtSignatureException("Signature of jwt is not valid")
    }


    class InvalidJwtSignatureException(override val message: String) : InvalidJwtException(message, null)

    open class InvalidJwtException(override val message: String, val t: Throwable? ) : AccessDeniedException(message, t)

    data class JwtUserInfo(val username: String, val authorities: Collection<GrantedAuthority>)
}
