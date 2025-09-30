public class hilos extends Thread {

    /**
     * Constructor que recibe el nombre del hilo y lo pasa
     * a la clase padre (Thread) para su inicialización.
     * @param str El nombre para este hilo.
     */
    public hilos(String str) {
        super(str);
    }

    // Definimos lo que hace el hilo
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " " + getName());
        }
    }

    /**
     * El método main es el punto de entrada. Crea y lanza dos
     * hilos ("Lucas" y "Andy") y luego imprime un mensaje
     * para indicar que el hilo principal ha terminado sus tareas.
     */
    public static void main(String[] args) {
        new hilos("Lucas").start();
        new hilos("Andy").start();
        System.out.println("Termina hilo principal");
    }
}