import com.example.fitapp.presentation.ui.mvi.UIState


sealed class MainActivityState: UIState {
    object Idle : MainActivityState()
    object Loading : MainActivityState()
    data class Success(val steps: Long) : MainActivityState()
    data class Error(val error: String) : MainActivityState()
}