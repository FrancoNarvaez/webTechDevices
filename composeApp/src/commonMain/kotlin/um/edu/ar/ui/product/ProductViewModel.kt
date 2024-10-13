package um.edu.ar.ui.product

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import um.edu.ar.clases.Adicional
import um.edu.ar.clases.Device
import um.edu.ar.clases.OpcionPersonalizacion
import um.edu.ar.clases.Personalizacion

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
            Device(
                id = 1,
                codigo = "001",
                nombre = "Notebook Pro",
                descripcion = "High-end notebook",
                precioBase = 1500.0,
                moneda = "USD",
                caracteristicas = listOf(),
                personalizaciones = listOf(
                    Personalizacion(
                        id = 1,
                        nombre = "RAM",
                        descripcion = "Choose your RAM size",
                        opciones = listOf(
                            OpcionPersonalizacion(id = 1, codigo = "RAM8GB", nombre = "8GB", descripcion = "8GB RAM", precioAdicional = 100.0),
                            OpcionPersonalizacion(id = 2, codigo = "RAM16GB", nombre = "16GB", descripcion = "16GB RAM", precioAdicional = 200.0)
                        )
                    ),
                    Personalizacion(
                        id = 2,
                        nombre = "Storage",
                        descripcion = "Choose your storage size",
                        opciones = listOf(
                            OpcionPersonalizacion(id = 3, codigo = "SSD256GB", nombre = "256GB SSD", descripcion = "256GB SSD", precioAdicional = 150.0),
                            OpcionPersonalizacion(id = 4, codigo = "SSD512GB", nombre = "512GB SSD", descripcion = "512GB SSD", precioAdicional = 250.0)
                        )
                    )
                ),
                adicionales = listOf(
                    Adicional(id = 1, nombre = "Mouse", descripcion = "Wireless Mouse", precio = 50.0, precioGratis = -1.0),
                    Adicional(id = 2, nombre = "Keyboard", descripcion = "Mechanical Keyboard", precio = 100.0, precioGratis = -1.0)
                )
            ),
            Device(
                id = 2,
                codigo = "002",
                nombre = "Tablet X",
                descripcion = "Affordable tablet",
                precioBase = 500.0,
                moneda = "USD",
                caracteristicas = listOf(),
                personalizaciones = listOf(
                    Personalizacion(
                        id = 3,
                        nombre = "Color",
                        descripcion = "Choose your color",
                        opciones = listOf(
                            OpcionPersonalizacion(id = 5, codigo = "ColorBlack", nombre = "Black", descripcion = "Black Color", precioAdicional = 0.0),
                            OpcionPersonalizacion(id = 6, codigo = "ColorWhite", nombre = "White", descripcion = "White Color", precioAdicional = 0.0)
                        )
                    )
                ),
                adicionales = listOf(
                    Adicional(id = 3, nombre = "Cover", descripcion = "Protective Cover", precio = 30.0, precioGratis = -1.0),
                    Adicional(id = 4, nombre = "Screen Protector", descripcion = "Tempered Glass Screen Protector", precio = 20.0, precioGratis = -1.0)
                )
            )
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