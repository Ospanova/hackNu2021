package com.example.demo.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Message (
    var text: String,
    var userId: Long? = null,

    @Id @GeneratedValue var id: Long? = null)