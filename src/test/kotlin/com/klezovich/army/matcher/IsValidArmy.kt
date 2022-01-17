package com.klezovich.army.matcher

import com.klezovich.army.Army
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class IsValidArmy(
    val targetUnitTypes: Set<String>,
    val targetTotalTroopCount: Int
) : TypeSafeMatcher<Army>() {

    override fun describeTo(description: Description) {
        description.appendText("Checks if valid army has been generated")
    }

    override fun matchesSafely(army: Army): Boolean {
        val units = army.getUnits()

        // Check all unit types present
        val armyUnitTypes = units.map { it.unitType }.toSet()
        if (!armyUnitTypes.equals(targetUnitTypes)) return false

        // Check each unit has at least one soldier
        if (units.map { it.troopCount }.any { it <= 0 }) return false

        // Check total troop count is valid
        if (units.map { it.troopCount }.sum() != targetTotalTroopCount) return false
        return true
    }

    companion object {
        fun validArmyFor(unitTypes: Set<String>, totalTroops: Int): Matcher<Army> {
            return IsValidArmy(targetUnitTypes = unitTypes, targetTotalTroopCount = totalTroops)
        }
    }
}
