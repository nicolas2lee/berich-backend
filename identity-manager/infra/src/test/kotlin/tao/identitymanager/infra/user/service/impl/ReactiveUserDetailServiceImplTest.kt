package tao.identitymanager.infra.user.service.impl

import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import reactor.test.StepVerifier
import tao.identitymanager.infra.user.repository.UserRepository

class ReactiveUserDetailServiceImplTest{

    val userRepository: UserRepository = mock(UserRepository::class.java)

    val reactiveUserDetailServiceImpl: ReactiveUserDetailServiceImpl = ReactiveUserDetailServiceImpl(userRepository)

    @Test
    internal fun `Should return empty when username incorrect`() {
        given(userRepository.findByUsername(any() ?: "test")).willReturn(null)


        StepVerifier.create(reactiveUserDetailServiceImpl.findByUsername("user"))
                .verifyComplete()
    }
}