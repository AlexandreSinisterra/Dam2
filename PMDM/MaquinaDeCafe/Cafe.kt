package MaquinaDeCafe

abstract class Cafe(//las caracteristicas de los cafe
    val nombre: String,
    val aguaNecesaria: Int,
    val cafeNecesario: Int,
    val lecheNecesaria: Int,
    val precio: Int,
    var azucar: Int = 0,
)
// los tipos de cafe que tenemos
class Americano : Cafe("Americano", 200, 15, 0, 2)
class Africano : Cafe("Africano", 0, 20, 10, 5)
class Capuchino : Cafe("Capuchino", 100, 15,  50, 3)
