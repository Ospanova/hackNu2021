package com.example.demo.services

import com.example.demo.entities.UserClient
import com.example.demo.repositories.UserRepository
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service

@Service
class UserService (private val repository: UserRepository) {
    fun save(user: UserClient): UserClient {
        return this.repository.save(user)
    }
    fun findByUsername(username:String) : UserClient? {
        return this.repository.findByUsername(username).get(0)
    }
    fun getById(id: Long) : UserClient? {
        return this.repository.getOne(id)
    }
    fun getUserFromCookie(cookie: String) : UserClient? {
        return this.getById(Jwts.parser().setSigningKey("secret").parseClaimsJws(cookie).body.issuer.toLong())
    }
}