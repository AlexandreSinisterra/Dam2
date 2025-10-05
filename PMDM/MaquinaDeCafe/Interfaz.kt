package MaquinaDeCafe

open class Interfaz {
    open fun mostrarMensaje(mensaje: String) {
        print(mensaje)
    }

    open fun pedirTipoCafe(): String {
        var tipocafe = "a"
        do {
            var comprobacion = true
            mostrarMensaje("\nCafes disponibles:\n ")
            mostrarMensaje("\n1-Americano \n")
            mostrarMensaje("\n2-Africano \n")
            mostrarMensaje("\n3-capuchino \n")

            var tipo = readLine()?.toInt()
            when (tipo) {
                1 -> {mostrarMensaje("\nPerfecta eleccion\n")
                    tipocafe = "americano"
                }
                2 -> {mostrarMensaje("\nPerfecta eleccion\n")
                    tipocafe = "Africano"
                }
                3 -> {mostrarMensaje("\nPerfecta eleccion\n")
                    tipocafe = "capuchino"
                }
                else -> comprobacion = false
            }
        } while (!comprobacion)
        return tipocafe
    }

    open fun mostrarError(mensaje: String) {
        mostrarMensaje("ERROR: $mensaje")
    }
}