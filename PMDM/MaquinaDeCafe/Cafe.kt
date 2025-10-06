package MaquinaDeCafe
/**
 * Clase abstracta que representa un café en la máquina.
 *
 * Define las características comunes a todos los tipos de café,
 * como el nombre, los recursos necesarios para su preparación,
 * el precio y la cantidad de azúcar opcional que el usuario puede añadir.
 *
 * @property nombre Nombre del café.
 * @property aguaNecesaria Cantidad de agua necesaria (en ml).
 * @property cafeNecesario Cantidad de café necesaria (en gramos).
 * @property lecheNecesaria Cantidad de leche necesaria (en ml).
 * @property precio Precio del café en Euros.
 * @property azucar Cantidad de azúcar añadida (por defecto 0).
 */
abstract class Cafe(
    val nombre: String,
    val aguaNecesaria: Int,
    val cafeNecesario: Int,
    val lecheNecesaria: Int,
    val precio: Int,
    var azucar: Int = 0,
)
/**
 * Representa un café Americano.
 *
 * Características:
 * - Agua: 200 ml
 * - Café: 15 g
 * - Leche: 0 ml
 * - Precio: 2
 */
class Americano : Cafe("Americano", 200, 15, 0, 2)
/**
 * Representa un café Africano.
 *
 * Características:
 * - Agua: 0 ml
 * - Café: 20 g
 * - Leche: 10 ml
 * - Precio: 5
 */
class Africano : Cafe("Africano", 0, 20, 10, 5)
/**
 * Representa un café Capuchino.
 *
 * Características:
 * - Agua: 100 ml
 * - Café: 15 g
 * - Leche: 50 ml
 * - Precio: 3
 */
class Capuchino : Cafe("Capuchino", 100, 15,  50, 3)
