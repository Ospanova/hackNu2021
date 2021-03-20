package com.example.demo.dtos

data class GetMessageDTO (
    var replyTo: Long? = null,
    var visible: Boolean? = true,
    val groupId: Long? = null
)