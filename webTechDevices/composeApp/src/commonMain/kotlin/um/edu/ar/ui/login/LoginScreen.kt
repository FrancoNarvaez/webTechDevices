package um.edu.ar.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import um.edu.ar.ui.theme.*
import um.edu.ar.utilidadFN.loadImage
import um.edu.ar.viewmodels.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel, onNavigateToRegister: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(BackgroundColorBlue.value)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Title(Modifier)
        }
        item {
            LoginContent(viewModel, onNavigateToRegister)
        }
    }
}

@Composable
fun LoginContent(viewModel: LoginViewModel, onNavigateToRegister: () -> Unit) {
    val loginEnabled = viewModel.loginEnabled.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(
            Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Box(
            Modifier
                .fillMaxSize()
                .padding(top = 45.dp, start = 32.dp, end = 32.dp, bottom = 32.dp)
                .shadow(6.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LogoImageCompany()
                NameCompany()
                FieldsToComplete(viewModel)
                Links(Modifier.align(Alignment.End), onNavigateToRegister)
                LoginButton(loginEnabled) {
                    coroutineScope.launch {
                        viewModel.onLoginSelected()
                    }
                }
            }
        }
    }
}

@Composable
fun Title(modifier: Modifier) {
    Text(
        "Login",
        style = MaterialTheme.typography.h1,
        color = Color(LightBlue.value),
        modifier = modifier.padding(start = 16.dp)
    )
}

@Composable
fun LogoImageCompany() {
    Image(
        painter = loadImage("icons8_logo_app"),
        contentDescription = "Header Image",
        Modifier.size(130.dp)
    )
}

@Composable
fun NameCompany() {
    Text(
        "Web Tech Devices", style = MaterialTheme.typography.h4, color = Color(LightBlue.value)
    )
}

@Composable
fun FieldsToComplete(viewModel: LoginViewModel) {
    val email = viewModel.email.collectAsState().value
    val password = viewModel.password.collectAsState().value

    Column(
        modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        EmailField(email) { viewModel.onLoginChanged(it, password) }
        PasswordField(password) { viewModel.onLoginChanged(email, it) }
    }
}

@Composable
fun EmailField(email: String, onTextFieldChange: (String) -> Unit) {
    TextField(value = email,
        onValueChange = { onTextFieldChange(it) },
        placeholder = { Text("Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(MegaLightBlue.value),
            focusedIndicatorColor = Color(DarkBlue.value),
        )
    )
}

@Composable
fun PasswordField(password: String, onTextFieldChange: (String) -> Unit) {
    TextField(value = password,
        onValueChange = { onTextFieldChange(it) },
        placeholder = { Text("Password") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(MegaLightBlue.value),
            focusedIndicatorColor = Color(DarkBlue.value),
        )
    )
}

@Composable
fun Links(modifier: Modifier, onNavigateToRegister: () -> Unit) {
    Column(
        modifier = modifier.padding(end = 3.dp),
    ) {
        ForgotPasswordLink()
        Spacer(modifier = modifier.padding(2.dp))
        CreateNewAccountLink(onNavigateToRegister)
    }
}

@Composable
fun ForgotPasswordLink() {
    Text("Forgot your password?",
        style = MaterialTheme.typography.body1,
        color = Color(BlueLinksColor.value),
        modifier = Modifier.clickable { })
}

@Composable
fun CreateNewAccountLink(onNavigateToRegister: () -> Unit) {
    Text(
        "Create new account",
        style = MaterialTheme.typography.body1,
        color = Color(BlueLinksColor.value),
        modifier = Modifier.clickable { onNavigateToRegister() }
    )
}

@Composable
fun LoginButton(loginEnabled: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier.fillMaxWidth().height(125.dp).padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(DarkBlue.value),
            disabledBackgroundColor = Color(DisabledColor.value),
            contentColor = Color.White,
            disabledContentColor = Color(DarkBlue.value)
        ),
        enabled = loginEnabled
    ) {
        Text("Login")
    }
}