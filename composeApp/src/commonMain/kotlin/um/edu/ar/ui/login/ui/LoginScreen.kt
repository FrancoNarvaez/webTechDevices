package um.edu.ar.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import um.edu.ar.ui.theme.BackgoundLightBlue
import um.edu.ar.ui.theme.BackgroundColorBlue
import um.edu.ar.ui.theme.DarkBlue
import um.edu.ar.ui.theme.LightBlue
import um.edu.ar.ui.theme.MegaLightBlue
import webtechdevices.composeapp.generated.resources.Res
import webtechdevices.composeapp.generated.resources.logoappremove3

@Composable
fun LoginScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(BackgroundColorBlue.value))
    ) {
        Title(Modifier.align(Alignment.TopCenter).padding(top = 16.dp))
        LoginContent()
    }
}


@Composable
fun LoginContent() {
    Box(
        Modifier
            .padding(60.dp, 150.dp)
            .shadow(
                6.dp,
                shape = MaterialTheme.shapes.large,
                ambientColor = Color.Black,
                clip = false
            )
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .background(Color(BackgoundLightBlue.value))
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoImageCompany()
            Spacer(modifier = Modifier.padding(8.dp))
            NameCompany()
            Spacer(modifier = Modifier.padding(8.dp))
            FieldsToComplete()
            Spacer(modifier = Modifier.padding(8.dp))
            ForgotPasswordLink()
            Spacer(modifier = Modifier.padding(8.dp))
            LoginButton()
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
        "Web Tech Devices",
        style = MaterialTheme.typography.h4,
        color = Color(LightBlue.value)
    )
}

@Composable
fun FieldsToComplete() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        EmailField()
        PasswordField()
    }
}

@Composable
fun EmailField() {
    TextField(
        value = "",
        onValueChange = { },
        modifier = Modifier.fillMaxWidth(),
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
fun PasswordField() {
    TextField(
        value = "",
        onValueChange = { },
        modifier = Modifier.fillMaxWidth(),
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
fun ForgotPasswordLink() {
    Text(
        "Forgot your password?",
        style = MaterialTheme.typography.body1,
        color = Color(LightBlue.value)
    )
}

@Composable
fun LoginButton() {

}



