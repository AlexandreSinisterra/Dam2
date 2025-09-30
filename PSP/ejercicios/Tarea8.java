package ejercicios;

public class Tarea8 extends Thread{

    private final int nivelCabreo;

    public Tarea8(String str, int a) {
        super(str);
        this.nivelCabreo = a;
    }

    @Override
    public void run() {
        for (int i = 1; i < nivelCabreo; i++) {
            System.out.println("["+getName()+"] Cabreo nivel: "+i);
        }
        System.out.println("["+getName()+"] Cabreo nivel: "+nivelCabreo+"... ¡He llegado a mi límite!");
    }

    public static void main(String[] args) {
        new Tarea8("Diego",4).start();
        new Tarea8("Manuel", 5).start();
        new Tarea8("Damian", 3).start();
        new Tarea8("Araujo", 5).start();
        System.out.println("Termina hilo principal");
    }
}
