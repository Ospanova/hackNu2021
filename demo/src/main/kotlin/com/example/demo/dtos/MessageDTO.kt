package com.example.demo.dtos

import java.sql.Date

data class MessageDTO (
    var text: String,
    var replyTo: Long? = null,
    var visible: Boolean? = true,
    val groupId: Long? = null
)