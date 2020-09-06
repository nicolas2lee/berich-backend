package tao.identitymanager.exposition.jwt

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.security.core.authority.SimpleGrantedAuthority


class JwtTokenProviderTest {
    val LOG = LoggerFactory.getLogger(this.javaClass)

    val jwtProvider = JwtTokenProvider()

    @Test
    internal fun should_generate_jwt_token() {
        val audience = "aud"
        val subject = "sub"
        val expiration= 1000L
        val authorities= arrayOf("USER", "ADMIN")
                //listOf<SimpleGrantedAuthority>(SimpleGrantedAuthority("USER"), SimpleGrantedAuthority("ADMIN"))

        val token = jwtProvider.createToken(audience, subject, expiration, authorities )
        LOG.info(token)
        val results = token.split(".")
        results.forEach{assertNotNull(it)}
        assertThat(results).hasSize(3)
    }

    @Test
    internal fun should_throw_invalid_jwt_exception_when_jwt_signature_not_valid_with_shared_secret() {
        val header = buildHeader()
        val payload = buildPayload()
        val signature = "iamatest"
        val jwt = "$header.$payload.$signature"

        Assertions.assertThrows(JwtTokenProvider.InvalidJwtException::class.java,
                 {jwtProvider.parseJwtWithSignatureVerification(jwt)}
        )
    }

    @Test
    internal fun should_return_valid_jwt_user_info() {
        val header = buildHeader()
        val payload = buildPayload()
        val signature = buildSignature()
        val jwt = "$header.$payload.$signature"

        val jwtUserInfo = jwtProvider.parseJwtWithSignatureVerification(jwt)

        assertThat(jwtUserInfo.username).isEqualTo("admin")
        assertThat(jwtUserInfo.authorities).isNotEmpty
        assertThat(jwtUserInfo.authorities).containsOnly(SimpleGrantedAuthority("USER"), SimpleGrantedAuthority("ADMIN"))
    }

    /**
     * {
    "alg": "HS256"
    }
     */
    private fun buildHeader(): String {
        return "eyJhbGciOiJIUzI1NiJ9"
    }

    /**
     * {
    "sub": "admin",
    "aud": "aud",
    "authenticated": true,
    "iss": "tao.berich",
    "exp": 1598992781,
    "authorities": [
    "USER", "ADMIN"
    ]
    }
     */
    private fun buildPayload(): String {
        return "eyJzdWIiOiJhZG1pbiIsImF1ZCI6ImF1ZCIsImF1dGhlbnRpY2F0ZWQiOnRydWUsImlzcyI6InRhby5iZXJpY2giLCJleHAiOjE1OTkxNjk3MzcsImF1dGhvcml0aWVzIjpbIkFETUlOIiwiVVNFUiJdfQ"
    }

    private fun buildSignature(): String {
        return "oqLsu-5xe70FzsQidJY9CSsI43eNTYUkHgQ9A646Xfw"
    }
}
