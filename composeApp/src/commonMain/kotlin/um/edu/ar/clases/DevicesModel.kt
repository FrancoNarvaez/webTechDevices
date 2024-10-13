package um.edu.ar.clases

data class Device(
    val id: Int,
    val codigo: String,
    val nombre: String,
    val descripcion: String,
    val precioBase: Double,
    val moneda: String,
    val caracteristicas: List<Caracteristica>,
    val personalizaciones: List<Personalizacion>,
    val adicionales: List<Adicional>
)

data class Caracteristica(
    val id: Int,
    val nombre: String,
    val descripcion: String
)

data class Personalizacion(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val opciones: List<OpcionPersonalizacion>
)

data class OpcionPersonalizacion(
    val id: Int,
    val codigo: String,
    val nombre: String,
    val descripcion: String,
    val precioAdicional: Double
)

data class Adicional(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val precioGratis: Double // -1 si no tiene promoción
)

data class CartItem(
    val id: Int,
    val name: String,
    val price: Double,
    val quantity: Int
)