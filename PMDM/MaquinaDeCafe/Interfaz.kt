package MaquinaDeCafe

open class Interfaz {
    open fun mostrarMensaje(mensaje: String) {
        println(mensaje)
    }

    open fun pedirTipoCafe(): String {
        val cafesValidos = listOf("solo", "leche", "capuchino")
        var tipo: String?
        do {
            mostrarMensaje("¿Qué café deseas? (solo, leche, capuchino): ")
            tipo = readLine()?.lowercase()
            if (tipo !in cafesValidos) {
                mostrarError("Tipo de café no válido.")
            }
        } while (tipo !in cafesValidos)
        return tipo!!
    }

    open fun pedirAzucar(): Boolean {
        mostrarMensaje("¿Quieres azúcar? (s|si / n|no): ")
        val respuesta = readLine()?.lowercase()
        return respuesta == "s" || respuesta == "si"
    }

    open fun mostrarError(mensaje: String) {
        mostrarMensaje("ERROR: $mensaje")
    }

    open fun pedirOrden(): Boolean {
        TODO("Not yet implemented")
    }
}