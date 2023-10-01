package com.example.security.securityservice.exception

import org.springframework.security.core.AuthenticationException

class InvalidTokenException(message: String, exception: Throwable? = null) : AuthenticationException(message, exception)

class UserNotFoundException(message: String) : RuntimeException(message)
