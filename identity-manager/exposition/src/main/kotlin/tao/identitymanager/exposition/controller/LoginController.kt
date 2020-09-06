package tao.identitymanager.exposition.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class LoginController () {

    @GetMapping("/login")
    fun login(): Mono<String> {
        return Mono.justOrEmpty("please login")
    }
}