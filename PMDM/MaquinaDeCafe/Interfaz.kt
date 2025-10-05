package MaquinaDeCafe

open class Interfaz {
    open fun mostrarMensaje(mensaje: String) {
        print(mensaje)
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

    open fun mostrarError(mensaje: String) {
        mostrarMensaje("ERROR: $mensaje")
    }
}