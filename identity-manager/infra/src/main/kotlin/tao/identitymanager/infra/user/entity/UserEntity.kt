package tao.identitymanager.infra.user.entity

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import tao.identitymanager.infra.common.AbstractEntity


@Document("user")
class UserEntity : AbstractEntity() {

    lateinit var username: String
    //default password are encrypted with bcrypt
    lateinit var password: String
    lateinit var authorities: Collection<String>


    fun toDomain() : UserDetails {
        return User(username, password, authorities.map { SimpleGrantedAuthority(it) })
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEntity

        if (username != other.username) return false

        return true
    }

    override fun hashCode(): Int {
        return username.hashCode()
    }

}