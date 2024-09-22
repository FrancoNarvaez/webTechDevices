package um.edu.ar

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import um.edu.ar.ui.login.ui.LoginScreen
import um.edu.ar.ui.login.ui.LoginViewModel


@Composable
@Preview
fun App() {
    LoginScreen(LoginViewModel())
}