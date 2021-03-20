package com.example.demo.repositories


import com.example.demo.entities.MessageDB
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<MessageDB, Long> {
    fun getAllByGroupIdAndVisible(groupId: Long?, visible: Boolean) : List<MessageDB>
    fun getAllByReplyToAndVisible(replyTo: Long?, visible: Boolean) : List<MessageDB>
}