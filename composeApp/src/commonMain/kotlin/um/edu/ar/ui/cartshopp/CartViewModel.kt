package um.edu.ar.ui.cartshopp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CartViewModel : ViewModel() {
    private val _isCartVisible = MutableStateFlow(false)
    val isCartVisible: StateFlow<Boolean> = _isCartVisible

    fun onCartButtonClick() {
        _isCartVisible.value = !_isCartVisible.value
    }
}