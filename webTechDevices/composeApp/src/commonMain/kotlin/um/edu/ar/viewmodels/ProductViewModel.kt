package um.edu.ar.viewmodels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import um.edu.ar.clases.Adicional
import um.edu.ar.clases.Device
import um.edu.ar.clases.OpcionPersonalizacion
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import um.edu.ar.clases.Caracteristica
import um.edu.ar.clases.CartItem
import um.edu.ar.clases.Personalizacion
import um.edu.ar.utilidadFN.sendPurchaseRequest

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

    init {
        loadProducts()
    }

    fun loadProducts() {
        _isLoading.value = true
        _products.value = listOf(
            // Add your products here
            Device(
                id = 1,
                codigo = "TEST123",
                nombre = "Test Device 1",
                descripcion = "This is a test device 1",
                precioBase = 100.0,
                caracteristicas = listOf(
                    Caracteristica(
                        id = 1,
                        nombre = "Screen Size",
                        descripcion = "5.5 inches"
                    )
                ),
                personalizaciones = listOf(
                    Personalizacion(
                        id = 1,
                        nombre = "Color",
                        descripcion = "Choose a color",
                        opciones = listOf(
                            OpcionPersonalizacion(id = 1, codigo = "ASF87AS4", nombre = "Red", descripcion = "Red color", precioAdicional = 10.0),
                            OpcionPersonalizacion(id = 2, codigo = "BDF98DF5", nombre = "Blue", descripcion = "Blue color", precioAdicional = 15.0)                        )
                    )
                ),
                adicionales = listOf(
                    Adicional(id = 1, nombre = "Extra Battery", descripcion = "Extra battery pack", precio = 20.0, precioGratis = -1.0),
                    Adicional(id = 2, nombre = "Screen Protector", descripcion = "Screen protector", precio = 5.0, precioGratis = 199.99)
                ),
                moneda = "USD"
            ),
            Device(
                id = 2,
                codigo = "TEST124",
                nombre = "Test Device 2",
                descripcion = "This is a test device 2",
                precioBase = 200.0,
                caracteristicas = listOf(
                    Caracteristica(
                        id = 2,
                        nombre = "Battery Life",
                        descripcion = "10 hours"
                    )
                ),
                personalizaciones = listOf(
                    Personalizacion(
                        id = 2,
                        nombre = "Storage",
                        descripcion = "Choose storage size",
                        opciones = listOf(
                            OpcionPersonalizacion(id = 3, codigo = "CDF09GH6", nombre = "64GB", descripcion = "64GB storage", precioAdicional = 20.0),
                            OpcionPersonalizacion(id = 4, codigo = "EDF10IJ7", nombre = "128GB", descripcion = "128GB storage", precioAdicional = 40.0)                        )
                    )
                ),
                adicionales = listOf(
                    Adicional(id = 3, nombre = "Wireless Charger", descripcion = "Wireless charger", precio = 30.0, precioGratis = -1.0),
                    Adicional(id = 4, nombre = "Case", descripcion = "Protective case", precio = 10.0, precioGratis = 199.99)
                ),
                moneda = "USD"
            ),
            Device(
                id = 3,
                codigo = "TEST125",
                nombre = "Test Device 3",
                descripcion = "This is a test device 3",
                precioBase = 300.0,
                caracteristicas = listOf(
                    Caracteristica(
                        id = 3,
                        nombre = "Camera",
                        descripcion = "12MP"
                    )
                ),
                personalizaciones = listOf(
                    Personalizacion(
                        id = 3,
                        nombre = "Color",
                        descripcion = "Choose a color",
                        opciones = listOf(
                            OpcionPersonalizacion(id = 5, codigo = "FGH11KL8", nombre = "Black", descripcion = "Black color", precioAdicional = 10.0),
                            OpcionPersonalizacion(id = 6, codigo = "HIJ12MN9", nombre = "White", descripcion = "White color", precioAdicional = 15.0)                        )
                    )
                ),
                adicionales = listOf(
                    Adicional(id = 5, nombre = "Headphones", descripcion = "Wireless headphones", precio = 50.0, precioGratis = -1.0),
                    Adicional(id = 6, nombre = "Screen Protector", descripcion = "Screen protector", precio = 5.0, precioGratis = 199.99)
                ),
                moneda = "USD"
            ),
            Device(
                id = 4,
                codigo = "TEST126",
                nombre = "Test Device 4",
                descripcion = "This is a test device 4",
                precioBase = 400.0,
                caracteristicas = listOf(
                    Caracteristica(
                        id = 4,
                        nombre = "Processor",
                        descripcion = "Octa-core"
                    )
                ),
                personalizaciones = listOf(
                    Personalizacion(
                        id = 4,
                        nombre = "RAM",
                        descripcion = "Choose RAM size",
                        opciones = listOf(
                            OpcionPersonalizacion(id = 7, codigo = "JKL13OP0", nombre = "4GB", descripcion = "4GB RAM", precioAdicional = 20.0),
                            OpcionPersonalizacion(id = 8, codigo = "LMN14QR1", nombre = "8GB", descripcion = "8GB RAM", precioAdicional = 40.0)                        )
                    )
                ),
                adicionales = listOf(
                    Adicional(id = 7, nombre = "Extra Battery", descripcion = "Extra battery pack", precio = 20.0, precioGratis = -1.0),
                    Adicional(id = 8, nombre = "Case", descripcion = "Protective case", precio = 10.0, precioGratis = 199.99)
                ),
                moneda = "USD"
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

    fun onPurchaseClick(cartViewModel: CartViewModel) {
        // Simulate sending a purchase request to the backend
        _isLoading.value = true
        // Assuming a suspend function `sendPurchaseRequest` exists
        viewModelScope.launch {
            sendPurchaseRequest(
                product = _selectedProduct.value,
                customizations = _selectedCustomizations.value,
                addons = _selectedAddons.value
            )
            _isLoading.value = false
            cartViewModel.buyAllItems()
        }
    }

    fun onAddClick(cartViewModel: CartViewModel) {
        val selectedProduct = _selectedProduct.value ?: return
        val cartItem = CartItem(
            id = selectedProduct.id,
            name = selectedProduct.nombre,
            price = _finalPrice.value,
            quantity = 1, // Assuming quantity is 1 for simplicity
            customizations = _selectedCustomizations.value,
            addons = _selectedAddons.value
        )
        cartViewModel.addItemToCart(cartItem)
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