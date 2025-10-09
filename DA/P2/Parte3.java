package P2;
import java.io.*;

public class Parte3 {
    public static void main(String[] args) throws FileNotFoundException {
        String nombre = "texto3.txt";
        String direccion = "C:\\Users\\sanda\\Desktop";
        crearFicheiro(direccion,nombre);
        grabarTexto(direccion,nombre);
    }

    public static void crearFicheiro(String dirName, String fileName) {
        File f = new File(dirName, fileName);
        System.out.println("");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    static void grabarTexto(String direccion, String nombre) throws RuntimeException {
        String ruta = (direccion+"\\"+nombre);
        String frase = "o tempo está xélido";

        try (FileOutputStream fos = new FileOutputStream(ruta);
             DataOutputStream dos = new DataOutputStream(fos)) {

            for (int i = 0; i <= 2; i++) {
                System.out.println("Escribiendo: " + frase);
                dos.writeUTF(frase);
                System.out.println("tamaño del fichero: " + dos.size() + " bytes");
            }
            System.out.println("Tamaño final del fichero: " + dos.size() + " bytes");
            System.out.println("");
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }


        try (FileInputStream fis = new FileInputStream(ruta);
             DataInputStream dis = new DataInputStream(fis)) {

            for (int i = 0; i <= 2; i++) {
                System.out.println("quedan: " + fis.available() + " bytes por leer");
                System.out.println("Cadena: " + dis.readUTF());
            }
            System.out.println("Ya no queda nada por leer");
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}