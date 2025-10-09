package MaquinaDeCafe

/**
 * Punto de entrada principal del programa.
 *
 * Llama a la función [MaquinadeCafe.iniciar] para comenzar
 * la simulación de la máquina de café.
 */
fun main() {
    MaquinadeCafe.iniciar()
}
/**
 * Representa la máquina de café automática.
 *
 * Este objeto gestiona el ciclo de vida de la máquina, sus estados
 * y los recursos disponibles (agua, café, leche, azúcar, vasos y palitos).
 *
 * La máquina funciona mediante un bucle infinito en el que va cambiando de estado
 * de acuerdo con el flujo definido en [EstadoMaquinaCafe].
 *
 * Recursos gestionados:
 * - [agua] en mililitros
 * - [cafe] en gramos
 * - [azucar] en gramos
 * - [leche] en mililitros
 * - [vasos] en unidades
 * - [palitos] en unidades
 * - [dineroAcumulado] dinero total recaudado
 */
object MaquinadeCafe {
    var estadoactual: EstadoMaquinaCafe = EstadoMaquinaCafe.Idle // tenemos que asignarle un estado base
    var interfaz = Interfaz() //vinculamos la clase interfaz
    /**
     * Estos son los recursos que iran disminuyendo a medida que se hacen los pedidos
     * El dinero acumulado irá aumentando, aunque no he creado ninguna funcion para ver directamente el dinero
     */
    var dineroAcumulado = 0
    var agua = 1000
    var cafe = 500
    var azucar = 300
    var leche = 500
    var vasos = 10
    var palitos = 10
    /**
     * Inicia la ejecución de la máquina de café.
     *
     * Mediante un bucle infinito, la máquina evalúa su [estadoactual]
     * y ejecuta las acciones correspondientes (verificación de recursos,
     * pedido de café, cobro, preparación y reposición).
     *
     * El flujo se gestiona utilizando la clase sellada [EstadoMaquinaCafe].
     */
    fun iniciar() {
        while (true) {//while true ya que queremos que siempre se esté ejecutando

            when (estadoactual) {//aqui ira comprobando los diferentes estados de la maquina y saltando entre ellos

                is EstadoMaquinaCafe.Idle -> {// estado base de la maquina
                    interfaz.mostrarMensaje("\nEmpezando examen de la máquina\n")//utilizamos la interfaz como view
                    estadoactual = EstadoMaquinaCafe.VerificandoEstado//asi se cambian los estados, se pueden pasar parametros
                }

                is EstadoMaquinaCafe.VerificandoEstado -> {
                    interfaz.mostrarMensaje("\nProcediendo a verificar los elementos\n")
                    estadoactual = EstadoMaquinaCafe.VerificandoCafe
                }
                /**
                 * Los siguientes estados serán para verificar la cantidad
                 * de recursos que tiene la maquina
                 */
                is EstadoMaquinaCafe.VerificandoCafe -> {
                    interfaz.mostrarMensaje("\nComprobando Café")
                    repeat (3) {// bucle para aparentar que esta cargando
                        Thread.sleep(1000)
                        interfaz.mostrarMensaje(".")
                    }
                    var hayCafe = true
                    if (cafe<20) hayCafe=false
                    /**
                     * Si el cafe no es suficiente para hacer cualquier cafe
                     * saltará un error e ira al estado de Error
                     * en el caso de que si haya cafe
                     * saltara al siguiente estado de verificacion
                     */
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
                    var hayagua = true
                    if (agua<200) hayagua=false

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
                    var hayAzucar = true
                    if (azucar<10) hayAzucar=false

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
                    var hayLeche = true
                    if (leche<50) hayLeche=false

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
                    var hayVaso = true
                    if (vasos<1) hayVaso=false
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
                    var hayPalito = true
                    if (palitos<1) hayPalito=false
                    if (hayPalito){
                        interfaz.mostrarMensaje("\nPalito correcto\n")
                        estadoactual = EstadoMaquinaCafe.PidiendoCafe
                    }else{
                        estadoactual = EstadoMaquinaCafe.Error("\nNo queda Palito\n")
                    }
                }

                is EstadoMaquinaCafe.PidiendoCafe ->{
                    interfaz.mostrarMensaje("\nTodo correcto\n")
                    val claseCafe = interfaz.pedirTipoCafe()// se pide el ctipo de cafe, y se obtiene una variable tipo "Cafe"
                    estadoactual = EstadoMaquinaCafe.PidiendoTarjeta(claseCafe)
                }

                is EstadoMaquinaCafe.PidiendoTarjeta ->{
                    val claseCafe = (estadoactual as EstadoMaquinaCafe.PidiendoTarjeta).c
                    dineroAcumulado += interfaz.pedirPago(claseCafe)// se verifica el pago y aumenta la cantidad de dinero
                    estadoactual = EstadoMaquinaCafe.PidiendoAzucar(claseCafe)
                }

                is EstadoMaquinaCafe.PidiendoAzucar ->{
                    val claseCafe = (estadoactual as EstadoMaquinaCafe.PidiendoAzucar).c
                    claseCafe.azucar = interfaz.pedirAzucar(claseCafe)// se modifica la cantidad de azucar del cafe
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

                is EstadoMaquinaCafe.CalculandoRecursos ->{//despues de hacer el cafe se restan los recursos utilizados
                    val claseCafe = (estadoactual as EstadoMaquinaCafe.CalculandoRecursos).c
                    agua -= claseCafe.aguaNecesaria
                    cafe -= claseCafe.cafeNecesario
                    azucar -= claseCafe.azucar
                    leche -= claseCafe.lecheNecesaria
                    vasos = vasos -1
                    palitos = palitos -1
                    estadoactual = EstadoMaquinaCafe.Idle // vuelve al estado base
                }

                is EstadoMaquinaCafe.Reponiendo -> { //Vuelven la cantidad de recursos originales
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
                    interfaz.mostrarError(msg) //se muestra el error
                    Thread.sleep(500)
                    estadoactual = EstadoMaquinaCafe.Reponiendo //se resetea la maquina y se repone
                }
            }
        }
    }

    /**
     * Se crea una clase sealed donde se van a contener todos los estados de la maquina
     *
     * En esta clase hay 2 tipos de estados los object y los data class
     *
     * Los data class se le pasan un parámetro desde otro estado en este caso el Cafe
     * Los object no se les pasa ningun parametro
     */
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
        object Reponiendo : EstadoMaquinaCafe()
        data class Error(val message: String) : EstadoMaquinaCafe()
    }
}