package com.example.demo.controller

import com.example.demo.dtos.GetMessageDTO
import com.example.demo.dtos.MessageDTO
import com.example.demo.dtos.ResponseMessage
import com.example.demo.entities.MessageDB
import com.example.demo.repositories.MessageRepository
import com.example.demo.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.sql.Date

@RestController
@RequestMapping("/messages")
class MessageController (val repository: MessageRepository, val userService: UserService){
    @GetMapping
    fun getMessages(@RequestHeader("jwt") jwt: String?, @RequestBody getMessage: GetMessageDTO) : ResponseEntity<Any> {
        val user = jwt?.let { userService.getUserFromCookie(it) } ?: return ResponseEntity.status(401).body(ResponseMessage("Unathorized"))
        if (getMessage.groupId != null)
            return ResponseEntity.ok(repository.getAllByGroupIdAndVisible(getMessage.groupId, getMessage.visible ?: true))
        if (getMessage.replyTo != null)
                return ResponseEntity.ok(repository.getAllByReplyToAndVisible(getMessage.replyTo, getMessage.visible ?: true))
        return ResponseEntity.ok(ResponseMessage("success"))
    }

    @PostMapping
    fun sendMessage(@RequestHeader("jwt") jwt: String?, @RequestBody message: MessageDTO) : ResponseEntity <Any> {
        val user = jwt?.let { userService.getUserFromCookie(it) } ?: return ResponseEntity.status(401).body(ResponseMessage("Unathorized"))
        repository.save(MessageDB(
            text = message.text,
            userId = user.id,
            replyTo = message.replyTo,
            visible = message.visible ?: true,
            createdAt = Date(System.currentTimeMillis()),
            groupId = message.groupId
        ))
        return ResponseEntity.ok(ResponseMessage("success"))
    }

    @PutMapping("/{id}")
    fun updateCustomer(@PathVariable id: Long, @RequestBody message: MessageDB) {
        assert(message.id == id)
        repository.save(message)
    }


}