package um.edu.ar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import um.edu.ar.ui.login.LoginScreen
import um.edu.ar.ui.login.LoginViewModel
import um.edu.ar.ui.product.CustomizeProductScreen
import um.edu.ar.ui.product.ProductViewModel
import um.edu.ar.ui.product.ProductsScreen
import um.edu.ar.ui.cartshopp.CartViewModel
import um.edu.ar.ui.scaffold.Toolbar

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val loginViewModel: LoginViewModel = viewModel()
            val productViewModel: ProductViewModel = viewModel()
            val cartViewModel: CartViewModel = viewModel()

            Scaffold(topBar = { Toolbar() }, content = {
                Box(modifier = Modifier.fillMaxSize()) {
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            LoginScreen(viewModel = loginViewModel)
                        }
                        composable("ProductListView") {
                            productViewModel.loadProducts()
                            ProductsScreen(viewModel = productViewModel)
                        }
                        composable("CustomizeProductView") {
                            val selectedProduct = productViewModel.selectedProduct.collectAsState().value
                            if (selectedProduct != null) {
                                CustomizeProductScreen(
                                    device = selectedProduct,
                                    finalPrice = productViewModel.finalPrice.collectAsState().value,
                                    onAddonToggle = { addon -> productViewModel.onAddonToggle(addon) },
                                    onCancelClick = { productViewModel.onCancelCustomization() },
                                    onCustomizationChange = { customization, option ->
                                        productViewModel.onCustomizationChange(customization, option)
                                    },
                                    onPurchaseClick = { /* Acción de compra */ },
                                    selectedAddons = productViewModel.selectedAddons.collectAsState().value,
                                    selectedCustomizations = productViewModel.selectedCustomizations.collectAsState().value
                                )
                            } else {
                                Text("No se ha seleccionado ningún producto")
                            }
                        }
                    }

                    val isCartVisible by cartViewModel.isCartVisible.collectAsState()
                    if (isCartVisible) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text("Vista de compra")
                        }
                    }

                    NavigationEffects(loginViewModel, productViewModel, navController)
                }
            })
        }
    }
}

@Composable
fun NavigationEffects(
    loginViewModel: LoginViewModel,
    productViewModel: ProductViewModel,
    navController: NavController
) {
    val loginState by loginViewModel.navigationState.collectAsState()
    LaunchedEffect(loginState) {
        snapshotFlow { loginState }
            .collect { state ->
                if (state == "ProductListView") {
                    navController.navigate("ProductListView") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }
    }

    val productState by productViewModel.navigationState.collectAsState()
    LaunchedEffect(productState) {
        snapshotFlow { productState }
            .collect { state ->
                if (state == "CustomizeProductView") {
                    navController.navigate("CustomizeProductView")
                } else if (state == "ProductListView") {
                    navController.navigate("ProductListView") {
                        popUpTo("CustomizeProductView") { inclusive = true }
                    }
                }
            }
    }
}