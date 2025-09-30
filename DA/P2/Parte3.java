package P2;
import java.io.*;

public class Parte3 {
    public static void main(String[] args) throws FileNotFoundException {
        String ruta = "texto3.txt";
        duplicarTexto(ruta);
    }

    static void duplicarTexto(String ruta) throws RuntimeException {

        String texto = "o tempo está xélido";
        int nveces = 3;

        try (FileOutputStream fos = new FileOutputStream(ruta);
             DataOutputStream dos = new DataOutputStream(fos)) {

            for (int i = 0; i < nveces; i++) {
                System.out.println("Cadea: " + texto);
                dos.writeUTF(texto);
                System.out.println("tamaño do ficheiro: " + dos.size() + "bytes");
            }

            System.out.println("Tamaño final del archivo: " + dos.size() + " bytes.");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileInputStream fis = new FileInputStream(ruta);
             DataInputStream dis = new DataInputStream(fis)) {

            System.out.println("\n Lectura del archivo ");
            for (int i = 0; i < nveces; i++) {
                System.out.println("quedan: " +fis.available() + " bytes por leer");
                String valorLeido = dis.readUTF(); // Lee la cadena de caracteres que escribimos
                System.out.println("Cadena leída: " + valorLeido);
            }

            System.out.println("\nXa non queda nada por ler");

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}