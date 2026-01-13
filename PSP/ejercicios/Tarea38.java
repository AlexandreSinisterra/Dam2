package ejercicios;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Scanner;

public class Tarea38 {
    public static void main(String[] args) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            Scanner sc = new Scanner(System.in);

            String contrasenha = Registro(md,sc);
            Boolean igual = InicioSesion(md,sc,contrasenha);

            if (igual) System.out.println("ACCESO CONCEDIDO");
            else System.out.println("ERROR: Credenciales inválidas");

            sc.close();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String Registro(MessageDigest md,Scanner sc){

        System.out.println("manda la contraseña para el registro");
        String mensajeNoCifrado = sc.nextLine();

        md.update(mensajeNoCifrado.getBytes());

        byte[] resumen = md.digest();

        String mensajeCifrado = HexFormat.of().formatHex(resumen);
        System.out.println(mensajeCifrado);

        return mensajeCifrado;
    }

    public static boolean InicioSesion(MessageDigest md,Scanner sc, String contra){

        System.out.println("Usuario registrado. Inicie sesión para probar.");
        String mensajeNoCifrado = sc.nextLine();

        md.update(mensajeNoCifrado.getBytes());

        byte[] resumen = md.digest();

        String mensajeCifrado = HexFormat.of().formatHex(resumen);
        System.out.println(mensajeCifrado);

        if (mensajeCifrado.equals(contra)) return true;
        else return false;
    }
}
