import java.io.IOException;

public class ProcesoRuntimePrueba {
    public static void main(String[] args) throws IOException {
        String[] procesoAEjecutar = {"gnome-text-editor","prueba.txt"};
        //mi bombo
       /* for (int i=0;i< 100000; i++){
            Runtime.getRuntime().exec(procesoAEjecutar);
        }*/
        Runtime.getRuntime().exec(procesoAEjecutar);
    }
}
