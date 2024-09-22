package um.edu.ar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import um.edu.ar.ui.login.ui.LoginScreen
import um.edu.ar.ui.login.ui.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val loginViewModel: LoginViewModel = viewModel()

            // Setup NavHost
            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    LoginScreen(viewModel = loginViewModel)

                    LaunchedEffect(Unit) {
                        loginViewModel.navigationState.collect { state ->
                            if (state == "main") {
                                navController.navigate("main") {
                                    popUpTo("login") { inclusive = true } // Remove login from back stack
                                }
                            }
                        }
                    }
                }
                composable("main") {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Main Screen")
    }
}
