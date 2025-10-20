package ejercicios;

import java.util.Scanner;

public class Tarea19 extends Thread{
    public static int nVocales = 0;
    public char letra;
    public String frase;
    public char letraActual;

    public Tarea19(char letra, String frase) {
        this.frase = frase;
        this.letra = letra;
    }

    @Override
    public void run() {
        synchronized (Tarea19.class) {
            for (int i = 0; i<frase.length();i++){//bucle que recorre todo el String
                letraActual = frase.charAt(i);//va copiando cada letra
                if (letraActual==letra) nVocales++;//y si es igual a la que el hilo esta buscando, suma 1 al Nº de vocales
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("introduce una frase y te diré el numero de vocales que hay");
        Scanner sc = new Scanner(System.in);
        String frase = sc.nextLine();
        frase = frase.toLowerCase();//pasamos el texto a minusculas por si hay vocales en mayusculas, asi no tenemos que comprobar el doble
        sc.close();

        Tarea19 vocalA = new Tarea19('a',frase);
        Tarea19 vocalE = new Tarea19('e',frase);
        Tarea19 vocalI = new Tarea19('i',frase);
        Tarea19 vocalO = new Tarea19('o',frase);
        Tarea19 vocalU = new Tarea19('u',frase);

        vocalA.start();
        vocalE.start();
        vocalI.start();
        vocalO.start();
        vocalU.start();

        vocalA.join();
        vocalE.join();
        vocalI.join();
        vocalO.join();
        vocalU.join();

        System.out.println("En el texto hay un total de "+nVocales+" vocales");

    }
}
