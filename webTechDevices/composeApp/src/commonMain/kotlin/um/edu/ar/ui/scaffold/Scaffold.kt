package um.edu.ar.ui.scaffold

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import um.edu.ar.ui.cartshopp.CartViewModel
import um.edu.ar.ui.product.ProductViewModel
import um.edu.ar.ui.theme.*
import webtechdevices.composeapp.generated.resources.Res
import webtechdevices.composeapp.generated.resources.icons8_back_96
import webtechdevices.composeapp.generated.resources.icons8_shopping_cart_48

@Composable
fun TopAppBar() {
    val productViewModel: ProductViewModel = viewModel()
    val cartViewModel: CartViewModel = viewModel()


    TopAppBar(
        title = { Text(text = "Web Tech Devices", color = Color(MegaLightBlue.value)) },
        backgroundColor = Color(BackgoundLightBlue.value),
        actions = {
            IconButton(onClick = { cartViewModel.onCartButtonClick() }) {
                Image(
                    painter = org.jetbrains.compose.resources.painterResource(Res.drawable.icons8_shopping_cart_48),
                    contentDescription = "Cart"
                )
            }

            IconButton(onClick = { productViewModel.onBackButtonClick() }) {
                Image(
                    painter = org.jetbrains.compose.resources.painterResource(Res.drawable.icons8_back_96),
                    contentDescription = "Back"
                )
            }
        }
    )
}

@Composable
fun BottomNavigationBar(currentRoute: String?, navigate: (String) -> Unit) {
    BottomNavigation (
        backgroundColor = Color(BackgoundLightBlue.value),
        contentColor = Color(BlueLinksColor.value)
    ){
        BottomNavigationItem(
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Products") },
            selected = currentRoute == "ProductListView",
            onClick = { navigate("ProductListView") },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Sales") },
            selected = currentRoute == "SalesView",
            onClick = { navigate("SalesView") },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White
        )
    }
}