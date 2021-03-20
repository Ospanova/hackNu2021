package com.example.demo.repositories

import com.example.demo.entities.Subscription
import org.springframework.data.jpa.repository.JpaRepository

interface SubscriptionRepository : JpaRepository<Subscription, Long> {
    fun getAllByUserId(userId: Long) : List <Subscription>
}