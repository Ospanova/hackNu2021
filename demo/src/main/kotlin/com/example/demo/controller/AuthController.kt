package com.example.demo.controller

import com.example.demo.dtos.LoginDTO
import com.example.demo.dtos.RegisterDTO
import com.example.demo.dtos.ResponseMessage
import com.example.demo.entities.UserClient
import com.example.demo.repositories.UserRepository
import com.example.demo.services.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api")
class AuthController(val userService: UserService) {

    @PostMapping("register")
    fun register(@RequestBody body: RegisterDTO) : ResponseEntity<UserClient> {
        val user  = UserClient(username = body.username, email = body.email)
        user.password = body.password
        return ResponseEntity.ok(this.userService.save(user))
    }


    @PostMapping("login")
    fun login(@RequestBody body: LoginDTO, response: HttpServletResponse) : ResponseEntity<Any> {
        val user: UserClient = (this.userService.findByEmail(body.email) ?: return ResponseEntity.badRequest().body(ResponseMessage(message = "User not found!")))
        if (!user.comparePassword(body.password))
            return ResponseEntity.badRequest().body(ResponseMessage("Invalid password"))
        val issuer = user.id.toString()
        val token = Jwts.builder().setIssuer(issuer).setExpiration(Date(System.currentTimeMillis() + 60*24*3000))
            .signWith(SignatureAlgorithm.HS256, "secret").compact()
        val cookie  = Cookie("jwt", token)
        cookie.isHttpOnly = true
        response.addCookie(cookie)
        return ResponseEntity.ok(ResponseMessage("success"))
    }
    @GetMapping("user")
    fun user(@CookieValue("jwt") jwt: String?) : ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(ResponseMessage("Unathorized"))
            }
            return ResponseEntity.ok(userService.getUserFromCookie(jwt))
        } catch(e: Exception) {
            return ResponseEntity.status(401).body(ResponseMessage("Unathorized"))
        }
    }

}