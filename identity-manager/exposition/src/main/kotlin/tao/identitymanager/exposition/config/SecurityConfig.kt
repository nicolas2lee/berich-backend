package tao.identitymanager.exposition.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.WebFilterExchange
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import tao.identitymanager.exposition.jwt.JwtBeforeAuthorizationWebFiler
import tao.identitymanager.exposition.jwt.JwtTokenProvider
import java.net.URI


@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity, jwtTokenProvider: JwtTokenProvider): SecurityWebFilterChain {
        return http
                .csrf().disable()
                //.cors().configurationSource(CorsConfig()).and()
                .authorizeExchange()
                .pathMatchers( "/login").permitAll()
                .anyExchange().authenticated()
                .and()
                .addFilterBefore(JwtBeforeAuthorizationWebFiler(jwtTokenProvider), SecurityWebFiltersOrder.AUTHORIZATION)
                .formLogin().authenticationSuccessHandler(AuthSuccessHandler(jwtTokenProvider))
                .and()
                .httpBasic().disable()
                .logout().disable()
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .build();
    }

    class AuthSuccessHandler(val jwtTokenProvider: JwtTokenProvider): ServerAuthenticationSuccessHandler {
        companion object{
            const val JWT_TOKEN = "token"
            val LOG = LoggerFactory.getLogger(AuthSuccessHandler.javaClass)
        }

        override fun onAuthenticationSuccess(webFilterExchange: WebFilterExchange, authentication: Authentication): Mono<Void> {
            val userDetails = authentication.principal as UserDetails
            val jwt = jwtTokenProvider.createToken("aud", userDetails.username, 10000, userDetails.authorities.map { it.authority }.toTypedArray())
            LOG.debug("Generated jwt is: [$jwt]")
            val response = webFilterExchange.exchange.response
            response.cookies.remove("SESSION")
            response.headers.add("Set-Cookie", "${JWT_TOKEN}=${jwt}; HttpOnly")
            response.statusCode = HttpStatus.FOUND
            response.headers.location = URI.create("/me")
            return Mono.empty<Void>().then()
        }
    }

    class CorsConfig : org.springframework.web.cors.reactive.CorsConfigurationSource{
        override fun getCorsConfiguration(p0: ServerWebExchange): CorsConfiguration? {
            val corsConfig = CorsConfiguration()
            corsConfig.applyPermitDefaultValues()
            corsConfig.allowedOrigins = listOf("http://localhost:3000", "http://localhost:8080")
            corsConfig.allowCredentials = true
            return corsConfig
        }

    }
}