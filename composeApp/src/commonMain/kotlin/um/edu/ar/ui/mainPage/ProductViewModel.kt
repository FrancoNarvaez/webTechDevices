package um.edu.ar.ui.mainPage

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import um.edu.ar.clases.Device

class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Device>>(emptyList())
    val products: StateFlow<List<Device>> = _products

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _navigationState = MutableStateFlow("ProductListView")
    val navigationState: StateFlow<String> = _navigationState

    fun loadProducts() {
        _isLoading.value = true
        // Simulate loading products
        _products.value = listOf(
            Device(1, "001", "Device 1", "Description 1", 100.0, "USD", listOf(), listOf(), listOf()),
            Device(2, "002", "Device 2", "Description 2", 200.0, "USD", listOf(), listOf(), listOf())
        )
        _isLoading.value = false
    }

    fun onProductSelected(product: Device) {
        _navigationState.value = "CustomizeProductView"
    }


}