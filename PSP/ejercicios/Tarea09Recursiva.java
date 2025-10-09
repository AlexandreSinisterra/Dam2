package ejercicios;

public class Tarea09Recursiva extends Thread{

    private  int limite;
    private  int primero;
    private  int segundo;
    private  int cantidad;

    public Tarea09Recursiva(int a, int P, int S, int N) {
        this.limite = a;
        this.primero = P;
        this.segundo = S;
        this.cantidad = N;
    }

    @Override
    public void run() {
        if (cantidad<=limite) {
            System.out.println(primero);
            cantidad++;
        }
        if (cantidad<=limite) {
            System.out.println(segundo);
            cantidad++;
        }
        primero+=segundo;
        segundo+=primero;
        if (cantidad<=limite) {
            new Tarea09Recursiva(limite, primero, segundo, cantidad).start();
        }
    }

    public static void main(String[] args) {
        new Tarea09Recursiva(20, 0, 1, 0).start();
    }
}
