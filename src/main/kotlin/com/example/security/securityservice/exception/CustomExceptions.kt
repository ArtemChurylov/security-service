package com.example.security.securityservice.exception

class AuthenticationException(message: String, exception: Throwable? = null) : RuntimeException(message, exception)

class UserNotFoundException(message: String) : RuntimeException(message)
