package ejercicios;

public class Tarea13 extends Thread{

    public Tarea13(String str) {
        super(str);
    }

    @Override
    public void run() {
        for (int i = 1; i<11; i++){
            System.out.println("Nombre del hilo: "+getName()+", Itineracion: "+i+", Prioridad: "+getPriority());
            try {
                Thread.sleep((long) (Math.random()*9+1)*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Nombre del hilo: "+getName()+", TERMINE , Prioridad: "+getPriority());
    }

    public static void main(String[] args) {
        Tarea13 anacardo = new Tarea13("hilo_1");
        anacardo.setPriority(Thread.MAX_PRIORITY);

        Tarea13 ribonucleico = new Tarea13("hilo_2");
        ribonucleico.setPriority(Thread.NORM_PRIORITY);

        Tarea13 trujo = new Tarea13("hilo_3");
        trujo.setPriority(Thread.NORM_PRIORITY);

        Tarea13 ayuda = new Tarea13("hilo_4");
        ayuda.setPriority(Thread.MIN_PRIORITY);

        anacardo.start();
        ribonucleico.start();
        trujo.start();
        ayuda.start();

    }
}