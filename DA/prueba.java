import java.io.File;
import java.io.*;
import java.nio.file.*;

public class prueba {

    public static void main(String[] args) throws IOException {
        String rADirectorio = "/home/dam/Documentos/DirPrueba";
        System.out.println(creaDirectorio(rADirectorio));
        System.out.println(eDirectorio(rADirectorio));
        String nombreFichero = "pulgar";
        System.out.println(crearFicheiro(rADirectorio,nombreFichero));
        String rAFichero = "/home/dam/Documentos/DirPrueba/pulgar";
        System.out.println(eFicheiro(rAFichero));
        System.out.println(modoAcceso(rADirectorio,nombreFichero));
        System.out.println(calculaLonxitude(rADirectorio,nombreFichero));
        System.out.println(mLectura(rADirectorio,nombreFichero));
        System.out.println(mEscritura(rADirectorio,nombreFichero));
        mContido(rADirectorio);
        System.out.println(borrarFicheiro(rADirectorio,nombreFichero));
        System.out.println(borrarDirectorio(rADirectorio));

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


    public static String calculaLonxitude(String dirName, String fileName) {
        File f = new File(dirName, fileName);

        return ("- longitud: " + f.length() +" bytes");
    }


    public static String mLectura(String dirName, String fileName) {
        File f = new File(dirName, fileName);
                f.setReadOnly();
        return modoAcceso(dirName,fileName);
    }


    public static String mEscritura(String dirName, String fileName) {
        File f = new File(dirName, fileName);
            f.setWritable(true);
        return modoAcceso(dirName,fileName);
    }


    public static String borrarFicheiro(String dirName, String fileName) {
        File f = new File(dirName, fileName);
        String fichero = dirName+"/"+fileName;
        String mensaje;

        if (eFicheiro(fichero).contains("non")){
            mensaje="- fichero no existe";
        }else {
            f.delete();
            mensaje= "- borrado";
        }

        return mensaje;
    }

    public static String borrarDirectorio(String dirName) throws IOException {
        File directorio = new File(dirName);
        String mensaje;
        if (directorio.delete()) {
            mensaje = "Fichero borrado correctamente.";
        } else {
            mensaje = "No se pudo borrar el fichero.";
        }
        return mensaje;
    }

    public static void mContido(String dirName) {
        File dir = new File(dirName);

            String[] contido = dir.list();

            if (contido.length > 0) {
                for (String t : contido) {
                    System.out.println(t);
                }

            } else {
                System.out.println("No tiene contenido.");
            }
    }
}
