package com.example.security.securityservice.service

import com.example.security.securityservice.dto.CreateUserDto
import com.example.security.securityservice.dto.UserDto
import com.example.security.securityservice.mapper.toDto
import com.example.security.securityservice.mapper.toEntity
import com.example.security.securityservice.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository, val passwordEncoder: PasswordEncoder) {

    suspend fun createUser(createUserDto: CreateUserDto) {
        val userEntity = createUserDto.apply { password = passwordEncoder.encode(password) }.toEntity()
        userRepository.save(userEntity)
    }

    suspend fun findUserByEmail(email: String): UserDto {
        return userRepository.findByEmail(email)?.toDto() ?: throw RuntimeException("User not found")
    }
}