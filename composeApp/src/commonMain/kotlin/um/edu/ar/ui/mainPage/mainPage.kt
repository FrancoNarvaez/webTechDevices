package um.edu.ar.ui.mainPage

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import um.edu.ar.clases.Device
import um.edu.ar.clases.OpcionPersonalizacion
import um.edu.ar.clases.Adicional



@Composable
fun CustomizeProductView(
    device: Device,
    selectedCustomizations: Map<String, OpcionPersonalizacion>,
    onCustomizationChange: (String, OpcionPersonalizacion) -> Unit,
    selectedAddons: List<Adicional>,
    onAddonToggle: (Adicional) -> Unit,
    finalPrice: Double,
    onPurchaseClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text(text = device.nombre, style = MaterialTheme.typography.h5)
        Text(text = device.descripcion)
        Text(text = "Precio base: ${device.precioBase}")

        // Características
        device.caracteristicas.forEach { caracteristica ->
            Text(text = "${caracteristica.nombre}: ${caracteristica.descripcion}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Personalizaciones
        Text(text = "Personalizaciones")
        device.personalizaciones.forEach { personalizacion ->
            Text(text = personalizacion.nombre)
            DropdownMenu(
                expanded = false, // Cambiar según la lógica que determines
                onDismissRequest = { /* lógica para cerrar */ },
                content = {
                    personalizacion.opciones.forEach { opcion ->
                        DropdownMenuItem(onClick = { onCustomizationChange(personalizacion.nombre, opcion) }) {
                            Text(text = "${opcion.nombre} (+${opcion.precioAdicional})")
                        }
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Adicionales
        Text(text = "Adicionales")
        device.adicionales.forEach { addon ->
            Row {
                Checkbox(
                    checked = selectedAddons.contains(addon),
                    onCheckedChange = { onAddonToggle(addon) }
                )
                Text(text = "${addon.nombre} (${addon.precio})")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Precio final
        Text(text = "Precio final: $finalPrice")

        // Botones de compra y cancelar
        Row {
            Button(onClick = onPurchaseClick) {
                Text("Comprar")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = onCancelClick) {
                Text("Cancelar")
            }
        }
    }
}
