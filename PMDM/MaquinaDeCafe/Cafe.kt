package MaquinaDeCafe

abstract class Cafe(
    val nombre: String,
    val aguaNecesaria: Int,
    val cafeNecesario: Int,
    val azucarNecesaria: Int,
    val lecheNecesaria: Int,
    val precio: Int,
    val vasoNecesario: Int = 1,
    val palitoNecesario: Int = 1
)

class Americano : Cafe("Americano", 200, 15, 5, 0, 2)
class Africano : Cafe("Africano", 0, 20, 7, 10, 5)
class Capuchino : Cafe("Capuchino", 100, 15, 5, 50, 3)
