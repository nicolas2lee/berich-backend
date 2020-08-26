package tao.identitymanager.exposition.controller

import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class UserController (val reactiveUserDetailsService: ReactiveUserDetailsService) {

    @GetMapping("/welcome")
    fun welcome(): Mono<String> {
        return Mono.justOrEmpty("welcome")
    }

    @GetMapping("/")
    fun mainPage(): Mono<String> {
        return Mono.justOrEmpty("ok")
    }
}