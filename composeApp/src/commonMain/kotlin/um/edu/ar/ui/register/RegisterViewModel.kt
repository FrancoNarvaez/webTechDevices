package um.edu.ar.ui.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword

    private val _registerEnable = MutableStateFlow(false)
    val registerEnabled: StateFlow<Boolean> = _registerEnable

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    fun onRegisterChanged(email: String, password: String, confirmPassword: String) {
        _email.value = email
        _password.value = password
        _confirmPassword.value = confirmPassword
        _registerEnable.value = isValidEmail(email) && isValidPassword(password) && password == confirmPassword
    }

    private fun isValidEmail(email: String): Boolean = email.contains("@") && email.contains(".")

    private fun isValidPassword(password: String): Boolean = password.length >= 6

}