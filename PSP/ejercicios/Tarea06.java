package ejercicios;

import java.io.IOException;
import java.util.Scanner;

public class Tarea06 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Interfaz PruebaInterfaz = new Interfaz();
        PruebaInterfaz.inicializar();
    }


    static class Interfaz {
        public void inicializar() throws IOException, InterruptedException {
            Scanner sc = new Scanner(System.in);
            Lanzador PruebaLanzamiento = new Lanzador();
            boolean salir = true;
            while (salir) {
                System.out.println("Introduce el host o IP (o 'salir' para terminar):");
                String ipHost = sc.nextLine();
                if (ipHost.equalsIgnoreCase("salir")) {
                    salir = false;
                }
                else {
                    PruebaLanzamiento.inicializar(ipHost);
                }
            }
        }
    }


    static class Lanzador{
        public void inicializar(String ipHost) throws IOException, InterruptedException {
            ProcessBuilder pb = new ProcessBuilder("ping","-n","7",ipHost);
            pb.inheritIO();
            Process p = pb.start();
            int exitcode =  p.waitFor();
            System.out.println(exitcode);
            p.destroy();
        }

    }

}