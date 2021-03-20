package com.example.demo.entities
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Client(
    var login: String,
    var firstname: String,
    var lastname: String,
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = -1) {

    private constructor() : this("", "", "")
}
