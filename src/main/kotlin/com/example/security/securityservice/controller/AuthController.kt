package com.example.security.securityservice.controller

import com.example.security.securityservice.dto.AuthUserDetails
import com.example.security.securityservice.dto.CreateUserDto
import com.example.security.securityservice.dto.UserDto
import com.example.security.securityservice.jwt.JwtTokenDetails
import com.example.security.securityservice.service.SecurityService
import com.example.security.securityservice.service.UserService
import org.springframework.web.bind.annotation.*

@RequestMapping("api/v1/auth")
@RestController
class AuthController(val userService: UserService, val securityService: SecurityService) {

    @PostMapping("register")
    suspend fun register(@RequestBody createUserDto: CreateUserDto) {
        userService.createUser(createUserDto)
    }

    @PostMapping("login")
    suspend fun authenticate(@RequestBody authUserDetails: AuthUserDetails): JwtTokenDetails {
        return securityService.authenticate(authUserDetails.email, authUserDetails.password)
    }

    @GetMapping
    suspend fun getUser(@RequestParam email: String): UserDto {
        return userService.findUserByEmail(email)
    }
}