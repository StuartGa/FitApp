import com.example.fitapp.domain.model.CardModel
import com.example.fitapp.domain.model.CircleModel
import com.example.fitapp.presentation.ui.mvi.UIState


sealed class MainActivityState: UIState {
    object Idle : MainActivityState()
    object Loading : MainActivityState()
    data class Success(val steps: Long, val circleModels: List<CircleModel>, val cards: List<CardModel>) : MainActivityState()
    data class Error(val error: String) : MainActivityState()
    object LogOut : MainActivityState()

}