package com.example.demo.controller.models

import com.example.demo.entities.MessageDB


data class Group (
    val level: String,
    val name: String,
    val lastMessage: MessageDB? = null,
    val id: Long
)