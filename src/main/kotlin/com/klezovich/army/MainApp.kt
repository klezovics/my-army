package com.klezovich.army

fun main() {
    val builder = ArmyBuilder()
    var army = builder.build(setOf("spearmen", "swordsmen", "archers"), 167)

    println("Army composition")
    army.getUnits().forEach {
        println("${it.troopCount} ${it.unitType}")
    }
}
