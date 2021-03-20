package com.example.demo.services

import com.example.demo.entities.Subscription
import com.example.demo.repositories.SubscriptionRepository
import com.example.demo.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class SubscriptionService (private val repository: SubscriptionRepository) {
    fun getSubscibedGroups(userId: Long) : List <Long> {
        return repository.getAllByUserId(userId).map { it.groupId }
    }
    fun addNew(userId: Long, groupId: Long) {
        this.repository.save(Subscription(userId, groupId))
    }
}