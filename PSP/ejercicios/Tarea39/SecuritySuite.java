package ejercicios.Tarea39;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class SecuritySuite {
    public static void main(String[] args) {
        cesar();
    }

    public static void Hash(){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String leak = "4a630b8e79a0cd2fbae3f58e751abb28d0f4918f76af188d8996f13fabe08af8";
            String contenido;

            try (BufferedReader br = new BufferedReader(new FileReader("/home/dam/IdeaProjects/Dam2/PSP/ejercicios/Tarea39/diccionario.txt"))) {
                    String lectura;

                    while ((lectura = br.readLine()) != null) {

                        contenido = (lectura);
                        md.update(contenido.getBytes());
                        byte[] resumen = md.digest();
                        String mensajeCifrado = HexFormat.of().formatHex(resumen);

                        if (mensajeCifrado.equals(leak)) {
                            System.out.println("¡CONTRASEÑA ENCONTRADA! La clave es:"+contenido);
                            break;
                        }

                        md.reset();

                    }

                } catch (IOException e) {
                }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cesar(){

        String secreto = "KRÑD OXPGR";
        String alfabeto = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        String nuevaPalabra="";
        int clave = 0;
        boolean encontrada = false;

        for (int desplazamiento = 1; desplazamiento<27; desplazamiento++ ) {

            nuevaPalabra = "";
            clave = desplazamiento;

            for (int i = 0; i < secreto.length(); i++) {

                char letra = secreto.charAt(i);
                int posicion = alfabeto.indexOf(letra);

                if (posicion != -1) {
                    posicion = (posicion - desplazamiento + 27) % 27;
                    nuevaPalabra += alfabeto.charAt(posicion);
                } else {
                    nuevaPalabra += " ";
                }

            }
            encontrada = prueba(nuevaPalabra);
            if (encontrada) break;
        }

        String mensaje = encontrada
                ? "Mensaje Descifrado: [" + nuevaPalabra + "] (Clave:" + clave + ")"
                : "No se encontró la palabra";

        System.out.println(mensaje);

    }

    public static boolean prueba(String palabra){
        HttpClient client = HttpClient.newHttpClient();
        String body = "text="+palabra+"&language=es";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.languagetool.org/v2/check"))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {

            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            return response.body().contains("\"matches\":[]") ? true : false;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
