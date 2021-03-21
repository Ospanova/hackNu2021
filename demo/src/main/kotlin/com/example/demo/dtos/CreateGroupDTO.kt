package com.example.demo.dtos

data class CreateGroupDTO (
    var level: String,
    var name: String = "",
    var locationX: Long? = null,
    var locationY: Long? = null,
    var admin: Long? = null,
)