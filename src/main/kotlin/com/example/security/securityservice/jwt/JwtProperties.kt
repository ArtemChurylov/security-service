package com.example.security.securityservice.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.time.Duration

@Component
@ConfigurationProperties("jwt")
data class JwtProperties(
    var secret: String = "",
    var issuer: String = "",
    var expirationTime: Duration = Duration.ZERO
)
