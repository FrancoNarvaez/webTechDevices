package um.edu.ar.ui.ventas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import um.edu.ar.clases.VentasItems
import um.edu.ar.ui.theme.DarkBlue
import um.edu.ar.viewmodels.VentasViewModel

@Composable
fun VentasViewScreen(ventasViewModel: VentasViewModel) {
    val sales = ventasViewModel.sales.collectAsState().value

    // Fetch sales history when the composable is first displayed
    remember { ventasViewModel.fetchSalesHistory() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial de ventas", color = Color.White) },
                backgroundColor = Color.Blue
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(sales) { sale: VentasItems ->
                    VentasItemsView(sale)
                }
            }
        }
    )
}

@Composable
fun VentasItemsView(sale: VentasItems) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = sale.productName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(DarkBlue.value)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Quantity: ${sale.quantity}", fontSize = 16.sp)
            Text(text = "Total Price: $${sale.totalPrice}", fontSize = 16.sp)
            Text(text = "Date: ${sale.date}", fontSize = 16.sp)
        }
    }
}