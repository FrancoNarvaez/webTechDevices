package um.edu.ar.ui.scaffold


import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import um.edu.ar.ui.theme.*


@Composable
fun Toolbar(){
    TopAppBar(title = { Text(text = "Web Tech Devices", color = Color(MegaLightBlue.value)) }, backgroundColor = Color(BackgoundLightBlue.value) )
}

