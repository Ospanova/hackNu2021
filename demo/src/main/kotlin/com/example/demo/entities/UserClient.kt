package com.example.demo.entities
import com.example.demo.dtos.LocationDTO
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class UserClient(
    @Column(unique = true)
    var username: String,
    var firstname: String? = null,
    var lastname: String? = null,
    var XLocation: Double = 0.0,
    var YLocation: Double = 0.0,
    var email: String,
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0) {


    private constructor() : this(username = "", email= "")

    var password = ""
        @JsonIgnore
        get() = field
        set(value) {
            field = BCryptPasswordEncoder().encode(value)
        }


    public fun comparePassword(password: String) : Boolean{
        return BCryptPasswordEncoder().matches(password, this.password)
    }
    fun setLocation(locationDTO: LocationDTO) {
        this.XLocation = locationDTO.longitude
        this.YLocation = locationDTO.latitude
    }
}
