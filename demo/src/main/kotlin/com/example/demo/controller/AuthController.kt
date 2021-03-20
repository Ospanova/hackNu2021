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
import java.util.*

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
    fun login(@RequestBody body: LoginDTO) : ResponseEntity<Any> {
        val user: UserClient = (this.userService.findByEmail(body.email) ?: ResponseEntity.badRequest().body(ResponseMessage(message = "User not found!"))) as UserClient
        if (!user.comparePassword(body.password))
            return ResponseEntity.badRequest().body(ResponseMessage("Invalid password"))
        val issuer = user.id.toString()
        val token = Jwts.builder().setIssuer(issuer).setExpiration(Date(System.currentTimeMillis() + 60*24*3000))
            .signWith(SignatureAlgorithm.HS256, "secret").compact()

        return ResponseEntity.ok(token)
    }


//    @GetMapping("/{login}")
//    fun findOne(@PathVariable login: String) = repository.findByLogin(login) ?: throw ResponseStatusException(NOT_FOUND, "This user does not exist")
}