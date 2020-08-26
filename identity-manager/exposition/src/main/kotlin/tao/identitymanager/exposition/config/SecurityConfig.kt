package tao.identitymanager.exposition.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.WebFilterExchange
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import reactor.core.publisher.Mono
import tao.identitymanager.exposition.jwt.JwtTokenProvider
import java.net.URI


@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity, jwtTokenProvider: JwtTokenProvider): SecurityWebFilterChain {
        return http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/", "/home").permitAll()
                .anyExchange().authenticated()
                .and().formLogin().authenticationSuccessHandler(
                        authSuccessHandler(jwtTokenProvider))
                .and().logout()
                .and().securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .build();
    }

    @Bean
    fun authSuccessHandler(jwtTokenProvider: JwtTokenProvider): AuthSuccessHandler {
        return AuthSuccessHandler(jwtTokenProvider)
    }

    class AuthSuccessHandler(val jwtTokenProvider: JwtTokenProvider): ServerAuthenticationSuccessHandler {
        companion object{
            const val AUTHORIZATION_HEADER = "Authorization"
            val LOG = LoggerFactory.getLogger(AuthSuccessHandler.javaClass)
        }

        override fun onAuthenticationSuccess(webFilterExchange: WebFilterExchange, authentication: Authentication): Mono<Void> {
            val userDetails = authentication.principal as UserDetails
            val jwt = jwtTokenProvider.createToken("aud", userDetails.username, 10000)
            LOG.debug("Generated jwt is: [$jwt]")
            val response = webFilterExchange.exchange.response
            response.cookies.remove("SESSION")
            response.headers.add("Set-Cookie", "${AUTHORIZATION_HEADER}=Bearer ${jwt}; HttpOnly")
            response.statusCode = HttpStatus.FOUND
            response.headers.location = URI.create("/")
            return Mono.empty<Void>().then()
        }
    }
}