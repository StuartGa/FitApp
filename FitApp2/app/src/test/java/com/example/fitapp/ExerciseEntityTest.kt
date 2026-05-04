package com.example.fitapp

import com.example.fitapp.domain.entities.ExerciseData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExerciseEntityTest {

    @Test
    fun `exercise seed data has 50 exercises`() {
        assertEquals(50, ExerciseData.exercises.size)
    }

    @Test
    fun `all exercises have required fields`() {
        ExerciseData.exercises.forEach { exercise ->
            assertFalse(exercise.name.isEmpty())
            assertFalse(exercise.category.isEmpty())
            assertFalse(exercise.muscleGroup.isEmpty())
            assertFalse(exercise.instructions.isEmpty())
        }
    }

    @Test
    fun `exercises span multiple categories`() {
        val categories = ExerciseData.exercises.map { it.category }.distinct()
        assertTrue(categories.size >= 5)
        assertTrue(categories.contains("Strength"))
        assertTrue(categories.contains("Cardio"))
        assertTrue(categories.contains("Bodyweight"))
    }

    @Test
    fun `exercises have valid difficulties`() {
        val validDifficulties = setOf("Beginner", "Intermediate", "Advanced")
        ExerciseData.exercises.forEach { exercise ->
            assertTrue("${exercise.name} has invalid difficulty: ${exercise.difficulty}",
                validDifficulties.contains(exercise.difficulty))
        }
    }

    @Test
    fun `bench press has correct fields`() {
        val benchPress = ExerciseData.exercises.first { it.name == "Bench Press" }
        assertEquals("Strength", benchPress.category)
        assertEquals("Chest", benchPress.muscleGroup)
        assertEquals("Barbell", benchPress.equipment)
        assertEquals("Intermediate", benchPress.difficulty)
    }
}
