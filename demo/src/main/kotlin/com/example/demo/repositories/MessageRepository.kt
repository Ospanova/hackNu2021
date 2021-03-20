package com.example.demo.repositories


import com.example.demo.entities.MessageDB
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<MessageDB, Long> {
}