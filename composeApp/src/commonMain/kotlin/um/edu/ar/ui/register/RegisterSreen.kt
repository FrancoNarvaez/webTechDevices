package um.edu.ar.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
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
import um.edu.ar.ui.login.LogoImageCompany
import um.edu.ar.ui.login.NameCompany
import um.edu.ar.ui.theme.BackgroundColorBlue
import um.edu.ar.ui.theme.BlueLinksColor
import um.edu.ar.ui.theme.DarkBlue
import um.edu.ar.ui.theme.DisabledColor
import um.edu.ar.ui.theme.LightBlue
import um.edu.ar.ui.theme.MegaLightBlue

@Composable
fun RegisterScreen(viewModel: RegisterViewModel, onNavigateToLogin: () -> Unit) {
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
            RegisterContent(viewModel, onNavigateToLogin)
        }
    }
}

@Composable
fun RegisterContent(viewModel: RegisterViewModel, onNavigateToLogin: () -> Unit) {
    val registerEnabled = viewModel.registerEnabled.collectAsState().value
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
                RegisterFields(viewModel)
                Links(Modifier.align(Alignment.End), onNavigateToLogin)
                RegisterButton(registerEnabled) {
                    coroutineScope.launch {
                        onNavigateToLogin()
                    }
                }
            }
        }
    }
}

@Composable
fun Title(modifier: Modifier) {
    Text(
        "Sing Up",
        style = MaterialTheme.typography.h1,
        color = Color(LightBlue.value),
        modifier = modifier.padding(start = 16.dp)
    )
}

@Composable
fun RegisterFields(viewModel: RegisterViewModel) {
    val email = viewModel.email.collectAsState().value
    val password = viewModel.password.collectAsState().value
    val confirmPassword = viewModel.confirmPassword.collectAsState().value

    Column(
        modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        EmailField(email) { viewModel.onRegisterChanged(it, password, confirmPassword) }
        PasswordField(password) { viewModel.onRegisterChanged(email, it, confirmPassword) }
        ConfirmPasswordField(confirmPassword) { viewModel.onRegisterChanged(email, password, it) }
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
fun ConfirmPasswordField(confirmPassword: String, onTextFieldChange: (String) -> Unit) {
    TextField(value = confirmPassword,
        onValueChange = { onTextFieldChange(it) },
        placeholder = { Text("Confirm Password") },
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
fun Links(modifier: Modifier, onNavigateToLogin: () -> Unit) {
    Column(
        modifier = modifier.padding(end = 3.dp),
    ) {
        IHaveAlreadyAnAccount(onNavigateToLogin)
    }
}

@Composable
fun IHaveAlreadyAnAccount(onNavigateToLogin: () -> Unit) {
    Text(
        "I have already an account",
        style = MaterialTheme.typography.body1,
        color = Color(BlueLinksColor.value),
        modifier = Modifier.clickable { onNavigateToLogin() })
}

@Composable
fun RegisterButton(registerEnabled: Boolean, onRegisterSelected: () -> Unit) {
    Button(
        onClick = { onRegisterSelected() },
        modifier = Modifier.fillMaxWidth().height(125.dp).padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(DarkBlue.value),
            disabledBackgroundColor = Color(DisabledColor.value),
            contentColor = Color.White,
            disabledContentColor = Color(DarkBlue.value)
        ),
        enabled = registerEnabled
    ) {
        Text("Register")
    }
}