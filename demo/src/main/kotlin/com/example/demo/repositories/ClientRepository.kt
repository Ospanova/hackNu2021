package com.example.demo.repositories

import com.example.demo.entities.Client
import org.springframework.data.repository.CrudRepository

interface ClientRepository : CrudRepository<Client, Long> {
    fun findByLogin(login: String): Client?
}