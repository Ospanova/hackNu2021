package com.example.demo.controller.models

import sun.plugin2.message.Message

data class Group (
    val level: String?,
    val name: String,
    val lastMessage: Message? = null,
    val id: Long
)