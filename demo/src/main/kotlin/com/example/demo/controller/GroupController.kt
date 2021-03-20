package com.example.demo.controller

import com.example.demo.controller.models.Group
import com.example.demo.dtos.LocationDTO
import com.example.demo.dtos.ResponseMessage
import com.example.demo.entities.GroupDB
import com.example.demo.entities.Subscription
import com.example.demo.repositories.GroupRepository
import com.example.demo.services.SubscriptionService
import com.example.demo.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/groups")
class GroupController (val service: SubscriptionService, val groupRepository: GroupRepository, val userService: UserService) {
    @GetMapping
    fun findAll(@RequestHeader("jwt") jwt: String?): ResponseEntity<Any> {
        val user = jwt?.let { userService.getUserFromCookie(it) } ?: return ResponseEntity.status(401).body(ResponseMessage("Unauthorized"))
        val groups = this.service.getSubscibedGroups(user.id).toSet().map {
            val group = groupRepository.getOne(it)
            Group(level = group.level, group.name, null, group.id ?: 0)
        }
        return ResponseEntity.ok(groups)
    }
    @PostMapping
    fun newGroup(@RequestHeader("jwt") jwt: String?, @RequestBody group: GroupDB): ResponseEntity<GroupDB> {
        val newGroup = this.groupRepository.save(group)
        if (jwt != null) {
            val user = userService.getUserFromCookie(jwt)
            if (user != null) {
                newGroup.id?.let { service.addNew(user.id, it) }
            }
        }
        return ResponseEntity.ok(newGroup)
    }
    @PostMapping("pin/{groupId}")
    fun pinGroup(@RequestHeader("jwt") jwt: String?, @PathVariable groupId: Long) : ResponseEntity<Any> {
        val user = jwt?.let { userService.getUserFromCookie(it) } ?: return ResponseEntity.status(401).body(ResponseMessage("Unauthorized"))
        service.addNew(user.id, groupId)
        return ResponseEntity.ok(ResponseMessage("success"))
    }
    @GetMapping("locationGroups")
    fun getLocationGroups(@RequestHeader("jwt") jwt: String?, @RequestBody locationDTO: LocationDTO) : ResponseEntity<Any> {
        val user = jwt?.let { userService.getUserFromCookie(it) } ?: return ResponseEntity.status(401).body(ResponseMessage("Unauthorized"))
        user.setLocation(locationDTO)
        val groups: List <GroupDB> = emptyList()
//        locationDTO.getNormalizeLocation().map{
//            groups.plusElement(groupRepository.getAllByLocationIndex(it)).get(0)
//        }
        return ResponseEntity.ok(groups)
    }

}