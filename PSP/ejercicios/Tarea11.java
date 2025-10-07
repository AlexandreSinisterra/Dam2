package ejercicios;
import java.util.Scanner;

public class Tarea11 extends Thread{

    private  int limite;
    private  int numero;

    public Tarea11(int a,int n) {
        this.limite = a;
        this.numero = n;

    }


    @Override
    public void run() {
        Thread nuevohilo = null;

        if (limite > 1) {

            int nuevoLimite = limite - 1;
            int nuevoNumero = numero + 1;

            Tarea11 ayuda = new Tarea11(nuevoLimite, nuevoNumero);
            nuevohilo = new Thread(ayuda);
            nuevohilo.start();

        }

        if (numero == 1){

            long inicio = System.currentTimeMillis();

            while(nuevohilo.isAlive()) {
                System.out.println("[Control Central] Vigilando a Hilo-1... sigue activo.");
                try {
                    Thread.sleep(9);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

                System.out.println("[Control Central] Hilo-1 ha terminado.");
                long fin = System.currentTimeMillis();
                long tiempo = fin - inicio;
                System.out.println("Tiempo total de la caída: "+ tiempo +"ms");

        }else {

            for (int i = 1; i < 6; i++) {
                System.out.println("[HILO-" + (numero - 1) + "] Itineracion " + i);
                try {
                    Thread.sleep((long) (Math.random() * 6 + 1));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if (nuevohilo != null) {

                try {
                    nuevohilo.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            System.out.println("Acabó hilo Hilo-" + (numero - 1));
        }
    }


    public static void main(String[] args) {
        new Tarea11(PedirCantidad(),1).start();
    }

    public static int PedirCantidad(){
        Scanner sc = new Scanner(System.in);

        System.out.println("dime cuantos hilos quieres( mayor o igual que 1)");

        int hilos = sc.nextInt()+1;
        sc.close();

        if (hilos>=1){
            System.out.println("mu bien");
        }else{
            System.out.println("mu mal, asignandose 5 por defecto");
            hilos = 5+1;
        }
        return hilos;
    }
}
