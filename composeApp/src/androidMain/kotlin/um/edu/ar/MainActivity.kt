package um.edu.ar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import um.edu.ar.clases.Device
import um.edu.ar.ui.login.ui.LoginScreen
import um.edu.ar.ui.login.ui.LoginViewModel
import um.edu.ar.ui.mainPage.CustomizeProductView
import um.edu.ar.ui.mainPage.ProductListView
import um.edu.ar.ui.mainPage.ProductViewModel
import um.edu.ar.ui.scaffold.Toolbar

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(topBar = { Toolbar() }, content = {
                NavHost(navController = navController, startDestination = "login") {
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

                    composable("ProductListView") {
                        ProductListView(products = listOf(
                            Device(
                                id = 1,
                                codigo = "001",
                                nombre = "Device 1",
                                descripcion = "Description 1",
                                precioBase = 100.0,
                                moneda = "USD",
                                caracteristicas = listOf(),
                                personalizaciones = listOf(),
                                adicionales = listOf()
                            )
                        ), // Replace with actual product list
                            onSelectProduct = { device ->
                                // Handle product selection
                            })
                        val productViewModel = ProductViewModel()
                        LaunchedEffect(Unit) {
                            productViewModel.navigationState.collect { state ->
                                if (state == "CustomizeProductView") {
                                    navController.navigate("CustomizeProductView") {
                                        popUpTo("ProductListView") { inclusive = true }
                                    }
                                }
                            }
                        }
                    }

                        composable("CustomizeProductView") {
                            CustomizeProductView(device = Device(
                                id = 1,
                                codigo = "001",
                                nombre = "Device 1",
                                descripcion = "Description 1",
                                precioBase = 100.0,
                                moneda = "USD",
                                caracteristicas = listOf(),
                                personalizaciones = listOf(),
                                adicionales = listOf()
                            ), // Pass the actual device
                                finalPrice = 150.0, // Replace with actual price
                                onAddonToggle = { addon ->
                                    // Handle addon toggle
                                }, onCancelClick = {
                                    // Handle cancel click
                                }, onCustomizationChange = { customization, option ->
                                    // Handle customization change
                                }, onPurchaseClick = {
                                    // Handle purchase click
                                }, selectedAddons = listOf(), // Replace with actual selected addons
                                selectedCustomizations = mapOf() // Replace with actual selected customizations
                            )
                        }
                    }
                })
            }
        }
    }