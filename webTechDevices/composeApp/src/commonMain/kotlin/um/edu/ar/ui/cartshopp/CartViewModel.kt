package um.edu.ar.ui.cartshopp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import um.edu.ar.clases.CartItem

class CartViewModel : ViewModel() {
    private val _isCartVisible = MutableStateFlow(false)
    val isCartVisible: StateFlow<Boolean> = _isCartVisible

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    fun onCartButtonClick() {
        _isCartVisible.value = !_isCartVisible.value
    }

    fun addItemToCart(item: CartItem) {
        _cartItems.value = _cartItems.value.toMutableList().apply {
            add(item)
        }
    }

    fun removeItemFromCart(item: CartItem) {
        _cartItems.value = _cartItems.value.toMutableList().apply {
            remove(item)
        }
    }

    fun buyAllItems() {
        // Implement the logic to process the purchase of all items in the cart
        // For example, send the cart items to a backend service
        _cartItems.value = emptyList() // Clear the cart after purchase
    }
}
