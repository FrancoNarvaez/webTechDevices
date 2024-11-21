package um.edu.ar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import um.edu.ar.clases.CartItem
import um.edu.ar.ui.cartshopp.CartShoppScreen
import um.edu.ar.ui.cartshopp.CartViewModel
import um.edu.ar.ui.login.LoginScreen
import um.edu.ar.ui.login.LoginViewModel
import um.edu.ar.ui.product.CustomizeProductScreen
import um.edu.ar.ui.product.ProductViewModel
import um.edu.ar.ui.product.ProductsScreen
import um.edu.ar.ui.register.RegisterScreen
import um.edu.ar.ui.register.RegisterViewModel
import um.edu.ar.ui.scaffold.BottomNavigationBar
import um.edu.ar.ui.scaffold.TopAppBar
import um.edu.ar.ui.theme.BackgroundColorBlue


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val loginViewModel: LoginViewModel = viewModel()
            val productViewModel: ProductViewModel = viewModel()
            val cartViewModel: CartViewModel = viewModel()
            val registerViewModel: RegisterViewModel = viewModel()

            Scaffold(topBar = { TopAppBar() },
                bottomBar = {BottomNavigationBar(navController)},
                content = {
                Box(modifier = Modifier.fillMaxSize()) {
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            LoginScreen(viewModel = loginViewModel,
                                onNavigateToRegister = { navController.navigate("register") })
                        }
                        composable("ProductListView") {
                            productViewModel.loadProducts()
                            ProductsScreen(viewModel = productViewModel)
                        }
                        composable("CustomizeProductView") {
                            val selectedProduct =
                                productViewModel.selectedProduct.collectAsState().value

                            if (selectedProduct != null) {
                                val finalPrice by productViewModel.finalPrice.collectAsState()
                                val selectedAddons by productViewModel.selectedAddons.collectAsState()
                                val selectedCustomizations by productViewModel.selectedCustomizations.collectAsState()

                                CustomizeProductScreen(
                                    device = selectedProduct,
                                    finalPrice = finalPrice,
                                    onAddonToggle = { addon -> productViewModel.onAddonToggle(addon) },
                                    onCancelClick = { productViewModel.onCancelCustomization() },
                                    onCustomizationChange = { customization, option ->
                                        productViewModel.onCustomizationChange(
                                            customization, option
                                        )
                                    },
                                    onPurchaseClick = {
                                        cartViewModel.addItemToCart(
                                            CartItem(
                                                id = selectedProduct.id,
                                                name = selectedProduct.nombre,
                                                price = finalPrice,
                                                quantity = 1
                                            )
                                        )
                                    },
                                    onAddClick = {
                                        cartViewModel.addItemToCart(
                                            CartItem(
                                                id = selectedProduct.id,
                                                name = selectedProduct.nombre,
                                                price = finalPrice,
                                                quantity = 1
                                            )
                                        )
                                    },
                                    selectedAddons = selectedAddons,
                                    selectedCustomizations = selectedCustomizations
                                )
                            }
                        }
                        composable("register") {
                            RegisterScreen(viewModel = registerViewModel,
                                onNavigateToLogin = { navController.navigate("login") })
                        }
                    }

                    val isCartVisible by cartViewModel.isCartVisible.collectAsState()
                    if (isCartVisible) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .height(500.dp)
                                .width(250.dp)
                                .padding(4.dp)
                                .background(Color.White)
                                .border(3.dp, BackgroundColorBlue)

                        ) {
                            CartShoppScreen(
                                Modifier
                                    .align(Alignment.TopCenter)
                                    .padding(top = 4.dp)
                            )
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
    loginViewModel: LoginViewModel, productViewModel: ProductViewModel, navController: NavController
) {
    val loginState by loginViewModel.navigationState.collectAsState()
    LaunchedEffect(loginState) {
        snapshotFlow { loginState }.collect { state ->
            if (state == "ProductListView") {
                navController.navigate("ProductListView") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }
    }

    val productState by productViewModel.navigationState.collectAsState()
    LaunchedEffect(productState) {
        snapshotFlow { productState }.collect { state ->
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