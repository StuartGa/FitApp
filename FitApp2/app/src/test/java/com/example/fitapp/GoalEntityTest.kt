package com.example.fitapp

import com.example.fitapp.domain.entities.GoalEntity
import com.example.fitapp.domain.entities.GoalType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GoalEntityTest {

    @Test
    fun `goal entity default values`() {
        val goal = GoalEntity(
            type = GoalType.DAILY_STEPS.key,
            targetValue = 10000.0,
            unit = "steps",
            userEmail = "test@email.com"
        )

        assertEquals(0L, goal.id)
        assertEquals(0.0, goal.currentValue, 0.0)
        assertTrue(goal.isActive)
        assertTrue(goal.startDate > 0)
    }

    @Test
    fun `goal types have correct keys`() {
        assertEquals("daily_steps", GoalType.DAILY_STEPS.key)
        assertEquals("calories", GoalType.CALORIES.key)
        assertEquals("workouts_per_week", GoalType.WORKOUTS_PER_WEEK.key)
        assertEquals("weight", GoalType.WEIGHT.key)
        assertEquals("water_intake", GoalType.WATER_INTAKE.key)
        assertEquals("active_minutes", GoalType.ACTIVE_MINUTES.key)
    }

    @Test
    fun `goal deactivation`() {
        val goal = GoalEntity(
            id = 1,
            type = "daily_steps",
            targetValue = 8000.0,
            unit = "steps",
            userEmail = "test@email.com",
            isActive = false
        )
        assertFalse(goal.isActive)
    }
}
