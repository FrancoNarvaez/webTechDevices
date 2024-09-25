package um.edu.ar.ui.product

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import um.edu.ar.clases.Adicional
import um.edu.ar.clases.Device
import um.edu.ar.clases.OpcionPersonalizacion

class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Device>>(emptyList())
    val products: StateFlow<List<Device>> = _products

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _navigationState = MutableStateFlow("login")
    val navigationState: StateFlow<String> = _navigationState

    private val _selectedProduct = MutableStateFlow<Device?>(null)
    val selectedProduct: StateFlow<Device?> = _selectedProduct

    private val _selectedCustomizations = MutableStateFlow<Map<String, OpcionPersonalizacion>>(emptyMap())
    val selectedCustomizations: StateFlow<Map<String, OpcionPersonalizacion>> = _selectedCustomizations

    private val _selectedAddons = MutableStateFlow<List<Adicional>>(emptyList())
    val selectedAddons: StateFlow<List<Adicional>> = _selectedAddons

    private val _finalPrice = MutableStateFlow(0.0)
    val finalPrice: StateFlow<Double> = _finalPrice

    fun loadProducts() {
        _isLoading.value = true
        _products.value = listOf(
            Device(1, "001", "Notebook Pro", "High-end notebook", 1500.0, "USD", listOf(), listOf(), listOf()),
            Device(2, "002", "Tablet X", "Affordable tablet", 500.0, "USD", listOf(), listOf(), listOf())
        )
        _isLoading.value = false
    }

    fun onProductSelected(product: Device) {
        _selectedProduct.value = product
        _navigationState.value = "CustomizeProductView"
    }

    fun onCustomizationChange(customization: String, option: OpcionPersonalizacion) {
        _selectedCustomizations.value = _selectedCustomizations.value.toMutableMap().apply {
            put(customization, option)
        }
        calculateFinalPrice()
    }

    fun onAddonToggle(addon: Adicional) {
        val currentAddons = _selectedAddons.value.toMutableList()
        if (currentAddons.contains(addon)) {
            currentAddons.remove(addon)
        } else {
            currentAddons.add(addon)
        }
        _selectedAddons.value = currentAddons
        calculateFinalPrice()
    }

    private fun calculateFinalPrice() {
        val basePrice = _selectedProduct.value?.precioBase ?: 0.0
        val customizationsPrice = _selectedCustomizations.value.values.sumOf { it.precioAdicional }
        val addonsPrice = _selectedAddons.value.sumOf { it.precio }
        _finalPrice.value = basePrice + customizationsPrice + addonsPrice
    }

    fun onCancelCustomization() {
        _navigationState.value = "ProductListView"
    }

    fun onBackButtonClick() {
        if (_navigationState.value contentEquals "CustomizeProductView"){
            _navigationState.value = "ProductListView"
        }
    }
}