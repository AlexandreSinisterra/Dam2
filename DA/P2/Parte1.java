package P2;

import java.io.*;
import java.util.Arrays;

public class Parte1  {

    public static void main(String[] args) throws IOException {
        crearFicheiro("/home/dam/Escritorio","texto1.txt");
        escribirTexto("/home/dam/Escritorio/texto1.txt","Tanjiro\n" +
                "Nezuko\n" +
                "Muzan\n");
        crearFicheiro("/home/dam/Escritorio","texto2.txt");
        copiarArchivo("/home/dam/Escritorio/texto1.txt","/home/dam/Escritorio/texto2.txt");
        copiarArchivo2("/home/dam/Escritorio/texto1.txt","/home/dam/Escritorio/texto2.txt");
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


    public static void escribirTexto(String ruta, String texto) {
        try (FileWriter fw = new FileWriter(ruta)) {
            fw.write(texto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void copiarArchivo(String ruta, String ruta2) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String lectura;
            String contenido = "";
            while ((lectura = br.readLine()) != null) {
                contenido += ("\n"+lectura);
            }
            contenido = (contenido+"\n");
            escribirTexto(ruta2,contenido);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }

    public static void copiarArchivo2 (String ruta, String ruta2) throws IOException {
        FileInputStream in = new FileInputStream(ruta);
        FileOutputStream out = new FileOutputStream(ruta2,true);
        int bite;
        try {
            while ((bite = in.read())!= -1) {
                out.write(bite);
            }
        } catch (IOException e) {
        }
        in.close();
        out.close();
    }

}