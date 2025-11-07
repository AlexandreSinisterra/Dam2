package ejercicios.Tarea27;

import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {
        final int PUERTO = 6731;

        try (ServerSocket socketServidor = new ServerSocket(PUERTO)) {

            Socket conexion = socketServidor.accept();

            BufferedReader recibir = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            PrintWriter enviar = new PrintWriter(conexion.getOutputStream(), true);

            String recibido;

            while (true) {
                recibido = recibir.readLine();

                /**
                 * la linea recibida nunca va a ser null
                 * si se presiona la tecla 'enter', el mensaje recibido seria ""
                 * y en caso de detectar anteriormente el "" y cambiarlo por un null
                 * el printwriter lo transformaria en una cadena: "null"
                 */
                System.out.println(recibido);

                if (recibido.equalsIgnoreCase("")) {
                    System.out.println("se ha detectado un mensaje vacio, cerrando bucle del servidor");
                    break;
                }

                if (recibido.equalsIgnoreCase("adios")) {
                    break;
                }

                enviar.println("ECO: " + recibido);
            }

            conexion.close();

        } catch (IOException e) {
            System.err.println("Ha ocurrido el siguiente error: " + e.getMessage());
        }
    }
}
