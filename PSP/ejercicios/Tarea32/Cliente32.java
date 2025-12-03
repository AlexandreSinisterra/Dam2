package ejercicios.Tarea32;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente32 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();

        String lista = "hola,soy,homero,dou";

        byte[] datos = lista.getBytes();

        InetAddress ip = InetAddress.getByName("localhost");
        DatagramPacket paquete = new DatagramPacket(datos, datos.length, ip, 6730);

        socket.send(paquete);

        byte[] buffer = new byte[1024];
        DatagramPacket mensajeRecibido = new DatagramPacket(buffer, buffer.length);
        socket.receive(mensajeRecibido);

        System.out.println("Servidor dice: " + new String(mensajeRecibido.getData(), 0, mensajeRecibido.getLength()));

        socket.close();
    }
}
