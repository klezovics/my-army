package com.klezovich.army

class Army {

    private var units = mutableListOf<ArmyUnit>()

    fun addUnit(unit: ArmyUnit) {
        units.add(unit)
    }

    fun getUnits(): List<ArmyUnit> {
        return units
    }
}
