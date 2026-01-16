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
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class SecuritySuite {
    private static final AtomicBoolean encontrada = new AtomicBoolean(false);
    private static final String leak = "4a630b8e79a0cd2fbae3f58e751abb28d0f4918f76af188d8996f13fabe08af8";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        interfaz:
        while (true){
            System.out.println("bienvenido, escriba 'salir' para cerrar el programa");
            System.out.println("pulse 1 para realizar el hash");
            System.out.println("pulse 2 para acceder al descodificador César");
            System.out.println("pulse 3 para hacer fuerza bruta al hash");
            String opcion = sc.nextLine();
            switch (opcion){
                case "1": Hash();
                break;
                case "2": cesar(sc);
                break;
                case "3": fuerzaBruta();
                break;
                case "salir": break interfaz;
                default:
                    System.out.println("ERROR: opcion no encontrada");
            }
        }
        sc.close();
    }
    public static void fuerzaBruta(){

        encontrada.set(false);

        ExecutorService pool = Executors.newFixedThreadPool(4);
        char[] letras = "abcdefghijklmnñopqrstuvwxyz".toCharArray();

        pool.submit(() -> probar(letras, 'a', 'f'));
        pool.submit(() -> probar(letras, 'g', 'l'));
        pool.submit(() -> probar(letras, 'm', 'r'));
        pool.submit(() -> probar(letras, 's', 'z'));

        pool.shutdown();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void probar(char[] letras, char inicio, char fin){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        for (char c1 = inicio; c1 <= fin && !encontrada.get(); c1++) {
            for (char c2 : letras) {
                if (encontrada.get()) break;
                for (char c3 : letras) {
                    if (encontrada.get()) break;
                    for (char c4 : letras) {
                        if (encontrada.get()) break;

                        String intento = "" + c1 + c2 + c3 + c4;

                        md.update(intento.getBytes());
                        byte[] resumen = md.digest();
                        String mensajeCifrado = HexFormat.of().formatHex(resumen);
                        if (mensajeCifrado.equals(leak)) {
                            if (encontrada.compareAndSet(false, true)) {
                                System.out.println("Encontrada por " + Thread.currentThread().getName() + " -> " + intento);
                                System.out.println("");
                            }
                            break;
                        }
                    }
                }
            }
        }
    }



    public static void Hash(){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String contenido;

            try (BufferedReader br = new BufferedReader(new FileReader("diccionario.txt"))) {
                    String lectura;

                    while ((lectura = br.readLine()) != null) {

                        contenido = lectura.trim();
                        md.update(contenido.getBytes());
                        byte[] resumen = md.digest();
                        String mensajeCifrado = HexFormat.of().formatHex(resumen);

                        if (mensajeCifrado.equals(leak)) {
                            System.out.println("¡CONTRASEÑA ENCONTRADA! La clave es:"+contenido);
                            break;
                        }
                    }

                } catch (IOException e) {
                }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cesar(Scanner sc){

        //String secreto = "KRÑD OXPGR";
        String alfabeto = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        String nuevaPalabra="";
        int clave = 0;
        boolean encontrada = false;
        System.out.println("introduce la palabra codificada en César");
        String secreto = sc.nextLine().toUpperCase().trim();
        for (int desplazamiento = 1; desplazamiento<27; desplazamiento++ ) {
            if (secreto.length()<2){
                System.out.println("muy pequeño");
                break;
            }
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
