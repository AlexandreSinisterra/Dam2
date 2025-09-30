import java.io.File;
import java.io.*;

public class P1 {

    public static void main(String[] args) throws IOException {
        /**
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
         **/
        System.out.println(creaDirectorio("/home/dam/IdeaProjects/arquivosdir"));
        System.out.println(eDirectorio("/home/dam/IdeaProjects/arquivosdir/"));
        System.out.println(crearFicheiro("/home/dam/IdeaProjects/arquivosdir","Products1.txt"));
        System.out.println(eFicheiro("/home/dam/IdeaProjects/arquivosdir/Products1.txt"));
        System.out.println(creaDirectorio("/home/dam/IdeaProjects/arquivosdir/subdir"));
        System.out.println(crearFicheiro("/home/dam/IdeaProjects/arquivosdir/subdir","Products2.txt"));
        mContido("/home/dam/IdeaProjects/arquivosdir/");
        System.out.println(modoAcceso("/home/dam/IdeaProjects/arquivosdir","Products1.txt"));
        System.out.println(calculaLonxitude("/home/dam/IdeaProjects/arquivosdir","Products1.txt"));
        System.out.println(mLectura("/home/dam/IdeaProjects/arquivosdir","Products1.txt"));
        System.out.println(mEscritura("/home/dam/IdeaProjects/arquivosdir","Products1.txt"));
        System.out.println(borrarFicheiro("/home/dam/IdeaProjects/arquivosdir","Products1.txt"));
        System.out.println(borrarFicheiro("/home/dam/IdeaProjects/arquivosdir/subdir","Products2.txt"));
        System.out.println(borrarDirectorio("/home/dam/IdeaProjects/arquivosdir/subdir"));
        System.out.println(borrarDirectorio("/home/dam/IdeaProjects/arquivosdir"));

    }

    public static String creaDirectorio(String directorio) {
        File c = new File(directorio);
        System.out.println("");
        if (!c.exists()) {
            c.mkdir();
            return "- directorio" + directorio + " creado";
        } else {
            return "- directorio "+directorio+"ya existe";
        }

    }


    public static String eDirectorio(String cadea) {
        File c = new File(cadea);
        System.out.println("");
        if (c.isDirectory()) {
            return ("- "+cadea+" Es directorio");
        } else {
            return ("- "+cadea+"- non directorio");
        }
    }


    public static String crearFicheiro(String dirName, String fileName) {
        File f = new File(dirName, fileName);
        System.out.println("");
        try {
            if (!f.exists()) {
                f.createNewFile();
                return "-  fichero" + fileName + " creado";
            } else {
                return "- fichero "+fileName+" ya existe";
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "error";
        }
    }


    public static String eFicheiro(String cadea) {
        File c = new File(cadea);
        System.out.println("");
        if (c.isFile()) {
            return ("- "+cadea+" Es ficheiro");
        } else {
            return ("- "+cadea+" non ficheiro");
        }
    }


    public static String modoAcceso(String dirName, String fileName) {
        File f = new File(dirName, fileName);
        System.out.println("");
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
        System.out.println("");

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
            mensaje= "- fichero "+fileName+" borrado";
        }

        return mensaje;
    }

    public static String borrarDirectorio(String dirName) throws IOException {
        File directorio = new File(dirName);
        String mensaje;
        if (directorio.delete()) {
            mensaje = "directorio "+dirName+" borrado correctamente.";
        } else {
            mensaje = "No se pudo borrar el directorio.";
        }
        return mensaje;
    }

    public static void mContido(String dirName) {
        File dir = new File(dirName);
        System.out.println("");

            String[] contenido = dir.list();

            if (contenido.length > 0) {
                System.out.println("dentro del directorio hay: ");
                for (String a : contenido) {
                    System.out.println("- "+a);
                }

            } else {
                System.out.println("No tiene contenido.");
            }
    }
}
