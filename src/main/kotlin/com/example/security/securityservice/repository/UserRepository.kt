package com.example.security.securityservice.repository

import com.example.security.securityservice.entity.UserEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository : CoroutineCrudRepository<UserEntity, Long> {

    suspend fun findByEmail(email: String): UserEntity?
}