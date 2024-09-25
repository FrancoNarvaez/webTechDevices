package um.edu.ar.ui.scaffold

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import um.edu.ar.ui.cartshopp.CartViewModel
import um.edu.ar.ui.product.ProductViewModel
import um.edu.ar.ui.theme.*
import webtechdevices.composeapp.generated.resources.Res
import webtechdevices.composeapp.generated.resources.icons8_back_96
import webtechdevices.composeapp.generated.resources.icons8_shopping_cart_48

@Composable
fun Toolbar() {
    val productViewModel: ProductViewModel = viewModel()
    val cartViewModel: CartViewModel = viewModel()


    TopAppBar(
        title = { Text(text = "Web Tech Devices", color = Color(MegaLightBlue.value)) },
        backgroundColor = Color(BackgoundLightBlue.value),
        actions = {
            IconButton(onClick = { productViewModel.onBackButtonClick() }) {
                Image(
                    painter = org.jetbrains.compose.resources.painterResource(Res.drawable.icons8_back_96),
                    contentDescription = "Back"
                )
            }
            IconButton(onClick = { cartViewModel.onCartButtonClick() }) {
                Image(
                    painter = org.jetbrains.compose.resources.painterResource(Res.drawable.icons8_shopping_cart_48),
                    contentDescription = "Cart"
                )
            }
        }
    )
}