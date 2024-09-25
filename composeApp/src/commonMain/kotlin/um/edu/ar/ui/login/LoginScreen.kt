package um.edu.ar.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import org.jetbrains.compose.resources.painterResource
import um.edu.ar.ui.theme.BackgoundLightBlue
import um.edu.ar.ui.theme.BackgroundColorBlue
import um.edu.ar.ui.theme.BlueLinksColor
import um.edu.ar.ui.theme.DarkBlue
import um.edu.ar.ui.theme.DisabledColor
import um.edu.ar.ui.theme.LightBlue
import um.edu.ar.ui.theme.MegaLightBlue
import webtechdevices.composeapp.generated.resources.Res
import webtechdevices.composeapp.generated.resources.logoappremove3

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    Box(
        Modifier.fillMaxSize().background(Color(BackgroundColorBlue.value))
    ) {
        Title(Modifier.align(Alignment.TopCenter).padding(top = 16.dp))
        LoginContent(viewModel)
    }
}

@Composable
fun LoginContent(viewModel: LoginViewModel) {
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
            Modifier.padding(60.dp, 150.dp).shadow(
                6.dp,
                shape = MaterialTheme.shapes.large,
                ambientColor = Color.Black,
            )
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center)
                    .background(Color(BackgoundLightBlue.value)).fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LogoImageCompany()
                NameCompany()
                FieldsToComplete(viewModel)
                Links(Modifier.align(Alignment.End))
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
        painter = painterResource(Res.drawable.logoappremove3),
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
        modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
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
fun Links(modifier: Modifier) {
    Column(
        modifier = modifier.padding(end = 3.dp),
    ) {
        ForgotPasswordLink()
        Spacer(modifier = modifier.padding(2.dp))
        CreateNewAccountLink()
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
fun CreateNewAccountLink() {
    Text("Create new account",
        style = MaterialTheme.typography.body1,
        color = Color(BlueLinksColor.value),
        modifier = Modifier.clickable { })
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



