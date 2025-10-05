package MaquinaDeCafe

import MaquinaDeCafe.MaquinadeCafe.EstadoMaquinaCafe
import MaquinaDeCafe.MaquinadeCafe.interfaz

fun main() {
    MaquinadeCafe.iniciar()
}
object MaquinadeCafe {
    var estadoactual: EstadoMaquinaCafe = EstadoMaquinaCafe.Idle
    var interfaz = Interfaz()

    fun iniciar() {
        while (true) {
            when (estadoactual) {
                is EstadoMaquinaCafe.Idle -> {
                    interfaz.mostrarMensaje("\nEmpezando examen de la máquina\n")
                    estadoactual = EstadoMaquinaCafe.VerificandoEstado
                }

                is EstadoMaquinaCafe.VerificandoEstado -> {
                    interfaz.mostrarMensaje("\nProcediendo a verificar los elementos\n")
                    estadoactual = EstadoMaquinaCafe.VerificandoCafe
                }

                is EstadoMaquinaCafe.VerificandoCafe -> {
                    interfaz.mostrarMensaje("\nComprobando Café")
                    repeat (3) {
                        Thread.sleep(1000)
                        interfaz.mostrarMensaje(".")
                    }
                    val hayCafe = true
                    if (hayCafe){
                        interfaz.mostrarMensaje("\nCafé correcto\n")
                        estadoactual = EstadoMaquinaCafe.VerificandoAgua
                    }else{
                        estadoactual = EstadoMaquinaCafe.Error("\nNo queda cafe\n")
                    }
                }

                is EstadoMaquinaCafe.VerificandoAgua -> {

                }


                is EstadoMaquinaCafe.Limpiando -> {
                    interfaz.mostrarMensaje("Limpiando la máquina...")
                    Thread.sleep(500)
                    estadoactual = EstadoMaquinaCafe.Idle
                }
                is EstadoMaquinaCafe.Error -> {
                    val msg = (estadoactual as EstadoMaquinaCafe.Error).message
                    interfaz.mostrarError(msg)
                    Thread.sleep(500)
                    estadoactual = EstadoMaquinaCafe.Limpiando
                }
            }
        }
    }

    sealed class EstadoMaquinaCafe {
        object Idle : EstadoMaquinaCafe()
        object VerificandoEstado : EstadoMaquinaCafe()
        object VerificandoCafe : EstadoMaquinaCafe()
        object VerificandoAgua : EstadoMaquinaCafe()

        object Limpiando : EstadoMaquinaCafe()
        data class Error(val message: String) : EstadoMaquinaCafe()
    }
}