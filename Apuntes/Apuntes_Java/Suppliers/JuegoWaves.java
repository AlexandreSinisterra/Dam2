package Apuntes_Java.Suppliers;

import java.util.*;
import java.util.function.Supplier;

// Clase base para todos los enemigos
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

// Cinco tipos distintos de enemigos
class Zombie extends Enemigo { public Zombie() { super("Zombie", 50); } }
class Vampiro extends Enemigo { public Vampiro() { super("Vampiro", 80); } }
class Fantasma extends Enemigo { public Fantasma() { super("Fantasma", 40); } }
class Demonio extends Enemigo { public Demonio() { super("Demonio", 120); } }
class HombreLobo extends Enemigo { public HombreLobo() { super("HombreLobo", 70); } }

public class JuegoWaves {

    public static void main(String[] args) {
        Random random = new Random();

        // Lista de "fabricas" de enemigos usando Supplier
        List<Supplier<Enemigo>> tiposEnemigos = Arrays.asList(
                Zombie::new,
                Vampiro::new,
                Fantasma::new,
                Demonio::new,
                HombreLobo::new
        );

        int totalWaves = 3; // número de waves de enemigos

        for (int wave = 1; wave <= totalWaves; wave++) {
            System.out.println("\n--- Wave " + wave + " ---");

            int enemigosEnWave = 5 + random.nextInt(6); // de 5 a 10 enemigos por wave
            List<Enemigo> enemigos = new ArrayList<>();

            // Generamos enemigos aleatorios usando Supplier
            for (int i = 0; i < enemigosEnWave; i++) {
                // Elegimos un Supplier aleatorio
                Supplier<Enemigo> generador = tiposEnemigos.get(random.nextInt(tiposEnemigos.size()));

                // Creamos un nuevo enemigo usando el Supplier
                Enemigo enemigo = generador.get();

                enemigos.add(enemigo);
            }

            // Mostramos los enemigos y sus ataques
            enemigos.forEach(System.out::println);
            enemigos.forEach(Enemigo::atacar);
        }
    }
}
