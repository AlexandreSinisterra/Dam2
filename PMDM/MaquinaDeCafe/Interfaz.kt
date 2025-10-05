package MaquinaDeCafe

import MaquinaDeCafe.MaquinadeCafe.interfaz

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

    open fun pedirPago(cafe: Cafe): Int{
        mostrarMensaje("\nEl precio del "+cafe.nombre+" es de "+cafe.precio+"\n")
        mostrarMensaje("\nProcesando pago")
        repeat (3) {
            Thread.sleep(1000)
            interfaz.mostrarMensaje(".")
        }
        mostrarMensaje("\nMuchas gracias por su compra\n")
        return cafe.precio
    }

    open fun pedirAzucar(cafe: Cafe): Int{
        var cantidad = 0
        var comprobacion: Boolean
        do {
            comprobacion = true
            mostrarMensaje("\nCuanto azucar quieres?\n")
            mostrarMensaje("\nEscribe un numero del 1 al 10 \n")
            var cantidad = readLine()?.toInt()
            if ((cantidad!! >11) or (cantidad < 0)){
                comprobacion = false
            }
        } while (!comprobacion)
        mostrarMensaje("\nDe acuerdo\n")
        return cantidad
    }

    open fun mostrarError(mensaje: String) {
        mostrarMensaje("ERROR: $mensaje")
    }
}