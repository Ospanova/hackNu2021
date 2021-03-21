package com.example.demo.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import java.sql.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class GroupDB ( // toDo add type
    var level: String? = null,
    var name: String = "",
    var longitude: Long? = null,
    var latitude: Long? = null,
    var admin: Long? = null,
    @Id @GeneratedValue
    var id: Long? = null
)