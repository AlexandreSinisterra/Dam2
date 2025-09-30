package P2;

import java.io.*;
import java.util.Arrays;

public class Parte2 {
    public static void main(String[] args) throws IOException {

        String ruta1 = "/home/dam/Descargas/anaxa.jpeg";
        String ruta2 = "/home/dam/Escritorio/b.jpeg";

        copiarfoto2(ruta1, ruta2);
    }


    public static void copiarfoto(String ruta1, String ruta2) throws IOException {
        FileInputStream in = new FileInputStream(ruta1);
        FileOutputStream out = new FileOutputStream(ruta2, true);
        int caracter;
        try {
            while ((caracter = in.read()) != -1) {
                out.write(caracter);
            }
        } catch (IOException e) {
        }
        in.close();
        out.close();
    }


    public static void copiarfoto2(String ruta1, String ruta2) throws IOException {

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(ruta1));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(ruta2));
        int caracter = 0;
        byte[] p = new byte[8192];
        try {
            int bite;
            while ((bite = in.read()) != -1) {
                byte b = (byte) bite;
                p[caracter] = b;
                caracter++;
            }
            out.write(p);
        } catch (IOException e) {
        }
        in.close();
        out.close();
    }
}