package um.edu.ar.ui


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import um.edu.ar.ui.cartshopp.CartShoppScreen
import um.edu.ar.ui.login.LoginScreen
import um.edu.ar.ui.product.CustomizeProductScreen
import um.edu.ar.ui.product.ProductsScreen
import um.edu.ar.ui.register.RegisterScreen
import um.edu.ar.ui.scaffold.BottomNavigationBar
import um.edu.ar.ui.scaffold.TopAppBar
import um.edu.ar.ui.theme.BackgroundColorBlue
import um.edu.ar.ui.ventas.VentasViewScreen
import um.edu.ar.viewmodels.CartViewModel
import um.edu.ar.viewmodels.LoginViewModel
import um.edu.ar.viewmodels.ProductViewModel
import um.edu.ar.viewmodels.RegisterViewModel
import um.edu.ar.viewmodels.VentasViewModel

@Composable
fun AppContent() {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()
    val productViewModel: ProductViewModel = viewModel()
    val cartViewModel: CartViewModel = viewModel()
    val registerViewModel: RegisterViewModel = viewModel()
    val ventasViewModel: VentasViewModel = viewModel()

    MainScreen(
        navController = navController,
        loginViewModel = loginViewModel,
        productViewModel = productViewModel,
        cartViewModel = cartViewModel,
        registerViewModel = registerViewModel,
        ventasViewModel = ventasViewModel
    )
}

@Composable
fun MainScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    productViewModel: ProductViewModel,
    cartViewModel: CartViewModel,
    registerViewModel: RegisterViewModel,
    ventasViewModel: VentasViewModel
) {
    NavigationEffects(loginViewModel, productViewModel, navController)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (currentRoute == "login") {
        NavigationHost(
            navController = navController,
            loginViewModel = loginViewModel,
            productViewModel = productViewModel,
            cartViewModel = cartViewModel,
            registerViewModel = registerViewModel,
            ventasViewModel = ventasViewModel
        )
    } else {
        Scaffold(
            topBar = { TopAppBar(productViewModel, cartViewModel) },
            bottomBar = { BottomNavigationBar(currentRoute) { route -> navController.navigate(route) } }
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                NavigationHost(
                    navController = navController,
                    loginViewModel = loginViewModel,
                    productViewModel = productViewModel,
                    cartViewModel = cartViewModel,
                    registerViewModel = registerViewModel,
                    ventasViewModel = ventasViewModel
                )

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
                            cartViewModel,
                            Modifier
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    productViewModel: ProductViewModel,
    cartViewModel: CartViewModel,
    registerViewModel: RegisterViewModel,
    ventasViewModel: VentasViewModel
) {
    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(loginViewModel, onNavigateToRegister = { navController.navigate("register") })
        }
        composable("ProductListView") {
            ProductsScreen(productViewModel)
        }
        composable("customizeProduct") {
            val selectedProduct by productViewModel.selectedProduct.collectAsState()
            selectedProduct?.let { product ->
                CustomizeProductScreen(
                    device = product,
                    finalPrice = productViewModel.finalPrice.collectAsState().value,
                    selectedCustomizations = productViewModel.selectedCustomizations.collectAsState().value,
                    onCustomizationChange = productViewModel::onCustomizationChange,
                    selectedAddons = productViewModel.selectedAddons.collectAsState().value,
                    onAddonToggle = { addon -> productViewModel.onAddonToggle(addon) },
                    onPurchaseClick = { productViewModel.onPurchaseClick(cartViewModel) },
                    onAddClick = { productViewModel.onAddClick(cartViewModel) },
                    onCancelClick = productViewModel::onCancelCustomization
                )
            }
        }
        composable("register") {
            RegisterScreen(registerViewModel, onNavigateToLogin = { navController.navigate("login") })
        }
        composable("VentasView") {
            VentasViewScreen(ventasViewModel)
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
                navController.navigate("customizeProduct")
            } else if (state == "ProductListView") {
                navController.navigate("ProductListView") {
                    popUpTo("customizeProduct") { inclusive = true }
                }
            }
        }
    }
}

