# Explicación paso a paso: Wave de enemigos usando `Supplier`

## 1. Clase base `Enemigo`
Todos los enemigos heredan de esta clase. Tiene:
- `nombre`
- `vida`
- un método `atacar()` que define la acción de ataque.

```java
abstract class Enemigo {
    String nombre;
    int vida;

    public Enemigo(String nombre, int vida) {
        this.nombre = nombre;
        this.vida = vida;
    }

    public void atacar() {
        System.out.println(nombre + " ataca con " + vida + " de fuerza!");
    }

    @Override
    public String toString() {
        return nombre + " (Vida: " + vida + ")";
    }
}
```
## 2. Cinco tipos distintos de enemigos

Cada clase concreta (Zombie, Vampiro, etc.) tiene un constructor vacío. Esto es importante porque:

- Permite usar Supplier<Enemigo> sin parámetros.

- Cada vez que llamamos get(), obtenemos un nuevo objeto distinto.

```java
class Zombie extends Enemigo {
    public Zombie() { super("Zombie", 50); }
}

class Vampiro extends Enemigo {
    public Vampiro() { super("Vampiro", 80); }
}

class Fantasma extends Enemigo {
    public Fantasma() { super("Fantasma", 40); }
}

class Demonio extends Enemigo {
    public Demonio() { super("Demonio", 120); }
}

class HombreLobo extends Enemigo {
    public HombreLobo() { super("HombreLobo", 70); }
}

```
## 3. Lista de Supplier

Aquí guardamos fábricas de enemigos. Cada Supplier sabe cómo crear un tipo de enemigo, haciendo que nuestro método de generación sea totalmente genérico.

```java
List<Supplier<Enemigo>> tiposEnemigos = Arrays.asList(
    Zombie::new,
    Vampiro::new,
    Fantasma::new,
    Demonio::new,
    HombreLobo::new
);
```
## 4. Generar enemigos por wave

Elegimos un Supplier aleatorio y llamamos a get() para crear el enemigo en el momento. Esto evita tener que escribir muchos if/else para cada tipo.

```java
Supplier<Enemigo> generador = tiposEnemigos.get(random.nextInt(tiposEnemigos.size()));
Enemigo enemigo = generador.get();
```
## 5. Ventajas de usar Supplier

- Puedes crear tantos enemigos como quieras sin duplicar código.

- Puedes cambiar la lógica de creación dentro del Supplier (por ejemplo, ajustar vida según el nivel del jugador).

- Es genérico: no importa cuántos tipos de enemigos agregues, el resto del código no cambia.
