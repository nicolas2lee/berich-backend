package tao.identitymanager.domain.user

import java.time.LocalDateTime

data class User(val username: String,
                      val password: String,
                      val accountNonExpired: Boolean,
                      val accountNonLocked : Boolean,
                      val credentialsNonExpired: Boolean,
                      val enabled: Boolean,
                      val authorities: Set<String>,
                      val id: String,
                      val created: LocalDateTime,
                      val updated: LocalDateTime) {
}