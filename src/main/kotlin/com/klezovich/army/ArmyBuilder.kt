package com.klezovich.army

class ArmyBuilder {

    // Use a Set<String> to avoid having to deal with duplicate unit types
    // Also this way the solution is more flexible with regards to boundary #4
    // from the requirements
    fun build(unitTypes: Set<String>, totalTroops: Int): Army {

        // basic input validation
        require(unitTypes.size <= totalTroops)

        var army = Army()
        var remainingTroops = totalTroops
        val unitTypesToGenerate = unitTypes.toMutableList()

        while (unitTypesToGenerate.isNotEmpty()) {

            // If there's only one unit type remaining fill to capacity
            if (unitTypesToGenerate.size == 1) {
                val unitType = unitTypesToGenerate[0]
                army.addUnit(ArmyUnit(unitType, remainingTroops))
                unitTypesToGenerate.remove(unitType)
                break
            }

            // Otherwise, randomly select the unit type to allocate troops to first
            // This is done to achieve random distribution
            // If we were to always pick the first unit type first from a statistical 
            // point of view it would have a higher average unit count
            val unitTypeIdx = (0 until unitTypesToGenerate.size).random()
            val unitTypeName = unitTypesToGenerate[unitTypeIdx]

            // Ensure that there is at least 1 unit of each requested type by substracting unitTypesToGenerate.size
            val unitTypeCount = (1 until (remainingTroops - unitTypesToGenerate.size)).random()
            remainingTroops -= unitTypeCount

            army.addUnit(ArmyUnit(unitType = unitTypeName, troopCount = unitTypeCount))
            unitTypesToGenerate.removeAt(unitTypeIdx)
        }

        return army
    }
}
