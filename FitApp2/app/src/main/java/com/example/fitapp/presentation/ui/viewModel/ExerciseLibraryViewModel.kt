package com.example.fitapp.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitapp.data.local.database.ExerciseDao
import com.example.fitapp.domain.entities.ExerciseData
import com.example.fitapp.domain.entities.ExerciseEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseLibraryViewModel @Inject constructor(
    private val exerciseDao: ExerciseDao
) : ViewModel() {

    private val _exercises = MutableStateFlow<List<ExerciseEntity>>(emptyList())
    val exercises: StateFlow<List<ExerciseEntity>> = _exercises.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories.asStateFlow()

    init {
        seedAndLoad()
    }

    private fun seedAndLoad() {
        viewModelScope.launch {
            val existing = exerciseDao.getAllExercises()
            if (existing.isEmpty()) {
                exerciseDao.insertAll(ExerciseData.exercises)
            }
            loadExercises()
            _categories.value = exerciseDao.getCategories()
        }
    }

    fun loadExercises() {
        viewModelScope.launch {
            _exercises.value = exerciseDao.getAllExercises()
        }
    }

    fun filterByCategory(category: String?) {
        viewModelScope.launch {
            _exercises.value = if (category != null) {
                exerciseDao.getExercisesByCategory(category)
            } else {
                exerciseDao.getAllExercises()
            }
        }
    }

    fun searchExercises(query: String) {
        viewModelScope.launch {
            _exercises.value = exerciseDao.searchExercises(query)
        }
    }
}
