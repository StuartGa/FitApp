import com.example.fitapp.presentation.ui.mvi.UIEvent


sealed class MainActivityEvent : UIEvent {

    object Loading : MainActivityEvent()
    object onLoadSteps : MainActivityEvent()
    object LogOut : MainActivityEvent()

}
