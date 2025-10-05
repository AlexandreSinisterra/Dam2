package MaquinaDeCafe

import MaquinaDeCafe.MaquinadeCafe.EstadoMaquinaCafe
import MaquinaDeCafe.MaquinadeCafe.interfaz

fun main() {
    MaquinadeCafe.iniciar()
}
object MaquinadeCafe {
    var estadoactual: EstadoMaquinaCafe = EstadoMaquinaCafe.Idle
    var interfaz = Interfaz()
    var dineroAcumulado = 0
    var agua = 1000
    var cafe = 500
    var azucar = 300
    var leche = 500
    var vasos = 10
    var palitos = 10

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
                    interfaz.mostrarMensaje("\nComprobando agua")
                    repeat (3) {
                        Thread.sleep(1000)
                        interfaz.mostrarMensaje(".")
                    }
                    val hayagua = true
                    if (hayagua){
                        interfaz.mostrarMensaje("\nagua correcto\n")
                        estadoactual = EstadoMaquinaCafe.VerificandoAzucar
                    }else{
                        estadoactual = EstadoMaquinaCafe.Error("\nNo queda agua\n")
                    }
                }

                is EstadoMaquinaCafe.VerificandoAzucar -> {
                    interfaz.mostrarMensaje("\nComprobando Azucar")
                    repeat (3) {
                        Thread.sleep(1000)
                        interfaz.mostrarMensaje(".")
                    }
                    val hayAzucar = true
                    if (hayAzucar){
                        interfaz.mostrarMensaje("\nAzucar correcto\n")
                        estadoactual = EstadoMaquinaCafe.VerificandoLeche
                    }else{
                        estadoactual = EstadoMaquinaCafe.Error("\nNo queda Azucar\n")
                    }
                }

                is EstadoMaquinaCafe.VerificandoLeche -> {
                    interfaz.mostrarMensaje("\nComprobando Leche")
                    repeat (3) {
                        Thread.sleep(1000)
                        interfaz.mostrarMensaje(".")
                    }
                    val hayLeche = true
                    if (hayLeche){
                        interfaz.mostrarMensaje("\nLeche correcto\n")
                        estadoactual = EstadoMaquinaCafe.VerificandoVaso
                    }else{
                        estadoactual = EstadoMaquinaCafe.Error("\nNo queda Leche\n")
                    }
                }

                is EstadoMaquinaCafe.VerificandoVaso -> {
                    interfaz.mostrarMensaje("\nComprobando Vaso")
                    repeat (3) {
                        Thread.sleep(1000)
                        interfaz.mostrarMensaje(".")
                    }
                    val hayVaso = true
                    if (hayVaso){
                        interfaz.mostrarMensaje("\nVaso correcto\n")
                        estadoactual = EstadoMaquinaCafe.VerificandoPalito
                    }else{
                        estadoactual = EstadoMaquinaCafe.Error("\nNo queda Vaso\n")
                    }
                }

                is EstadoMaquinaCafe.VerificandoPalito -> {
                    interfaz.mostrarMensaje("\nComprobando Palito")
                    repeat (3) {
                        Thread.sleep(1000)
                        interfaz.mostrarMensaje(".")
                    }
                    val hayPalito = true
                    if (hayPalito){
                        interfaz.mostrarMensaje("\nPalito correcto\n")
                        estadoactual = EstadoMaquinaCafe.PidiendoCafe
                    }else{
                        estadoactual = EstadoMaquinaCafe.Error("\nNo queda Palito\n")
                    }
                }

                is EstadoMaquinaCafe.PidiendoCafe ->{
                    interfaz.mostrarMensaje("\nTodo correcto\n")
                    val claseCafe = interfaz.pedirTipoCafe()
                    estadoactual = EstadoMaquinaCafe.PidiendoTarjeta(claseCafe)
                }

                is EstadoMaquinaCafe.PidiendoTarjeta ->{
                    val claseCafe = (estadoactual as EstadoMaquinaCafe.PidiendoTarjeta).c
                    dineroAcumulado += interfaz.pedirPago(claseCafe)
                    estadoactual = EstadoMaquinaCafe.PidiendoAzucar(claseCafe)
                }

                is EstadoMaquinaCafe.PidiendoAzucar ->{
                    val claseCafe = (estadoactual as EstadoMaquinaCafe.PidiendoAzucar).c
                    claseCafe.azucar = interfaz.pedirAzucar(claseCafe)
                    estadoactual = EstadoMaquinaCafe.HaciendoCafe(claseCafe)
                }

                is EstadoMaquinaCafe.HaciendoCafe ->{
                    val claseCafe = (estadoactual as EstadoMaquinaCafe.HaciendoCafe).c
                    interfaz.mostrarMensaje("\nEmpezando a hacer el"+claseCafe.nombre+", espere un momento")
                    repeat (3) {
                        Thread.sleep(1000)
                        interfaz.mostrarMensaje(".")
                    }
                    interfaz.mostrarMensaje("\nEl "+claseCafe.nombre+" está servido, que lo disfrute ^^\n")
                    estadoactual = EstadoMaquinaCafe.CalculandoRecursos(claseCafe)
                }

                is EstadoMaquinaCafe.CalculandoRecursos ->{
                    val claseCafe = (estadoactual as EstadoMaquinaCafe.CalculandoRecursos).c
                    agua -= claseCafe.aguaNecesaria
                    cafe -= claseCafe.cafeNecesario
                    azucar -= claseCafe.azucar
                    leche -= claseCafe.lecheNecesaria
                    vasos = vasos -1
                    palitos = palitos -1
                    estadoactual = EstadoMaquinaCafe.Idle
                }

                is EstadoMaquinaCafe.Limpiando -> {
                    interfaz.mostrarMensaje("Reponiendo maquina...")
                    Thread.sleep(500)
                    agua = 1000
                    cafe = 500
                    azucar = 300
                    leche = 500
                    vasos = 10
                    palitos = 10
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
        object VerificandoAzucar : EstadoMaquinaCafe()
        object VerificandoLeche : EstadoMaquinaCafe()
        object VerificandoVaso : EstadoMaquinaCafe()
        object VerificandoPalito : EstadoMaquinaCafe()
        object PidiendoCafe : EstadoMaquinaCafe()
        data class PidiendoTarjeta(val c: Cafe) : EstadoMaquinaCafe()
        data class PidiendoAzucar(val c: Cafe) : EstadoMaquinaCafe()
        data class HaciendoCafe(val c: Cafe) : EstadoMaquinaCafe()
        data class CalculandoRecursos(val c: Cafe) : EstadoMaquinaCafe()
        object Limpiando : EstadoMaquinaCafe()
        data class Error(val message: String) : EstadoMaquinaCafe()
    }
}