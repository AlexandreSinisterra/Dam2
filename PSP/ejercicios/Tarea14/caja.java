package ejercicios.Tarea14;

public class caja extends Thread{
    public static double capital = 1000.0;
    private final int cantidad;
    private final int signo;

    public caja(int a,int b) {
        this.cantidad = a;
        this.signo = b;

    }

    @Override
    public void run() {
        synchronized (caja.class) {
            if (signo > 0) {
                for (int i = 1; i < cantidad; i++) {
                    System.out.println(capital);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    capital += 10;
                }
            } else {
                for (int i = 1; i < cantidad; i++) {
                    System.out.println(capital);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    capital -= 10;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        caja ingresos = new caja(5000,1);
        caja extracciones = new caja(3000,-1);
        ingresos.start();
        extracciones.start();
        ingresos.join();
        extracciones.join();
        System.out.println("el saldo final es de "+capital+" deberia ser igual a 21000.0");

    }
}
