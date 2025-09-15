import java.io.File;
import java.io.IOException;

public class prueba {

    // 1) Detecta se é un directorio
    public static String eDirectorio(String ruta) {
        File f = new File(ruta);
        return f.isDirectory() ? "é directorio" : "non é directorio";
    }

    // 2) Detecta se é un ficheiro
    public static String eFicheiro(String ruta) {
        File f = new File(ruta);
        return f.isFile() ? "é ficheiro" : "non é ficheiro";
    }

    // 3) Crea un directorio se non existe
    public static void creaDirectorio(String ruta) {
        File dir = new File(ruta);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Directorio creado.");
            } else {
                System.out.println("Non se puido crear o directorio.");
            }
        } else {
            System.out.println("O directorio xa existe.");
        }
    }

    // 4) Crea un ficheiro se non existe
    public static void creaFicheiro(String dirName, String fileName) {
        File dir = new File(dirName);
        File ficheiro = new File(dir, fileName);
        if (!ficheiro.exists()) {
            try {
                if (ficheiro.createNewFile()) {
                    System.out.println("Ficheiro creado.");
                } else {
                    System.out.println("Non se puido crear o ficheiro.");
                }
            } catch (IOException e) {
                System.out.println("Erro ao crear o ficheiro: " + e.getMessage());
            }
        } else {
            System.out.println("O ficheiro xa existe.");
        }
    }

    // 5) Comproba modo de acceso
    public static void modoAcceso(String dirName, String fileName) {
        File ficheiro = new File(dirName, fileName);
        System.out.println(ficheiro.canWrite() ? "escritura si" : "escritura non");
        System.out.println(ficheiro.canRead() ? "lectura si" : "lectura non");
    }

    // 6) Calcula lonxitude do ficheiro
    public static void calculaLonxitude(String dirName, String fileName) {
        File ficheiro = new File(dirName, fileName);
        if (ficheiro.exists() && ficheiro.isFile()) {
            System.out.println("Lonxitude: " + ficheiro.length() + " bytes");
        } else {
            System.out.println("O ficheiro non existe.");
        }
    }

    // 7) Fai o ficheiro de só lectura
    public static void mLectura(String dirName, String fileName) {
        File ficheiro = new File(dirName, fileName);
        if (ficheiro.exists()) {
            ficheiro.setReadOnly();
            System.out.println("Ficheiro fixado como só lectura.");
        } else {
            System.out.println("O ficheiro non existe.");
        }
    }

    // 8) Permite escritura no ficheiro
    public static void mEscritura(String dirName, String fileName) {
        File ficheiro = new File(dirName, fileName);
        if (ficheiro.exists()) {
            if (ficheiro.setWritable(true)) {
                System.out.println("Permiso de escritura concedido.");
            } else {
                System.out.println("Non se puido conceder escritura.");
            }
        } else {
            System.out.println("O ficheiro non existe.");
        }
    }

    // 9) Borra ficheiro
    public static void borraFicheiro(String dirName, String fileName) {
        File ficheiro = new File(dirName, fileName);
        if (ficheiro.exists()) {
            if (ficheiro.delete()) {
                System.out.println("Ficheiro borrado.");
            } else {
                System.out.println("Non se puido borrar o ficheiro.");
            }
        } else {
            System.out.println("Ficheiro inexistente.");
        }
    }

    // 10) Borra directorio se está baleiro
    public static void borraDirectorio(String dirName) {
        File dir = new File(dirName);
        if (dir.exists() && dir.isDirectory()) {
            if (dir.list().length == 0) {
                if (dir.delete()) {
                    System.out.println("Directorio borrado.");
                } else {
                    System.out.println("Non se puido borrar o directorio.");
                }
            } else {
                System.out.println("Ruta inexistente ou con descendencia.");
            }
        } else {
            System.out.println("Ruta inexistente ou con descendencia.");
        }
    }

    // 11) Lista contido de primeiro nivel
    public static void mContido(String dirName) {
        File dir = new File(dirName);
        if (dir.exists() && dir.isDirectory()) {
            String[] contido = dir.list();
            if (contido != null && contido.length > 0) {
                for (String nome : contido) {
                    System.out.println(nome);
                }
            } else {
                System.out.println("Directorio baleiro.");
            }
        } else {
            System.out.println("A ruta non é un directorio.");
        }
    }

    // 12) Mostra contido recursivo (opcional)
    public static void recur(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("[DIR] " + file.getAbsolutePath());
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        recur(f);
                    }
                }
            } else {
                System.out.println("[FILE] " + file.getAbsolutePath());
            }
        } else {
            System.out.println("Ruta non existente.");
        }
    }

    // Método main para probar (opcional)
    public static void main(String[] args) {
        creaDirectorio("/home/usuario/proba");
        creaFicheiro("/home/usuario/proba", "arquivo.txt");
        modoAcceso("/home/usuario/proba", "arquivo.txt");
        calculaLonxitude("/home/usuario/proba", "arquivo.txt");
        mLectura("/home/usuario/proba", "arquivo.txt");
        mEscritura("/home/usuario/proba", "arquivo.txt");
        mContido("/home/usuario/proba");
        recur(new File("/home/usuario/proba"));
        borraFicheiro("/home/usuario/proba", "arquivo.txt");
        borraDirectorio("/home/usuario/proba");
    }

}
