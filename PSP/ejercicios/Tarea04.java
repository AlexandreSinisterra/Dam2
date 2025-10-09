package ejercicios;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Tarea04 {
    public static void main(String[] args) throws IOException {
        boolean esWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        String[] comando;
        if (esWindows){
            comando = new String[]{"cmd", "dir"};
        }else {
            comando = new String[]{"sh","ls"};
        }
        ProcessBuilder pb = new ProcessBuilder(comando);


        String ruta = System.getProperty("user.dir");
        System.out.println(ruta);

        String directorio = new File(ruta).getName();
        System.out.println(directorio);

        String rutahome = System.setProperty("user.home", ruta);
        System.out.println(rutahome);

        String rutaTemp = System.setProperty("java.io.tmpdir",ruta);
        System.out.println(rutaTemp);


        Process p2 = pb.start();
    }
}

/*

        Map<String,String> entorno = pb.environment();
        String nuevaRuta = entorno.get("PATH")+":/tmp";
        entorno.replace("PATH",nuevaRuta);
        entorno.put("SALUDO", "Hola Mundo");
        pb.command("/bin/bash", "-c", "echo $SALUDO");

 */

