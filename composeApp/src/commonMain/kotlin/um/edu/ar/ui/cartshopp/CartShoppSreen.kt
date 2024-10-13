package um.edu.ar.ui.cartshopp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import um.edu.ar.clases.CartItem
import um.edu.ar.ui.theme.BackgroundColorBlue
import um.edu.ar.ui.theme.LightGray
import webtechdevices.composeapp.generated.resources.Res
import webtechdevices.composeapp.generated.resources.icons8_shopping_cart_48
import webtechdevices.composeapp.generated.resources.cross

@Composable
fun CartShoppScreen(modifier: Modifier = Modifier) {
    val cartViewModel: CartViewModel = viewModel()
    val cartItems = cartViewModel.cartItems.collectAsState().value

    Box(
        modifier = modifier.fillMaxSize().padding(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
            ) {
                Text(
                    "Shopping Cart",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.weight(1f).padding(top = 14.dp)
                )
                Image(
                    painter = org.jetbrains.compose.resources.painterResource(Res.drawable.icons8_shopping_cart_48),
                    contentDescription = "Cart",
                    modifier = Modifier.size(40.dp).padding(top = 12.dp)
                )
                IconButton(onClick = { cartViewModel.onCartButtonClick() }) {
                    Image(
                        painter = org.jetbrains.compose.resources.painterResource(Res.drawable.cross),
                        contentDescription = "Cart",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Divider(color = BackgroundColorBlue, thickness = 2.dp)
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cartItems) { item ->
                    CartItemView(item = item,
                        onRemoveClick = { cartViewModel.removeItemFromCart(it) })
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.White),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { cartViewModel.buyAllItems() },
                colors = ButtonDefaults.buttonColors(backgroundColor = BackgroundColorBlue),
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = "Buy All")
            }
        }
    }
}

@Composable
fun CartItemView(item: CartItem, onRemoveClick: (CartItem) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp).background(Color(LightGray.value))
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.name, style = MaterialTheme.typography.body1)
            Text(text = "${item.price} x ${item.quantity}", style = MaterialTheme.typography.body2)
        }
        Spacer(modifier = Modifier.padding(start = 4.dp))
        Button(
            onClick = { onRemoveClick(item) },
            colors = ButtonDefaults.buttonColors(backgroundColor = BackgroundColorBlue)
        ) {
            Text(text = "Remove", style = MaterialTheme.typography.body2)
        }
    }
}