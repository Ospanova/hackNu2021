package com.example.demo.repositories


import com.example.demo.entities.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, Long> {
}