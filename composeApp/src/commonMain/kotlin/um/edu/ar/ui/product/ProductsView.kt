package um.edu.ar.ui.product

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import um.edu.ar.clases.Device
import um.edu.ar.ui.theme.*

@Composable
fun ProductsScreen(viewModel: ProductViewModel) {
    val products = viewModel.products.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val coroutineScope = rememberCoroutineScope()


    if (isLoading) {
        Box(
            Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        ProductListView(products) { product ->
            coroutineScope.launch {
                viewModel.onProductSelected(product)
            }
        }
    }
}

@Composable
fun ProductListView(products: List<Device>, onSelectProduct: (Device) -> Unit) {
    LazyColumn {
        items(products.size) { index ->
            val product = products[index]
            Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = product.nombre, style = MaterialTheme.typography.h6)
                    Text(text = product.descripcion, style = MaterialTheme.typography.body2)
                    Text(text = "Precio base: ${product.precioBase} ${product.moneda}")
                    Button(onClick = { onSelectProduct(product) }, colors = ButtonDefaults.buttonColors(backgroundColor = BackgroundColorBlue)) {
                        Text("Seleccionar")
                    }
                }
            }
        }
    }
}
