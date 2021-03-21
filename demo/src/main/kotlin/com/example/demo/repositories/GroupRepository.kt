package com.example.demo.repositories

import com.example.demo.dtos.LocationDTO
import com.example.demo.entities.GroupDB
import org.springframework.data.jpa.repository.JpaRepository


interface GroupRepository: JpaRepository<GroupDB, Long> {
    fun getAllBy()
    fun getByLongitudeAndLatitude(longitude: Long, latitude: Long) : GroupDB?
}