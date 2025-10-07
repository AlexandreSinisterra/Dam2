package ejercicios;

public class Tarea12 extends Thread{

    public  int numero;
    public  int resta;


    public Tarea12(int a, int b) {
        this.numero = a;
        this.resta = b;

    }

    @Override
    public void run() {
        if (resta == 0) {
            for (int i = numero + numero; i <= 1923; i += 2) {
                numero+=i;
            }
        }else{
            for (int i = numero + 10; i <= 1923; i += 10) {
                numero+=i;
            }
            for (int i = resta + 10; i <= 1923; i += 10) {
                resta+=i;
            }
        }
        System.out.println(getName()+" "+(numero+resta));
    }

    public static void main(String[] args) {
        Tarea12 hilopar = new Tarea12(2,0);
        hilopar.setPriority(Thread.MAX_PRIORITY);

        Tarea12 hiloimpar = new Tarea12(1,0);
        hiloimpar.setPriority(Thread.NORM_PRIORITY);

        Tarea12 hilo34 = new Tarea12(3,4);
        hilo34.setPriority(Thread.MIN_PRIORITY);

        hilopar.start();
        hiloimpar.start();
        hilo34.start();
    }
}
