package ejercicios;

import java.net.Socket;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.util.Scanner;

public class Tarea25 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Introduce una dirección IP o 'localhost'(para salir escriba 'salir'): ");
            String host = sc.nextLine();

            if (host.equalsIgnoreCase("salir")) {
                System.out.println("Programa finalizado.");
                break;
            }

            System.out.println("si quieres escanear los puertos 21, 22, 80, 443 escriba 'si', si no escriba el puerto personalizado: ");
            String puertoPersonalizado = sc.nextLine();

            if (puertoPersonalizado.equalsIgnoreCase("si")) {
                escanear(host, 21);
                escanear(host, 22);
                escanear(host, 80);
                escanear(host, 443);
            } else {
                try {
                    int puerto = Integer.parseInt(puertoPersonalizado);
                    escanear(host, puerto);
                } catch (NumberFormatException e) {
                    System.out.println("Puerto inválido.");
                }
            }
        }
        sc.close();
    }

    private static void escanear(String host, int puerto) {
        System.out.println("\nEscaneando " + host + " ...");
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, puerto));
            System.out.println("El puerto " + puerto + " está abierto");

        } catch (IOException e) {
            System.out.println("El puerto " + puerto + " está cerrado o inaccesible");

        }
    }

}
