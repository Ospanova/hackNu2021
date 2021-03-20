package com.example.demo.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Subscription (
    var userId: Long,
    var groupId: Long,
    @Id
    @GeneratedValue
    var id: Long? = null
) {
    private constructor() : this(userId= 0, groupId = 0)
}