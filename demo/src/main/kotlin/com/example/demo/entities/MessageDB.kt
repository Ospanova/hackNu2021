package com.example.demo.entities

import com.fasterxml.jackson.annotation.JsonIgnore
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
    @JsonIgnore
    var visible: Boolean = true,
    @JsonIgnore
    val createdAt: Date,
    val groupId: Long? = null,
    @Id
    @GeneratedValue
    var id: Long? = null
)  {
    private constructor() : this(text = "", userId = 0, createdAt = Date(System.currentTimeMillis()))
}