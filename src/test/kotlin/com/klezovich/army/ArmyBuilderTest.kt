package com.klezovich.army

import com.klezovich.army.matcher.IsValidArmy
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ArmyBuilderTest {

    val builder = ArmyBuilder()

    @Test
    fun testValidArmyBuiltForSingleUnitType() {
        val army = builder.build(setOf("pikeman"), 100)
        val units = army.getUnits()

        assertEquals(1, units.size)
        assertEquals("pikeman", units[0].unitType)
        assertEquals(100, units[0].troopCount)
    }

    @Test
    fun testValidArmyBuildFor2UnitTypes() {
        val army = builder.build(setOf("t1", "t2"), 100)
        val units = army.getUnits()

        assertEquals(2, units.size)
        assertThat(units.map { it.unitType }, containsInAnyOrder("t1", "t2"))
        units.forEach {
            assertTrue(it.troopCount >= 1)
        }

        assertEquals(100, units.map { it.troopCount }.sum())
    }

    @Test
    fun testValidArmyCompositionFor3UnitsTypes() {
        val army = builder.build(setOf("t1", "t2", "t3"), 1000)

        // Testing armies with more than 3 unit types becomes difficult without a custom matcher :)
        assertThat(army, IsValidArmy.validArmyFor(setOf("t1", "t2", "t3"), 1000))
    }

    @Test
    fun testRandomArmyGenerationIsAlwaysValid() {
        repeat(1000) {
            val typeNum = (1..1000).random()
            val types = (1..typeNum).map {
                "t" + it
            }.toSet()

            val totalTroopCount = (typeNum..20000).random()

            val army = builder.build(unitTypes = types, totalTroops = totalTroopCount)
            assertThat(army, IsValidArmy.validArmyFor(unitTypes = types, totalTroops = totalTroopCount))
        }
    }
}
