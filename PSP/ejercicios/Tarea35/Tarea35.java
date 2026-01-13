

//hay que importar la ibreria gson de google para que funcione el programa

/**
 *
 * package ejercicios.Tarea35;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tarea35 {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<Moneda> monedas = cargar_Monedas();

        while (true) {
            String supuesto_nombre = leerFrase();
            boolean moneda_encontrada = false;
            for (Moneda m : monedas) {
                if (m.getSymbol().equals(supuesto_nombre) || m.getName().equals(supuesto_nombre)) {
                    System.out.println("Nombre: "+m.getName());
                    System.out.println("Simbolo: "+m.getSymbol());
                    System.out.println("Valor: "+m.getPrice_usd()+"$");
                    System.out.println("TOP: "+m.getRank());
                    System.out.println("Variacion 24h: "+m.getPc24());
                    moneda_encontrada = true;
                }
            }
            if (!moneda_encontrada) System.out.println("no se ha encontrado la moneda");
        }
    }

    public static String leerFrase() {
        String str;
        Scanner sc = new Scanner(System.in);
        System.out.println("introduzca la moneda (simbolo o nombre):");
        str = sc.nextLine();
        return str;
    }
*/
    /**
     * Lo hice creando objetos a partir del json porque me parecía una practica interesante
     * no se que tan bien estará realizar esta práctica, ya que crear más de 100 objetos no
     * pinta muy bien pero quise intentarlo
     *
     * Sé que simplemente podrías colocar la comprobacion de la moneda en el get y ya (lo marco y explico abajo)
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    /**
    public static List<Moneda> cargar_Monedas() throws IOException, InterruptedException {
        List<Moneda> monedas = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.coinlore.net/api/tickers/?start=0&limit=100"))//solo cargo los 100 primeros objetos
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json1 = response.body();

        JsonObject root = JsonParser.parseString(json1).getAsJsonObject();

        JsonArray gsonArr = root.getAsJsonArray("data");

        for (JsonElement obj : gsonArr) { //En este bucle recorre todas las monedas
            JsonObject gsonObj = obj.getAsJsonObject();
*/
            /**
             * Simplemente aqui en el bucle podrías comparar el nombre que el cliente nos da
             * con el nombre y simbolo con los ".get", y si algunp de ellos es el mismo, pues que
             * nos salga por pantalla la informacion de esta moneda
             */
            /**
            Moneda moneda = new Moneda(
                    gsonObj.get("name").getAsString(),
                    gsonObj.get("symbol").getAsString(),
                    gsonObj.get("price_usd").getAsDouble(),
                    gsonObj.get("rank").getAsInt(),
                    gsonObj.get("percent_change_24h").getAsDouble()
            );
            monedas.add(moneda);
        }
        return monedas;
    }
}
*/