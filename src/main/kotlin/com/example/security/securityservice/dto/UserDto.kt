package com.example.security.securityservice.dto

import com.example.security.securityservice.entity.UserRole
import java.time.LocalDateTime

data class UserDto(
    val id: Long,
    var email: String,
    var password: String,
    var role: UserRole,
    var firstName: String,
    var lastName: String,
    var enabled: Boolean,
    val createDate: LocalDateTime,
    var modifyDate: LocalDateTime
)
