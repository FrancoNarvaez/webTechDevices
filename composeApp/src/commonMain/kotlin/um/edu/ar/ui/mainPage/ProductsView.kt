package um.edu.ar.ui.mainPage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import um.edu.ar.clases.Device


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
                    Text(text = "Precio base: ${product.precioBase}")
                    Button(onClick = { onSelectProduct(product) }) {
                        Text("Seleccionar")
                    }
                }
            }
        }
    }
}