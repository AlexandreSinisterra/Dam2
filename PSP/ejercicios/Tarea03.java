package ejercicios;

import java.io.IOException;
import java.util.Scanner;

public class Tarea03 {
    public static void main(String[] args) throws IOException {
        System.out.println("Bienvenido");
        System.out.println("introduce el procesador de texto, procura escribir guiones en vez de espacios:");

        Scanner sc = new Scanner(System.in);
        String procesador = sc.next();

        System.out.println("introduce el nombre/ruta del archivo a abrir/crear");

        String ruta = sc.next();

        String[] comando = {procesador,ruta};

        ProcessBuilder pb = new ProcessBuilder(comando);
        Process p2 = pb.start();
    }
}



