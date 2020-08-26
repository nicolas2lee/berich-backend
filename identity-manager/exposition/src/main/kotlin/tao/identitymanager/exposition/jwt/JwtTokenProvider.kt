package tao.identitymanager.exposition.jwt

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.JWSSigner
import com.nimbusds.jose.crypto.MACSigner
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtTokenProvider {
/*    @Value("\${jwt.secret}")
    private val secret: String? = null

    @Value("\${app.jwtExpirationInMs}")
    private val jwtExpirationInMs = 0*/
    companion object{
        const val AUTHENTICATED_KEY: String = "authenticated"
    }

    fun createToken(aud: String, subject: String, expirationMillis: Long): String {
        val header = JWSHeader(JWSAlgorithm.HS256)
        val claimsSet = JWTClaimsSet.Builder()
                .subject(subject)
                .issuer("tao.berich")
                .expirationTime(Date(System.currentTimeMillis() + expirationMillis))
                .audience(aud)
                .claim(AUTHENTICATED_KEY, true)
                .build()
        val signedJWT = SignedJWT(header, claimsSet)
        val secret = "y/B?E(H+MbQeThWmYq3t6w9z@a&F)J@N"
        val sharedSecret: ByteArray = secret!!.toByteArray()
        val signer: JWSSigner = MACSigner(sharedSecret)
        signedJWT.sign(signer)
        return signedJWT.serialize()
    }
}
