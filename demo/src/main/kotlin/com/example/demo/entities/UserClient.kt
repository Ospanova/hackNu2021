package com.example.demo.entities
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class UserClient(
    var username: String,
    var firstname: String? = null,
    var lastname: String? = null,
    @Column(unique = true)
    var email: String,
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0) {

    var password = ""
        get() = field
        set(value) {
            field = BCryptPasswordEncoder().encode(value)
        }
        private constructor() : this(username = "", email= "")
    public fun comparePassword(password: String) : Boolean{
        return BCryptPasswordEncoder().matches(password, this.password)
    }
}
