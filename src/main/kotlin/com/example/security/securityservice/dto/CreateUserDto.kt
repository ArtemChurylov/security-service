package com.example.security.securityservice.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class CreateUserDto(
    val email: String,
    val firstName: String,
    val lastName: String,
    var password: String
)
