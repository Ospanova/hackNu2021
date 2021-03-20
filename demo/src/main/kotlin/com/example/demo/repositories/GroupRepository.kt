package com.example.demo.repositories

import com.example.demo.dtos.LocationDTO
import com.example.demo.entities.GroupDB
import org.springframework.data.jpa.repository.JpaRepository


interface GroupRepository: JpaRepository<GroupDB, Long> {
    fun getAllBy()
    fun getAllByLocationIndex(locationIndex: Long) : List<GroupDB>
//    fun getLocationGroups(locationDTO: LocationDTO) : GroupDB {
//
////        return locationDTO.getNormalizeLocation().map{
////            getAllByLocationIndex(it)
////        }
//    }
}