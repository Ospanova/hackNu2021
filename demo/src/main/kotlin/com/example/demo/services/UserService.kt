package com.example.demo.services

import com.example.demo.entities.UserClient
import com.example.demo.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (private val repository: UserRepository) {
    fun save(user: UserClient): UserClient {
        return this.repository.save(user)
    }
    fun findByEmail(email:String) : UserClient? {
        return this.repository.findByEmail(email)
    }
}