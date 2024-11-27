package um.edu.ar.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import um.edu.ar.clases.VentasItems

class VentasViewModel : ViewModel() {
    private val _sales = MutableStateFlow<List<VentasItems>>(emptyList())
    val sales: StateFlow<List<VentasItems>> = _sales

    fun fetchSalesHistory() {
        viewModelScope.launch {
            // Simulate a backend call to fetch sales history
            val salesHistory = fetchSalesFromBackend()
            _sales.value = salesHistory
        }
    }

    private suspend fun fetchSalesFromBackend(): List<VentasItems> {
        // Replace with actual backend call
        return listOf(
            VentasItems(1, "Product A", 2, 40.0, "2023-10-01"),
            VentasItems(2, "Product B", 1, 20.0, "2023-10-02")
        )
    }
}