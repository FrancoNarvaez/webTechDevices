package um.edu.ar.ui.cartshopp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import um.edu.ar.clases.CartItem
import um.edu.ar.ui.theme.BackgroundColorBlue
import um.edu.ar.ui.theme.LightGray
import um.edu.ar.viewmodels.CartViewModel
import androidx.compose.ui.Alignment
import um.edu.ar.utilidadFN.loadImage

@Composable
fun CartShoppScreen(cartViewModel: CartViewModel, modifier: Modifier = Modifier) {
    val cartItems = cartViewModel.cartItems.collectAsState().value
    val isCartVisible = cartViewModel.isCartVisible.collectAsState().value
    val coroutineScope = rememberCoroutineScope()

    if (isCartVisible) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            Column(
                modifier = modifier.fillMaxSize().align(Alignment.TopCenter),
            ) {
                CartHeader(cartViewModel)
                Divider(color = BackgroundColorBlue, thickness = 2.dp)
                Spacer(modifier = modifier.height(16.dp))
                LazyColumn(modifier = modifier.weight(1f)) {
                    items(cartItems) { item: CartItem ->
                        CartItemView(item = item, onRemoveClick = { cartViewModel.removeItemFromCart(it) })
                    }
                }
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.White),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { coroutineScope.launch { cartViewModel.buyAllItems() } },
                    colors = ButtonDefaults.buttonColors(backgroundColor = BackgroundColorBlue),
                    modifier = modifier.align(Alignment.CenterVertically)
                ) {
                    Text(text = "Buy All")
                }
            }
        }
    }
}

@Composable
fun CartHeader(viewModel: CartViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            "Shopping Cart",
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .weight(1f)
                .padding(top = 14.dp)
        )
        Image(
            painter = loadImage("icons8_shopping_cart_48"),
            contentDescription = "Cart",
            modifier = Modifier
                .size(40.dp)
                .padding(top = 12.dp)
        )
        IconButton(onClick = { viewModel.onCartButtonClick() }) {
            Image(
                painter = loadImage("icons8_close_window_48"),
                contentDescription = "Close",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun CartItemView(item: CartItem, onRemoveClick: (CartItem) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(LightGray.value))
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