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

                if (recibido.equalsIgnoreCase("")) {
                    recibido = null;
                    enviar.println(recibido);
                    break;
                }

                if (recibido.equalsIgnoreCase("adios")) {
                    enviar.println(recibido);
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
