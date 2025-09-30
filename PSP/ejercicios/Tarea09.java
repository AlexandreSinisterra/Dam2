package ejercicios;

public class Tarea09 extends Thread{

    private final int cantidad;

    public Tarea09(int a) {
        this.cantidad = a;
    }

    @Override
    public void run() {
        int a=1;
        int b=1;
        int i=1;

        if (i<=cantidad) {
            System.out.println("0");
        }

        while (true) {
            if (i>=cantidad){
                break;
            }
            System.out.println(a);
            i++;
            if (i==cantidad){
                break;
            }
            System.out.println(b);
            i++;
            if (i==cantidad){
                break;
            }
            a+=b;
            b+=a;
        }

    }

    public static void main(String[] args) {
        new Tarea09(7).start();
    }
}
