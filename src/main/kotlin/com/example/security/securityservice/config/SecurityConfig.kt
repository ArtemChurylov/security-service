package com.example.security.securityservice.config

import com.example.security.securityservice.entity.UserRole
import com.example.security.securityservice.jwt.JwtAuthenticationManager
import com.example.security.securityservice.jwt.JwtTokenAuthenticationConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import reactor.core.publisher.Mono
import java.security.SecureRandom

@Configuration
class SecurityConfig {

    val whiteList = arrayOf("api/v1/auth/register", "api/v1/auth/login")

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity, jwtAuthFilter: AuthenticationWebFilter): SecurityWebFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeExchange {
                it.pathMatchers(HttpMethod.POST, *whiteList).permitAll()
                    .pathMatchers(HttpMethod.GET, "api/v1/user").hasAuthority(UserRole.ADMIN.toString())
                    .anyExchange().authenticated()
            }
            .exceptionHandling {
                it
                    .authenticationEntryPoint { exchange, _ ->
                        return@authenticationEntryPoint Mono.fromRunnable { exchange.response.statusCode = HttpStatus.UNAUTHORIZED }
                    }
            }
            .addFilterAt(jwtAuthFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .build()
    }

    @Bean
    fun jwtAuthFilter(authenticationManager: JwtAuthenticationManager, converter: JwtTokenAuthenticationConverter): AuthenticationWebFilter {
        return AuthenticationWebFilter(authenticationManager).apply { setServerAuthenticationConverter(converter) }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(10, SecureRandom())
    }
}