import com.example.fitapp.presentation.ui.mvi.UIEffect

sealed class MainActivityEffect: UIEffect {
    object NavigateToSubDashboard: MainActivityEffect()
    object NavigateToDetailDashboard: MainActivityEffect()
    object CloseDashboard: MainActivityEffect()
    object LoadSteps: MainActivityEffect()
    data class ShowToast(val message: String): MainActivityEffect()
}
