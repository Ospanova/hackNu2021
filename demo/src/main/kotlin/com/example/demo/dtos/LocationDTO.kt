package com.example.demo.dtos

enum class CardType(val coefficient: Long) {
    FAR1X(1000),
    FAR2X(100),
    FAR3X(10)
}


data class LocationDTO (
    val X: Double,
    val Y: Double
)

{
    companion object  {
        private val maxY = 360
    }
    fun getNormalizeLocation(): List<Long> {
        var possibleLocations : List<Long> = emptyList()
        CardType.values().map {
            val x = this.X + 90
            val y = this.Y + 180
            val index = x * it.coefficient * it.coefficient * maxY + y * it.coefficient
            possibleLocations.toMutableList().plusElement(index.toLong())
        }
        return possibleLocations
    }
}