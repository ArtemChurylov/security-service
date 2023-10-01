package com.example.security.securityservice.jwt

import com.example.security.securityservice.exception.InvalidTokenException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenVerifier(val jwtProperties: JwtProperties) {

    suspend fun verify(token: String): JwtTokenVerificationResult {
        try {
            return JwtTokenVerificationResult(getClaims(token), token)
        } catch (exception: Exception) {
            throw InvalidTokenException("Invalid token", exception)
        }
    }

    private suspend fun getClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(Base64.getEncoder().encodeToString(jwtProperties.secret.toByteArray()))
            .parseClaimsJws(token)
            .body
    }
}

data class JwtTokenVerificationResult(
    val claims: Claims,
    val token: String
)