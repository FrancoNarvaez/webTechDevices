package um.edu.ar.ui.scaffold

import androidx.compose.foundation.Image
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import um.edu.ar.viewmodels.CartViewModel
import um.edu.ar.viewmodels.ProductViewModel
import um.edu.ar.ui.theme.*
import um.edu.ar.utilidadFN.loadImage

@Composable
fun TopAppBar(productViewModel: ProductViewModel, cartViewModel: CartViewModel) {
    TopAppBar(
        title = { Text(text = "Web Tech Devices", color = Color(MegaLightBlue.value)) },
        backgroundColor = Color(BackgoundLightBlue.value),
        actions = {
            IconButton(onClick = { cartViewModel.onCartButtonClick() }) {
                Image(
                    painter = loadImage("icons8_shopping_cart_48"),
                    contentDescription = "Cart"
                )
            }

            IconButton(onClick = { productViewModel.onBackButtonClick() }) {
                Image(
                    painter = loadImage("icons8_back_arrow_48"),
                    contentDescription = "Back"
                )
            }
        }
    )
}

@Composable
fun BottomNavigationBar(currentRoute: String?, navigate: (String) -> Unit) {
    BottomNavigation(
        backgroundColor = Color(BackgoundLightBlue.value),
        contentColor = Color(BlueLinksColor.value)
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Products") },
            selected = currentRoute == "ProductListView",
            onClick = { navigate("ProductListView") },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "Bill") },
            selected = currentRoute == "VentasView",
            onClick = { navigate("VentasView") },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White
        )
    }
}