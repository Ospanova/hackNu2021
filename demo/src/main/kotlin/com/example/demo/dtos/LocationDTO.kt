package com.example.demo.dtos




data class LocationDTO (
    var longitude: Double,
    var latitude: Double
)

{
    companion object  {
        private val maxY = 360
    }
    fun normalize() {
        this.longitude = this.longitude + 90
        this.latitude = this.latitude + 180
    }
}