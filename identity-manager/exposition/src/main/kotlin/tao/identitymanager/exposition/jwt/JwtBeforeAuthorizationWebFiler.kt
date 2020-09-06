package tao.identitymanager.exposition.jwt

import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

class JwtBeforeAuthorizationWebFiler(private val jwtTokenProvider: JwtTokenProvider) : WebFilter {
    companion object{
        const val JWT_COOKIE_KEY = "token"
        var LOG =  LoggerFactory.getLogger(JwtBeforeAuthorizationWebFiler::class.java)
    }

    @Throws(AccessDeniedException::class)
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val mutableList = exchange.request.cookies[JWT_COOKIE_KEY]
        if (mutableList == null || mutableList.isEmpty()) throw AccessDeniedException("jwt is empty")
        val jwt = mutableList[0].value
        LOG.info("jwt is [$jwt]")
        val jwtUserInfo: JwtTokenProvider.JwtUserInfo
        try {
            jwtUserInfo = jwtTokenProvider.parseJwtWithSignatureVerification(jwt)
            LOG.info("jwtUserInfo is [${jwtUserInfo.toString()}]")
        } catch (e: JwtTokenProvider.InvalidJwtException){
            throw AccessDeniedException("Invalid jwt token", e)
        }
        return chain.filter(exchange).subscriberContext(
                            ReactiveSecurityContextHolder.withAuthentication(
                                    UsernamePasswordAuthenticationToken(jwtUserInfo.username, null, jwtUserInfo.authorities)
                            )
                    )
    }

}