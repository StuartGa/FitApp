package com.example.fitapp

import com.example.fitapp.domain.entities.AchievementData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AchievementTest {

    @Test
    fun `achievement data has 12 achievements`() {
        assertEquals(12, AchievementData.achievements.size)
    }

    @Test
    fun `achievements have unique keys`() {
        val keys = AchievementData.achievements.map { it.key }
        assertEquals(keys.size, keys.distinct().size)
    }

    @Test
    fun `all achievements have required fields`() {
        AchievementData.achievements.forEach { achievement ->
            assertTrue(achievement.key.isNotEmpty())
            assertTrue(achievement.title.isNotEmpty())
            assertTrue(achievement.description.isNotEmpty())
            assertTrue(achievement.iconName.isNotEmpty())
        }
    }
}
