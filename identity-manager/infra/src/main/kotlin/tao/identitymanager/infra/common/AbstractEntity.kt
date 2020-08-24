package tao.identitymanager.infra.common

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

abstract class AbstractEntity() {
    @Id
    lateinit var id: String
    //lateinit var username: String
    lateinit var updated: LocalDateTime
    lateinit var created: LocalDateTime
}