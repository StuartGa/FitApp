package com.example.fitapp

import MainActivityEffect
import MainActivityState
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.fitapp.presentation.ui.group.DashboardTopbar
import com.example.fitapp.presentation.ui.screens.TodayScreen
import com.example.fitapp.presentation.ui.theme.FitAppTheme
import com.example.fitapp.presentation.ui.viewModel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = viewModel.uiState.collectAsState()
            FitAppTheme {
                Scaffold(
                    topBar = {
                        DashboardTopbar({
                            viewModel::setEvent
                        })

                    }) { innerPadding ->

                    when (state.value) {
                        is MainActivityState.Idle -> {
                            CircularProgressIndicator(modifier = Modifier.padding(innerPadding))
                        }

                        is MainActivityState.Loading -> {
                            CircularProgressIndicator(
                                Modifier.fillMaxSize()
                                    .padding(innerPadding)
                            )
                        }

                        is MainActivityState.Error -> {
                            TodayScreen(iner = innerPadding, 0L)

                        }

                        is MainActivityState.Success -> {
                            TodayScreen(
                                iner = innerPadding,
                                steps = (state.value as MainActivityState.Success).steps
                            )
                        }

                        else -> {}
                    }


                }
            }
            LaunchedEffect(Unit) {
                lifecycleScope.launch {
                    viewModel.effect.collect { effect ->
                        when (effect) {
                            is MainActivityEffect.ShowToast -> {}
                            is MainActivityEffect.NavigateToSubDashboard -> {}
                            is MainActivityEffect.NavigateToDetailDashboard -> {}
                            is MainActivityEffect.CloseDashboard -> {
                                val intent =
                                    Intent(this@MainActivity, LoginActivity::class.java).apply {
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    }
                                startActivity(intent)
                            }

                            is MainActivityEffect.LoadSteps -> {}
                            else -> {}
                        }


                    }
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DashboardPreview() {
        FitAppTheme {
            Scaffold(modifier = Modifier.fillMaxSize(),
                topBar = {
                    DashboardTopbar({})
                })
            { innerPadding ->
                TodayScreen(innerPadding, 100L)
            }

        }
    }


}