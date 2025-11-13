package ejercicios.Tarea27;

import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {
        final int PUERTO = 6731;
        int nClientes = 0;
        try (ServerSocket socketServidor = new ServerSocket(PUERTO)) {

            System.out.println("Servidor esta esperando a los clientes");

        while (true){
            Socket conexion = socketServidor.accept();
            nClientes++;
            System.out.println("cliente numero: "+nClientes+" aceptado");

            GestordeClientes conexionIndividual = new GestordeClientes(conexion, nClientes);
            conexionIndividual.start();
        }

        } catch (IOException e) {
            System.err.println("Ha ocurrido el siguiente error: " + e.getMessage());
        }
    }
}
