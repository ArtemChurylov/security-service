package com.example.security.securityservice.jwt

import java.util.Date

data class JwtTokenDetails(
    val userId: Long,
    val token: String,
    val issueDate: Date,
    val expirationDate: Date,
)
