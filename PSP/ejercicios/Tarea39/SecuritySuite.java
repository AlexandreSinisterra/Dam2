package ejercicios.Tarea39;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class SecuritySuite {
    public static void main(String[] args) {
        Hash();
    }

    public static void Hash(){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String leak = "4a630b8e79a0cd2fbae3f58e751abb28d0f4918f76af188d8996f13fabe08af8";
            String contenido = "";
            try (BufferedReader br = new BufferedReader(new FileReader("/home/dam/IdeaProjects/Dam2/PSP/ejercicios/Tarea39/diccionario.txt"))) {
                    String lectura;
                    while ((lectura = br.readLine()) != null) {
                        contenido = (lectura);
                        md.update(contenido.getBytes());
                        byte[] resumen = md.digest();
                        String mensajeCifrado = HexFormat.of().formatHex(resumen);
                        if (mensajeCifrado.equals(leak)) {
                            System.out.println("encontrado");
                            break;
                        }
                        md.reset();
                    }
                } catch (IOException e) {
                }
            System.out.println("la contraseña es: "+contenido);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
