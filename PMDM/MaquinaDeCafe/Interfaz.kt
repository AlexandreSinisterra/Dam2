package MaquinaDeCafe

open class Interfaz {
    open fun mostrarMensaje(mensaje: String) {
        print(mensaje)
    }

    open fun pedirTipoCafe(): Cafe {
        var tipocafe: Cafe = Americano()
        var comprobacion: Boolean

        do {
            comprobacion = true
            mostrarMensaje("\nCafes disponibles:\n ")
            mostrarMensaje("\n1-Americano \n")
            mostrarMensaje("\n2-Africano \n")
            mostrarMensaje("\n3-capuchino \n")

            var tipo = readLine()?.toInt()
            when (tipo) {
                1 -> {mostrarMensaje("\nPerfecta eleccion\n")
                    tipocafe = Americano()
                }
                2 -> {mostrarMensaje("\nPerfecta eleccion\n")
                    tipocafe = Africano()
                }
                3 -> {mostrarMensaje("\nPerfecta eleccion\n")
                    tipocafe = Capuchino()
                }
                else -> {comprobacion = false
                    mostrarError("\nPor favor introdruzca un numero valido\n")
                }
            }
        } while (!comprobacion)
        return tipocafe
    }

    open fun mostrarError(mensaje: String) {
        mostrarMensaje("ERROR: $mensaje")
    }
}