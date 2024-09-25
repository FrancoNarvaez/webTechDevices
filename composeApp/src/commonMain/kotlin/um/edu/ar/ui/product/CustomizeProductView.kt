package um.edu.ar.ui.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import um.edu.ar.clases.Adicional
import um.edu.ar.clases.Device
import um.edu.ar.clases.OpcionPersonalizacion
import um.edu.ar.ui.theme.BackgroundColorBlue

@Composable
fun CustomizeProductScreen(
    device: Device,
    finalPrice: Double,
    selectedCustomizations: Map<String, OpcionPersonalizacion>,
    onCustomizationChange: (String, OpcionPersonalizacion) -> Unit,
    selectedAddons: List<Adicional>,
    onAddonToggle: (Adicional) -> Unit,
    onPurchaseClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Mostrar información del dispositivo
        Text(text = device.nombre, style = MaterialTheme.typography.h5)
        Text(text = device.descripcion)
        Text(text = "Precio base: ${device.precioBase}")

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar personalizaciones
        Text(text = "Personalizaciones")
        device.personalizaciones.forEach { personalizacion ->
            Text(text = personalizacion.nombre)
            personalizacion.opciones.forEach { opcion ->
                RadioButton(selected = selectedCustomizations[personalizacion.nombre] == opcion,
                    onClick = { onCustomizationChange(personalizacion.nombre, opcion) })
                Text(text = "${opcion.nombre} (+${opcion.precioAdicional})")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar adicionales
        Text(text = "Adicionales")
        device.adicionales.forEach { addon ->
            Row {
                Checkbox(checked = selectedAddons.contains(addon),
                    onCheckedChange = { onAddonToggle(addon) })
                Text(text = "${addon.nombre} (${addon.precio})")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar precio final
        Text(text = "Precio final: $finalPrice")

        // Botones de compra y cancelar
        Row {
            Button(onClick = onPurchaseClick, colors = ButtonDefaults.buttonColors(backgroundColor = BackgroundColorBlue)) {
                Text("Comprar")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onCancelClick, colors = ButtonDefaults.buttonColors(backgroundColor = BackgroundColorBlue)) {
                Text("Cancelar")
            }
        }
    }
}