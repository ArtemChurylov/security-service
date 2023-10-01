package com.example.security.securityservice.controller

import com.example.security.securityservice.dto.UserDto
import com.example.security.securityservice.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("api/v1/user")
@RestController
class UserDetailsController(val userService: UserService) {

    @GetMapping
    suspend fun getUser(@RequestParam email: String): UserDto {
        return userService.findUserByEmail(email)
    }
}