package ejercicios;

public class Tarea15 extends Thread{
    private final Thread hilos;

    public Tarea15(String str, Thread a) {
        super(str);
        this.hilos = a;
    }

    @Override
    public void run() {
        if (hilos != null) {
            try {
                hilos.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 1; i <9 ; i++){
            System.out.println("Soy el " + getName() + " - itineracion: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Tarea15 hilo3 = new Tarea15("hilo_3", null);
        Tarea15 hilo2 = new Tarea15("hilo_2", hilo3);
        Tarea15 hilo1 = new Tarea15("hilo_1", hilo2);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        hilo1.join();
        System.out.println("acabe");
    }
}