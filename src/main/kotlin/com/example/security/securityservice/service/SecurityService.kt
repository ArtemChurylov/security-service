package com.example.security.securityservice.service

import com.example.security.securityservice.jwt.JwtTokenDetails
import com.example.security.securityservice.jwt.JwtTokenGenerator
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SecurityService(
    val userService: UserService,
    val passwordEncoder: PasswordEncoder,
    val jwtTokenGenerator: JwtTokenGenerator
) {

    suspend fun authenticate(email: String, password: String): JwtTokenDetails {
        val user = userService.findUserByEmail(email)

        if (!user.enabled) {
            throw DisabledException("User ${user.id} is disabled")
        }

        if (!passwordEncoder.matches(password, user.password)) {
            throw BadCredentialsException("Password doesn't match")
        }

        return jwtTokenGenerator.generateToken(user)
    }
}