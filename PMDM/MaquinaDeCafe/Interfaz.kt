package MaquinaDeCafe

open class Interfaz {//funcion para mostrar mensajes
    open fun mostrarMensaje(mensaje: String) {
        print(mensaje)
    }

    open fun pedirTipoCafe(): Cafe {//funcion para pedir el cafe
        var tipocafe: Cafe = Americano()
        var comprobacion: Boolean

        do {
            comprobacion = true
            mostrarMensaje("\nCafes disponibles:\n ")
            mostrarMensaje("\n1-Americano \n")
            mostrarMensaje("\n2-Africano \n")
            mostrarMensaje("\n3-capuchino \n")

            var tipo = readLine()?.toInt()//pasa lo que lee a un numero
            when (tipo) {
                1 -> {mostrarMensaje("\nPerfecta eleccion\n")
                    tipocafe = Americano()//asigna el cafe que tenemos al que pedimos
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
        } while (!comprobacion)//hasta que no se de un numero valido seguira preguntando
        return tipocafe//devuelve la clase Cafe
    }

    open fun pedirPago(cafe: Cafe): Int{//simula un pedido de pago
        mostrarMensaje("\nEl precio del "+cafe.nombre+" es de "+cafe.precio+"\n")
        mostrarMensaje("\nProcesando pago")
        repeat (3) {
            Thread.sleep(1000)
            mostrarMensaje(".")
        }
        mostrarMensaje("\nMuchas gracias por su compra\n")
        return cafe.precio
    }

    open fun pedirAzucar(cafe: Cafe): Int{//pide la cantidad deseada de azucar
        var cantidad: Int = 0
        var comprobacion: Boolean
        do {// funciona como el primer do while
            comprobacion = true
            mostrarMensaje("\nCuanto azucar quieres?\n")
            mostrarMensaje("\nEscribe un numero del 1 al 10 \n")
            val a = readLine()
            cantidad = a!!.toInt()
            /**
             * al poner directamente cantidad = readLine()?.toInt() me daba error, asique tuve que
             * crear una variable nueva y despues igualar cantidad a esta nueva
             */
            if ((cantidad!! >11) or (cantidad < 0)){
                comprobacion = false
            }
        } while (!comprobacion)
        mostrarMensaje("\nDe acuerdo\n")
        return cantidad//devuelve la cantidad de azucar
    }

    open fun mostrarError(mensaje: String) {
        mostrarMensaje("ERROR: $mensaje")//funciona como el mostrar mensaje
    }
}