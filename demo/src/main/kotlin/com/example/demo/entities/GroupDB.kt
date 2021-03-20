package com.example.demo.entities

import java.sql.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class GroupDB (
    var level: String? = null,
    var name: String = "",
    var locationIndex: Long? = null,
    var userId: Long? = null,
    @Id @GeneratedValue
    var id: Long? = null
) {
    private constructor() : this(name = "")
}