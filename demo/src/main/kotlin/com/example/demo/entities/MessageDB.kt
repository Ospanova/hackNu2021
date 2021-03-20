package com.example.demo.entities

import java.sql.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany
@Entity
class MessageDB (
    var text: String,
    var userId: Long,
    var replyTo: Long? = null,
    var visible: Boolean,
    val createdAt: Date,
    @Id
    @GeneratedValue
    var id: Long? = null
)