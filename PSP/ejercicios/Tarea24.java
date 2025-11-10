package ejercicios;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Tarea24 {
    public static void main(String[] args) throws UnknownHostException {
//a
        System.out.println("Introduce nombre de dominio");
        System.out.println("nombre introducido: https://www.google.com/search?q=google.com ");
        String dominioName = "www.google.com";

        System.out.printf("ip del dominio: ");
        InetAddress ip = InetAddress.getByName(dominioName);
        System.out.println(ip.getHostAddress());

        InetAddress local = InetAddress.getLocalHost();
        System.out.printf("nombre de la maquina: "+local+ " y su ip: ");
        System.out.println(local.getHostAddress());

    }
}
