import java.io.IOException;
import java.util.Map;

public class ProcesoProcessBuilderPrueba {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pBloc = new ProcessBuilder("gnome-text-editor", "prueba.txt");
        Process p = pBloc.start();


        String[] comando = {"gnome-text-editor",};
        ProcessBuilder pb = new ProcessBuilder(comando);
        pb.command().add("prueba.txt");
        Process p2 = pb.start();

        Map<String,String> entorno = pb.environment();
        System.out.println(entorno.get("PATH"));
        System.out.println(pb.directory());

    }
}
