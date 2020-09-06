package tao.identitymanager.exposition.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.security.Principal

@RestController
class UserController() {

    @GetMapping("/welcome")
    fun welcome(): Mono<String> {
        return Mono.justOrEmpty("welcome")
    }

    @GetMapping("/me")
    fun myProfile(exchange: ServerWebExchange): Mono<Principal> {
        return exchange.getPrincipal<Principal>()
                .log()
    }
}