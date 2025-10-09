package MaquinaDeCafe
/**
 * Clase que representa la interfaz de usuario de la máquina de café.
 *
 * Se encarga de mostrar mensajes por consola, solicitar interacciones
 * al usuario y validar las entradas recibidas.
 *
 * Todas las funciones están definidas como open para permitir su extensión
 */
open class Interfaz {
    /**
     * Muestra un mensaje por consola.
     *
     * @param mensaje Texto que se imprimirá en la consola.
     */
    open fun mostrarMensaje(mensaje: String) {
        print(mensaje)
    }
    /**
     * Solicita al usuario que elija un tipo de café.
     *
     * Presenta un menú de opciones y valida la entrada.
     * El proceso se repetirá hasta que se ingrese una opción válida.
     *
     * @return Una instancia de la clase [Cafe] correspondiente al tipo seleccionado.
     */
    open fun pedirTipoCafe(): Cafe {
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
    /**
     * Simula el proceso de pago de un café.
     *
     * Muestra el precio, procesa el pago con una pequeña espera
     * (simulada con `Thread.sleep`) y confirma la transacción.
     *
     * @param cafe Objeto [Cafe] del que se extrae el precio.
     * @return El precio del café pagado.
     */
    open fun pedirPago(cafe: Cafe): Int{
        mostrarMensaje("\nEl precio del "+cafe.nombre+" es de "+cafe.precio+"\n")
        mostrarMensaje("\nProcesando pago")
        repeat (3) {
            Thread.sleep(1000)
            mostrarMensaje(".")
        }
        mostrarMensaje("\nMuchas gracias por su compra\n")
        return cafe.precio
    }
    /**
     * Solicita al usuario la cantidad de azúcar deseada para el café.
     *
     * El valor debe estar entre 1 y 10. En caso de introducir un número inválido,
     * se volverá a preguntar hasta que la entrada sea correcta.
     *
     * @param cafe Objeto [Cafe] al que se añadirá la cantidad de azúcar.
     * @return La cantidad de azúcar seleccionada.
     */
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
    /**
     * Muestra un mensaje de error en la consola.
     *
     * @param mensaje Texto del error.
     */
    open fun mostrarError(mensaje: String) {
        mostrarMensaje("ERROR: $mensaje")//funciona como el mostrar mensaje
    }
}