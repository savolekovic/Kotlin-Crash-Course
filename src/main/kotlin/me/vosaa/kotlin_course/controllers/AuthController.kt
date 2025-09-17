package me.vosaa.kotlin_course.controllers

import me.vosaa.kotlin_course.security.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    data class AuthRequest(
        val email: String, val password: String
    )

    data class RefreshRequest(
        val refreshToken: String
    )

    @PostMapping("/register")
    fun register(
        @RequestBody body: AuthRequest
    ) {
        authService.register(email = body.email, password = body.password)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody body: AuthRequest
    ): AuthService.TokenPair {
        return authService.login(email = body.email, password = body.password)
    }

    @PostMapping("/refresh-token")
    fun refreshToken(
        @RequestBody body: RefreshRequest
    ): AuthService.TokenPair {
        return authService.refresh(refreshToken = body.refreshToken)
    }
}