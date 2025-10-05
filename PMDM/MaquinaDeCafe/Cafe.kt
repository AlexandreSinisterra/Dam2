package MaquinaDeCafe

abstract class Cafe(
    val nombre: String,
    val aguaNecesaria: Int,
    val cafeNecesario: Int,
    val azucarNecesaria: Int,
    val lecheNecesaria: Int,
    val vasoNecesario: Int = 1,
    val palitoNecesario: Int = 1
)

class Americano : Cafe("Americano", 200, 15, 5, 0)
class Africano : Cafe("Africano", 150, 20, 7, 10)
class Capuchino : Cafe("Capuchino", 100, 15, 5, 50)
