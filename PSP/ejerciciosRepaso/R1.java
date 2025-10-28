package ejerciciosRepaso;


public class R1 {
    public static void main(String[] args) throws InterruptedException {
        Audiencia audiencia = new Audiencia();

        Tornos Torno1 = new Tornos(audiencia,1000);
        Tornos Torno2 = new Tornos(audiencia,1000);
        Tornos Torno3 = new Tornos(audiencia,1000);
        Tornos Torno4 = new Tornos(audiencia,1000);

        Torno1.start();
        Torno2.start();
        Torno3.start();
        Torno4.start();

        Torno1.join();
        Torno2.join();
        Torno3.join();
        Torno4.join();

        audiencia.mostrarPersonas();
    }
}

class Tornos extends Thread {
    private Audiencia audiencia;
    private int cantidadPersonas;

    public Tornos (Audiencia a, int personas){
        this.audiencia = a;
        this.cantidadPersonas = personas;
    }

    @Override
    public void run(){
        for (int i=0; i<cantidadPersonas; i++) {
            audiencia.añadirPersona();
            try {
                sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Audiencia{
    private int personas = 0;

    public synchronized void añadirPersona() {
        personas ++;
    }

    public void mostrarPersonas(){
        System.out.println(personas);
    }
}