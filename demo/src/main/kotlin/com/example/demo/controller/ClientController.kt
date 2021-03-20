package com.example.demo.controller

import com.example.demo.entities.Client
import com.example.demo.repositories.ClientRepository
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(private val repository: ClientRepository) {

    @PostMapping
    fun addCustomer(@RequestBody client: Client)
            = repository.save(client)

    @GetMapping()
    fun findAll() = repository.findAll()

    @RequestMapping("/save")
    fun save(): String {
        repository.save(Client("aida", "Aida", "Ospanova"))
        repository.save(Client("shyngys", "Shyngys", "Saparbek"))

        return "Done"
    }

    @GetMapping("/{login}")
    fun findOne(@PathVariable login: String) = repository.findByLogin(login) ?: throw ResponseStatusException(NOT_FOUND, "This user does not exist")
}