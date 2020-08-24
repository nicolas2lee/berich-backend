package tao.identitymanager.infra.user.repository

import org.springframework.data.mongodb.repository.MongoRepository
import tao.identitymanager.infra.user.entity.UserEntity


interface UserRepository: MongoRepository<UserEntity, String> {
    fun findByUsername(username: String) : UserEntity?
}
