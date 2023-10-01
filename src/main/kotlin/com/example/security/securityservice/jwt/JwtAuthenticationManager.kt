package com.example.security.securityservice.jwt

import com.example.security.securityservice.service.UserService
import kotlinx.coroutines.reactor.mono
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JwtAuthenticationManager(val userService: UserService) : ReactiveAuthenticationManager {
    override fun authenticate(authentication: Authentication): Mono<Authentication> = mono {
        val principal = authentication.principal as CustomPrincipal
        val user = userService.findUserByEmail(principal.email)
        if (!user.enabled) {
            throw DisabledException("User ${user.id} is disabled")
        }
        return@mono authentication
    }
}