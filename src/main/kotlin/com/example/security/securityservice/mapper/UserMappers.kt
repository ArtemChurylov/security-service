package com.example.security.securityservice.mapper

import com.example.security.securityservice.dto.CreateUserDto
import com.example.security.securityservice.dto.UserDto
import com.example.security.securityservice.entity.UserEntity
import com.example.security.securityservice.entity.UserRole
import java.time.LocalDateTime

fun UserEntity.toDto(): UserDto {
    return UserDto(
        id = this.id!!,
        email = this.email,
        password = this.password,
        role = this.role,
        firstName = this.firstName,
        lastName = this.lastName,
        enabled = this.enabled,
        createDate = this.createDate,
        modifyDate = this.modifyDate
    )
}

fun CreateUserDto.toEntity(): UserEntity {
    val createDate = LocalDateTime.now()
    return UserEntity(
        email = this.email,
        password = this.password,
        role = UserRole.USER,
        firstName = this.firstName,
        lastName = this.lastName,
        enabled = true,
        createDate = createDate,
        modifyDate = createDate
    )
}