package um.edu.ar.ui.login.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class LoginViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _loginEnable = MutableStateFlow(false)
    val loginEnabled: StateFlow<Boolean> = _loginEnable

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _navigationState = MutableStateFlow("login")
    val navigationState: StateFlow<String> = _navigationState

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidEmail(email: String): Boolean = email.contains("@") && email.contains(".")

    private fun isValidPassword(password: String): Boolean = password.length >= 6
    fun onLoginSelected() {
        _isLoading.value = true
        _navigationState.value = "ProductListView"
        _isLoading.value = false

    }

}
