package ejercicios.Tarea33;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tarea33 {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.google.com"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            HttpResponse<Path> response2 = client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get("PSP/ejercicios/Tarea33/example.html")));
            System.out.println("Codigo: "+response.statusCode());
            System.out.println("Codigo: "+response.body());
            if (response.statusCode()==200) {
                response2.body();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
