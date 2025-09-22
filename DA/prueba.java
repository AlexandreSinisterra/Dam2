import java.io.File;
import java.io.IOException;

public class prueba {

    public static void main(String[] args) {
        String rADirectorio = "/home/dam/Documentos/DirPrueba";
        System.out.println(creaDirectorio(rADirectorio));
        System.out.println(eDirectorio(rADirectorio));
        String nombreFichero = "pulgar";
        System.out.println(crearFicheiro(rADirectorio,nombreFichero));
        String rAFichero = "/home/dam/Documentos/DirPrueba/pulgar";
        System.out.println(eFicheiro(rAFichero));
        System.out.println(modoAcceso(rADirectorio,nombreFichero));
    }

    public static String creaDirectorio(String directorio) {
        File c = new File(directorio);
        if (!c.exists()) {
            c.mkdir();
            return "- directorio" + directorio + " creado";
        } else {
            return "- directorio ya existe";
        }
    }


    public static String eDirectorio(String cadea) {
        File c = new File(cadea);
        if (c.isDirectory()) {
            return ("- Es directorio");
        } else {
            return ("- non directorio");
        }
    }


    public static String crearFicheiro(String dirName, String fileName) {
        File f = new File(dirName, fileName);
        try {
            if (!f.exists()) {
                f.createNewFile();
                return "-  fichero" + fileName + " creado";
            } else {
                return "- fichero ya existe";
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "error";
        }
    }


    public static String eFicheiro(String cadea) {
        File c = new File(cadea);
        if (c.isFile()) {
            return ("- Es ficheiro");
        } else {
            return ("- non ficheiro");
        }
    }


    public static String modoAcceso(String dirName, String fileName) {
        File f = new File(dirName, fileName);
        String mensaje="- ";

        if (f.canRead()) {
            mensaje +="lectura si";
        } else mensaje +="lectura no";

        if (f.canWrite()) {
            mensaje +=", escritura si";
        } else mensaje +=", escritura no";

        return mensaje;
    }



}
