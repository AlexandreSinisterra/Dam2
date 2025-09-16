package ejercicios;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Tarea03 {
    public static void main(String[] args) throws IOException {
        System.out.println("Bienvenido");
        System.out.println("introduce el procesador de texto, procura escribir guiones en vez de espacios:");

        Scanner sc = new Scanner(System.in);
        String procesador = sc.nextLine();

        System.out.println("introduce el nombre/ruta del archivo a abrir/crear");

        String ruta = sc.nextLine();
        String a = procesador + " \"" + ruta + "\"";
        String[] comando = {"sh", "-c",a,"/tmp"};
        ProcessBuilder pb = new ProcessBuilder(comando);

        Process p2 = pb.start();
    }
}

/*

        Map<String,String> entorno = pb.environment();
        String nuevaRuta = entorno.get("PATH")+":/tmp";
        entorno.replace("PATH",nuevaRuta);
        entorno.put("SALUDO", "Hola Mundo");
        pb.command("/bin/bash", "-c", "echo $SALUDO");

 */

