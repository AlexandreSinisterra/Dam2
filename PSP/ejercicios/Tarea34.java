package ejercicios;

import java.io.IOException;
import java.net.HttpRetryException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.util.Scanner;

public class Tarea34 {
    public static void main(String[] args) throws InterruptedException {
        ELTIEMPO eltiempo = new ELTIEMPO();
        System.out.println("hola");
        // se puede implementar una funcion de creacion de clases/hilos, y mediante una view vamos preguntando cuantos quiere.
        URLClass urlClass = new URLClass(leerFrase(), eltiempo);
        URLClass urlClass2 = new URLClass(leerFrase(), eltiempo);

        urlClass.start();
        urlClass2.start();


        urlClass.join();
        urlClass2.join();


        if (eltiempo.getTamaño() == 0){
            System.out.println("No funcionó ninguna url");
        } else {
            System.out.println(

                    "La web más rápida ha sido: " + eltiempo.getUrlTiempo() + " con " + eltiempo.getTiempo() + " ms.\n" +

                            "La web con más contenido ha sido: " + eltiempo.getUrlTamaño() + " con " + eltiempo.getTamaño() + " caracteres.");
        }
    }

    public static String leerFrase() {
        String str;
        Scanner sc = new Scanner(System.in);
        System.out.println("introduzca la url:");
        str = sc.nextLine();
        return str;
    }
}

class URLClass extends Thread{
    String url;
    private ELTIEMPO eltiempo;
    long time;

    public URLClass(String url, ELTIEMPO eltiempo) {
        this.url = url;
        this.eltiempo = eltiempo;
    }

    @Override
    public void run() {
        try {

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            setTime(System.currentTimeMillis());
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            setTime(System.currentTimeMillis()-getTime());
            //souts para verificar que el programa funciona
            System.out.println(url+" tiempo de espera: "+getTime());
            System.out.println(url+" longitud de mensaje: "+response.body().length());
            eltiempo.compararTiempo(url,time);
            eltiempo.compararTamaño(url,response.body().length());


        } catch (InterruptedException e) { } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

class ELTIEMPO {
    private long tiempo;
    private String urlTiempo;
    private int tamaño = 0;
    private String urlTamaño;
    private long t1 = 0;
    private long t2 = 0;
    private int l1 = 0;
    private int l2 = 0;

    public synchronized void compararTiempo(String urlhilo, long tiempoEsperado) throws InterruptedException {
        if (t1==0 && t2 ==0) t1 = tiempoEsperado;
        else {
            t2 = tiempoEsperado;
            if (t1>t2) t1 = t2;
            t2 =0;
        }
        setTiempo(t1);
        if (t1 == tiempoEsperado) setUrlTiempo(urlhilo);
    }

    public synchronized void compararTamaño(String urlhilo, int longitud) throws InterruptedException {
        if (l1==0 && l2 ==0) l1 = longitud;
        else {
            l2 = longitud;
            if (l1<l2) l1 = l2;
            l2 =0;
        }
        setTamaño(l1);
        if (l1 == longitud) setUrlTamaño(urlhilo);
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    public String getUrlTiempo() {
        return urlTiempo;
    }

    public void setUrlTiempo(String urlTiempo) {
        this.urlTiempo = urlTiempo;
    }

    public String getUrlTamaño() {
        return urlTamaño;
    }

    public void setUrlTamaño(String urlTamaño) {
        this.urlTamaño = urlTamaño;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
}
