import com.example.fitapp.presentation.ui.mvi.UIEvent


sealed class MainActivityEvent : UIEvent {

    data class Loading(val email: String, val password: String) : MainActivityEvent()
    data class onLoadSteps(val email: String, val password: String) : MainActivityEvent()

}
