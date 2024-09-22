package um.edu.ar.ui.login.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class LoginViewModel : ViewModel() {
private val _email = MutableStateFlow("")
val email: StateFlow<String> = _email

private val _password = MutableStateFlow("")
val password: StateFlow<String> = _password

private val _loginEnable = MutableStateFlow(false)
val loginEnabled: StateFlow<Boolean> = _loginEnable

private val _isLoading = MutableStateFlow(false)
val isLoading: StateFlow<Boolean> = _isLoading

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidEmail(email: String): Boolean = email.contains("@") && email.contains(".")

    private fun isValidPassword(password: String): Boolean = password.length >= 6
    suspend fun onLoginSelected() {
        _isLoading.value = true
        delay(2000)
        _isLoading.value = false

    }

}
