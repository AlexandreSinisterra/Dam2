package ejercicios.Tarea27;

import java.io.*;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        String servidor = "localhost";
        int puerto = 6731;

        try {
            Socket conexion = new Socket(servidor, puerto);
            System.out.println("Conectado con el servidor en " + servidor + ":" + puerto);

            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter enviar = new PrintWriter(conexion.getOutputStream(), true);
            BufferedReader recibir = new BufferedReader(new InputStreamReader(conexion.getInputStream()));

            while (true) {
                System.out.print("Escribe un mensaje (escribe 'adios' para terminar la conexion): ");
                String texto = teclado.readLine();

                enviar.println(texto);

                String respuesta = recibir.readLine();

                if ("adios".equalsIgnoreCase(respuesta)) {
                    System.out.println("cerrando la conexion");
                    break;
                }

                /**
                 * Al pasar el mensaje de lo que escribimos por teclado, aunque presionemos 'enter'
                 * el servidor recibira "" y no null, ademas he hecho desde el servidor que si detecte el mensaje ""
                 * nos envie null, pero lo que recibiria el cliente seria el null convertido en una cadena: "null"
                 */
                if (respuesta.equalsIgnoreCase("null")) {
                    System.out.println("se ha detectado un mensaje vacio, cerrando conexion");
                    break;
                }
                System.out.println(respuesta);
            }

            conexion.close();
            System.out.println("bucle terminado");

        } catch (IOException e) {
            System.out.println("Ha ocurrido un error en el cliente: " + e.getMessage());
        }
    }
}
