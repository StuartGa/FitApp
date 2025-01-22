package com.example.fitapp.presentation.ui.viewModel


import MainActivityEffect
import MainActivityEvent
import MainActivityState
import androidx.lifecycle.viewModelScope
import com.example.fitapp.data.local.sensor.StepCounter
import com.example.fitapp.domain.usecases.GetStepsUseCase
import com.example.fitapp.domain.usecases.InsertStepsUseCase
import com.example.fitapp.presentation.ui.mvi.projectStructure.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val stepsUseCase: InsertStepsUseCase,
    private val getStepsUseCase: GetStepsUseCase,
    private val stepCounter: StepCounter,

)
    :BaseViewModel<MainActivityEvent, MainActivityState, MainActivityEffect>() {
    override fun createInitialState(): MainActivityState = MainActivityState.Idle


    override fun handleEvent(event: MainActivityEvent) {
        when (event) {
            is MainActivityEvent.Loading -> handleLoading()
            is MainActivityEvent.onLoadSteps -> handleLoading()
            else -> {}
        }
    }

    private fun handleLoading() {
        viewModelScope.launch {
            insertSteps()
            getStepsUseCase()
                .onStart { setState { MainActivityState.Loading } }
                .catch { setEffect { MainActivityEffect.ShowToast("Error getting steps") } }
                .collect { result ->
                    result.fold(
                        onSuccess = { steps ->
                            setState { MainActivityState.Success(steps) }
                        },
                        onFailure = { error ->
                            setState { MainActivityState.Error(error.message ?: "Unknown error") }
                            setEffect { MainActivityEffect.ShowToast("Error getting steps: ${error.message}") }
                        }
                    )
        }
    }
    }


    private fun insertSteps() {
        viewModelScope.launch {
        val steps =  stepCounter.steps()
            stepsUseCase(steps)
        }
    }

}
