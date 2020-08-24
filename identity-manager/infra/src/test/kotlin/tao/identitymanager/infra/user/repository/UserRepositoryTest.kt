package tao.identitymanager.infra.user.repository

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.boot.test.context.SpringBootTest
import tao.identitymanager.infra.common.MongoConfig

@DataMongoTest
@SpringBootTest(classes = arrayOf(MongoConfig::class))
@Disabled
class UserRepositoryTest @Autowired constructor(val userRepository: UserRepository) {

    @Test
    fun `Should return admin user`() {
        //StepVerifier.create(userRepository.findByUsername("admin"))
                //.expectNext(UserEntity("admin", "admin", Collections.emptySet()))
          //      .expectComplete()
          //      .verify()
    }
}