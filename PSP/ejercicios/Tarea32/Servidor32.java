package ejercicios.Tarea32;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor32 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(6730);

        byte[] buffer = new byte[1024];
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

        System.out.println("Servidor esperando palabras...");

        socket.receive(paquete);
        String mensaje = new String(buffer).trim();

        String[] palabras = mensaje.split(",");


        String palabraMasLarga = "";
        for (String palabra : palabras) {
            if (palabra.length() > palabraMasLarga.length()) {
                palabraMasLarga = palabra;
            }
        }

        String respuesta = "Palabra más larga: " + palabraMasLarga + " (longitud: " + palabraMasLarga.length() + ")";
        byte[] datos = respuesta.getBytes();

        DatagramPacket salida = new DatagramPacket(
                datos, datos.length, paquete.getAddress(), paquete.getPort()
        );

        socket.send(salida);
        socket.close();

        System.out.println("Respuesta enviada. Servidor finalizado.");
    }
}
