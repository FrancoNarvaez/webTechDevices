package um.edu.ar.utilidadFN

import kotlinx.coroutines.delay
import um.edu.ar.clases.Adicional
import um.edu.ar.clases.Device
import um.edu.ar.clases.OpcionPersonalizacion

suspend fun sendPurchaseRequest(
    product: Device?,
    customizations: Map<String, OpcionPersonalizacion>,
    addons: List<Adicional>
) {
    // Simulate network delay
    delay(2000)
    // Simulate a successful request
    println("Purchase request sent for product: ${product?.nombre}")
}