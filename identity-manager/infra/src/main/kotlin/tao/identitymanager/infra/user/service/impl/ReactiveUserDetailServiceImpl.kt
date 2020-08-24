package tao.identitymanager.infra.user.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import tao.identitymanager.infra.user.repository.UserRepository

@Service
@Primary
class ReactiveUserDetailServiceImpl(@Autowired val userRepository: UserRepository) : ReactiveUserDetailsService {
    override fun findByUsername(username: String): Mono<UserDetails> {
        return Mono.justOrEmpty(userRepository.findByUsername(username))
                .checkpoint("Post fetch users from database")
                .map { it?.toDomain() }

    }
}