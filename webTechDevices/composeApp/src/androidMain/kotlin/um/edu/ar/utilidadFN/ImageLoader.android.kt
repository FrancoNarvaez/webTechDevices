package um.edu.ar.utilidadFN

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import um.edu.ar.R

@Composable
actual fun loadImage(resourceName: String): Painter {
    val resourceId = when (resourceName) {
        "icons8_shopping_cart_48" -> R.drawable.icons8_shopping_cart_48
        "icons8_close_window_48" -> R.drawable.cross
        "icons8_logo_app" -> R.drawable.logoappremove3
        "icons8_back_arrow_48" -> R.drawable.icons8_back_96
        else -> throw IllegalArgumentException("Unknown resource name")
    }
    return painterResource(id = resourceId)
}