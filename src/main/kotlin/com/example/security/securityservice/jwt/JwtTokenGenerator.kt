package com.example.security.securityservice.jwt

import com.example.security.securityservice.dto.UserDto
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenGenerator(val jwtProperties: JwtProperties) {

    suspend fun generateToken(user: UserDto): JwtTokenDetails {
        val issueDate = Date()
        val expirationDate = Date(issueDate.time + jwtProperties.expirationTime.toMillis())
        val claims = mapOf("email" to user.email, "role" to user.role)
        return generateToken(user.id, issueDate, expirationDate, claims)
    }

    private suspend fun generateToken(userId: Long, issueDate: Date, expirationDate: Date, claims: Map<String, Any>): JwtTokenDetails {
        val token = Jwts.builder()
            .addClaims(claims)
            .setIssuer(jwtProperties.issuer)
            .setSubject(userId.toString())
            .setIssuedAt(issueDate)
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(jwtProperties.secret.toByteArray()))
            .compact()
        return JwtTokenDetails(userId, token, issueDate, expirationDate);
    }
}