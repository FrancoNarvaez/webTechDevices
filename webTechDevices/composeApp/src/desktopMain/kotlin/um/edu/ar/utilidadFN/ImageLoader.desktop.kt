package um.edu.ar.utilidadFN

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

@Composable
actual fun loadImage(resourceName: String): Painter {
    return painterResource(resourceName)
}