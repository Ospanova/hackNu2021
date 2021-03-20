package com.example.demo.controller

import com.example.demo.entities.MessageDB
import com.example.demo.repositories.MessageRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/messages")
class MessageController (val repository: MessageRepository){
    @GetMapping
    fun findAll() = repository.findAll()
//
//    @PostMapping
//    fun addCustomer(@RequestBody message: Message)
//            = repository.save(message)
//
//    @PutMapping("/{id}")
//    fun updateCustomer(@PathVariable id: Long, @RequestBody message: Message) {
//        assert(message.id == id)
//        repository.save(message)
//    }


}