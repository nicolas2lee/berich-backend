package tao.identitymanager.exposition.jwt

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory


class JwtTokenProviderTest {
    val LOG = LoggerFactory.getLogger(this.javaClass)

    val jwtProvider = JwtTokenProvider()

    @Test
    fun should_generate_jwt_token() {
        val audience = "aud"
        val subject = "sub"
        val expiration= 1000L

        val token = jwtProvider.createToken(audience, subject, expiration)
        LOG.info(token)
        val results = token.split(".")
        results.forEach{assertNotNull(it)}
        assertThat(results).hasSize(3)
    }

}