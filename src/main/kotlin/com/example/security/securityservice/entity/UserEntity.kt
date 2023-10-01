package com.example.security.securityservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("users")
data class UserEntity(
    @Id
    var id: Long? = null,
    val email: String,
    val password: String,
    val role: UserRole,
    val firstName: String,
    val lastName: String,
    val enabled: Boolean,
    val createDate: LocalDateTime,
    val modifyDate: LocalDateTime
)

enum class UserRole {
    USER,
    ADMIN
}
