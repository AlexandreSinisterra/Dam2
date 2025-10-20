package ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        String frase = leerFrase();
        frase = frase.toLowerCase();//pasamos el texto a minusculas por si hay vocales en mayusculas, asi no tenemos que comprobar el doble

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


    public static String leerFrase(){
        String str;
        int opcion = 0;
        boolean verificacion = false;

        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Bienvenido al programa de conteo de vocales, seleccione como prefiere introducir su frase:");
            System.out.println("Pulse 1 para escribir por consola");
            System.out.println("Pulse 2 si tienes un archivo de texto ya creado");
            try {
                str = sc.nextLine();
                opcion = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduzca un numero");//si nos da error al convertirlo por lo que no es un numero
            }

            if (opcion<3 && opcion>0) verificacion = true;
            else System.out.println("introduzca 1 o 2");//por si el numero introducido no es ninguno de los 2

        }while (!verificacion);

        System.out.println("De acuerdo");

        return (opcion==1) ? leerTeclado(sc) : leerFichero(sc);//si elegimos la opcion 1 lee el teclado, sino la otra
        //se tiene que pasar el scanner como parametro para seguir utilizandolo, sino hay que usar un buffer reader
    }


    public static String leerTeclado(Scanner sc){
        String texto;

        System.out.println("introduce la frase:");

        texto = sc.nextLine();//si no metemos nada en la frase o no son letras, pues igualmente da igual porque a la hora de contar va a ser 0
        sc.close();//porfin podemos cerrar el scaner

        return texto;
    }


    public static String leerFichero(Scanner sc){
        String ruta;
        boolean  verificacion;
        String lectura;
        String contenido = "";

        do {
            System.out.println("Por favor escriba la ruta del documento de texto");

            ruta = sc.nextLine();

            verificacion = verificarFicheiro(ruta);//verificamos si se nos ha pasado bien la ruta o que sea un archivo

            if (!verificacion) System.out.println("no se ha encontrado el archivo o no es un fichero");

        }while (!verificacion);
        sc.close();//porfin podemos cerrar el scaner

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            while ((lectura = br.readLine()) != null) {
                contenido += ("\n"+lectura);//leemos linea a linea el contenido y lo vamos escribiendo en el string
            }
            contenido = (contenido+"\n");
        } catch (IOException e) {
        }
        return contenido;
    }


    public static boolean verificarFicheiro(String cadea) {
        File c = new File(cadea);
        if (c.isFile()) {
            return true;
        } else {
            return false;
        }
    }
}
