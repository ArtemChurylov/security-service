package com.example.security.securityservice.jwt

import kotlinx.coroutines.reactor.mono
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

private const val TOKEN_PREFIX = "Bearer "

@Component
class JwtTokenAuthenticationConverter(val jwtTokenVerifier: JwtTokenVerifier): ServerAuthenticationConverter {

    override fun convert(exchange: ServerWebExchange): Mono<Authentication> = mono {
        val token = getToken(exchange) ?: return@mono null
        val verificationResult = jwtTokenVerifier.verify(token)
        return@mono verificationResult.createAuthentication()
    }

    private suspend fun getToken(exchange: ServerWebExchange): String? {
        val tokenHeader = exchange.request.headers.getFirst(HttpHeaders.AUTHORIZATION) ?: return null
        return tokenHeader.substring(TOKEN_PREFIX.length)
    }
}

fun JwtTokenVerificationResult.createAuthentication(): Authentication {
    val claims = this.claims
    val principal = CustomPrincipal(claims.subject.toLong(), claims["email", String::class.java])
    return UsernamePasswordAuthenticationToken(principal, null, listOf(SimpleGrantedAuthority(claims["role", String::class.java])))
}