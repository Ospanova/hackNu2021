package com.example.demo.repositories

import com.example.demo.entities.UserClient
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserClient, Long> {
    fun findByEmail(email: String) : UserClient?
}