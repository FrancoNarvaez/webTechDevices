package um.edu.ar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import um.edu.ar.ui.login.ui.LoginScreen
import um.edu.ar.ui.login.ui.LoginViewModel
import um.edu.ar.ui.mainPage.CustomizeProductScreen
import um.edu.ar.ui.mainPage.ProductViewModel
import um.edu.ar.ui.mainPage.ProductsScreen
import um.edu.ar.ui.scaffold.Toolbar

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val productViewModel: ProductViewModel = viewModel()

            Scaffold(topBar = { Toolbar() }, content = {
                NavHost(navController = navController, startDestination = "login") {

                    // Pantalla de login
                    composable("login") {
                        val loginViewModel = LoginViewModel()
                        LoginScreen(viewModel = loginViewModel)

                        LaunchedEffect(Unit) {
                            loginViewModel.navigationState.collect { state ->
                                if (state == "ProductListView") {
                                    navController.navigate("ProductListView") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                }
                            }
                        }
                    }

                    // Pantalla de lista de productos
                    composable("ProductListView") {
                        productViewModel.loadProducts()
                        ProductsScreen(viewModel = productViewModel)

                        val navigationState = productViewModel.navigationState.collectAsState().value
                        LaunchedEffect(navigationState) {
                            snapshotFlow { navigationState }
                                .collect { state ->
                                    if (state == "CustomizeProductView") {
                                        navController.navigate("CustomizeProductView")
                                    }
                                }
                        }
                    }

                    // Pantalla de personalización del producto
                    composable("CustomizeProductView") {
                        val selectedProduct = productViewModel.selectedProduct.collectAsState().value
                        if (selectedProduct != null) {
                            CustomizeProductScreen(
                                device = selectedProduct,
                                finalPrice = productViewModel.finalPrice.collectAsState().value,
                                onAddonToggle = { addon ->
                                    productViewModel.onAddonToggle(addon)
                                },
                                onCancelClick = {
                                    // Acción de cancelar
                                },
                                onCustomizationChange = { customization, option ->
                                    productViewModel.onCustomizationChange(customization, option)
                                },
                                onPurchaseClick = {
                                    // Acción de compra
                                },
                                selectedAddons = productViewModel.selectedAddons.collectAsState().value,
                                selectedCustomizations = productViewModel.selectedCustomizations.collectAsState().value
                            )
                        } else {
                            Text("No se ha seleccionado ningún producto")
                        }
                    }
                }
            })
        }
    }
}
