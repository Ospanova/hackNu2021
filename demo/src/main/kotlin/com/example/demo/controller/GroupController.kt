package com.example.demo.controller

import com.example.demo.controller.models.Group
import com.example.demo.dtos.CreateGroupDTO
import com.example.demo.dtos.LocationDTO
import com.example.demo.dtos.ResponseMessage
import com.example.demo.entities.GroupDB
import com.example.demo.entities.Subscription
import com.example.demo.repositories.GroupRepository
import com.example.demo.services.SubscriptionService
import com.example.demo.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse


enum class CardType(val coefficient: Long) {
    FAR1X(1000),
    FAR2X(100),
    FAR3X(10)
}


@RestController
@RequestMapping("/groups")
class GroupController (val service: SubscriptionService, val groupRepository: GroupRepository, val userService: UserService) {
    @GetMapping
    fun findAll(@RequestHeader("jwt") jwt: String?, response: HttpServletResponse): ResponseEntity<Any> {
        response.addHeader("Access-Control-Allow-Origin", "http://192.168.1.81:3000")
        response.addHeader("access-control-expose-headers", "Location")

        val user = jwt?.let { userService.getUserFromCookie(it) } ?: return ResponseEntity.status(401).body(ResponseMessage("Unauthorized"))
        val groups = this.service.getSubscibedGroups(user.id).toSet().map {
            val group = groupRepository.getOne(it)
            Group(level = group.level, group.name, null, group.id ?: 0)
        }
        return ResponseEntity.ok(groups)
    }
    @PostMapping
    fun newGroup(@RequestHeader("jwt") jwt: String?, @RequestBody group: GroupDB, response: HttpServletResponse): ResponseEntity<Any> {
        response.addHeader("Access-Control-Allow-Origin", "*")
        response.addHeader("access-control-expose-headers", "Location")
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
    fun pinGroup(@RequestHeader("jwt") jwt: String?, @PathVariable groupId: Long, response: HttpServletResponse) : ResponseEntity<Any> {
        response.addHeader("Access-Control-Allow-Origin", "http://192.168.1.81:3000")
        response.addHeader("access-control-expose-headers", "Location")

        val user = jwt?.let { userService.getUserFromCookie(it) } ?: return ResponseEntity.status(401).body(ResponseMessage("Unauthorized"))
        service.addNew(user.id, groupId)
        return ResponseEntity.ok(ResponseMessage("success"))
    }
    @GetMapping("locationGroups")
    fun getLocationGroups(@RequestHeader("jwt") jwt: String?, @RequestBody locationDTO: LocationDTO, response: HttpServletResponse) : ResponseEntity<Any?> {
        response.addHeader("Access-Control-Allow-Origin", "http://192.168.1.81:3000")
        response.addHeader("access-control-expose-headers", "Location")
        val user = jwt?.let { userService.getUserFromCookie(it) } ?: return ResponseEntity.status(401).body(ResponseMessage("Unauthorized"))
        user.setLocation(locationDTO)
        val data = CardType.values().map {  type ->
            val g = this.groupRepository.getByLongitudeAndLatitude((locationDTO.longitude*type.coefficient).toLong(), (locationDTO.latitude*type.coefficient).toLong())
            if (g != null) {
                type to Group(g.level, g.name, null, id = g.id ?: 0)
            } else
                type to null
        }.toMap()
        return ResponseEntity.ok(data.values.filterNotNull())
    }

}