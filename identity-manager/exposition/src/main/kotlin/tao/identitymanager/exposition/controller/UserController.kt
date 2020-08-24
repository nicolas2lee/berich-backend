package tao.identitymanager.exposition.controller

import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class UserController (val reactiveUserDetailsService: ReactiveUserDetailsService) {

    @GetMapping("/welcome")
    fun test(): Mono<String> {
        return Mono.justOrEmpty("welcome")
    }
    @GetMapping("/")
    fun testMain(): Mono<String> {
        return Mono.justOrEmpty("main")
    }
    @GetMapping("/user")
    fun getUser(): Mono<UserDetails> {
        println("test")
        return reactiveUserDetailsService.findByUsername("admin")
                .checkpoint("a test")
                .log()
    }
}