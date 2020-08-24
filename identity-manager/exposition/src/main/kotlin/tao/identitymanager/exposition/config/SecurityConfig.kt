package tao.identitymanager.exposition.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.WebFilterExchange
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import reactor.core.publisher.Mono
import tao.identitymanager.exposition.jwt.JwtTokenProvider


@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity, jwtTokenProvider: JwtTokenProvider): SecurityWebFilterChain {
        return http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/", "/home", "/user").permitAll()
                .anyExchange().authenticated()
                .and().formLogin().authenticationSuccessHandler(authSuccessHandler(jwtTokenProvider))
                .and().logout()
                .and().securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .build();
    }

    //@Bean
    fun userDetailsService(): MapReactiveUserDetailsService {
        val user: UserDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build()
        return MapReactiveUserDetailsService(user)
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
            val response = webFilterExchange.exchange.response
            response.cookies.remove("SESSION")
            val jwt = jwtTokenProvider.createToken("aud", authentication.principal.toString(), 10000)
            LOG.info("Generated jwt is: [$jwt]")
            response.headers.add("Set-Cookie", "${AUTHORIZATION_HEADER}=Bearer ${jwt}; HttpOnly")
            return Mono.empty<Void>().then()
        }
    }
}