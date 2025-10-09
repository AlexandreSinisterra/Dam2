package ejercicios;

public class Tarea16 extends Thread{
    public static int contador = 0;

    public Tarea16() {
    }

    @Override
    public void run() {
        synchronized (Tarea16.class) {

                for (int i = 0; i < 50; i++) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    contador++;
                }
            }
        }

    public static void main(String[] args) throws InterruptedException {

        Tarea16 hilo1 = new Tarea16();
        Tarea16 hilo2 = new Tarea16();
        Tarea16 hilo3 = new Tarea16();
        Tarea16 hilo4 = new Tarea16();
        Tarea16 hilo5 = new Tarea16();

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        hilo1.join();
        hilo2.join();
        hilo3.join();
        hilo4.join();
        hilo5.join();

        System.out.println("el saldo final es de "+contador+" deberia ser igual a 250");

    }
}
