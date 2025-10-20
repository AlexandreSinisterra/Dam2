public class WaitNotify {
    public static void main(String[] args) {

        Almacen almacen = new Almacen();

        Productor productor = new Productor(almacen);
        Consumidor consumidor = new Consumidor(almacen);

        productor.start();
        consumidor.start();
    }
}



class Productor extends Thread {
    private Almacen almacen;

    public Productor(Almacen a) {
        this.almacen = a;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                almacen.poner("Producto #" + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) { }
    }
}



class Consumidor extends Thread {
    private Almacen almacen;

    public Consumidor(Almacen a) {
        this.almacen = a;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                almacen.sacar();
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) { }
    }
}



class Almacen {
    private String producto = null;

    public synchronized void poner(String p) throws InterruptedException {
        while (producto != null) {
            System.out.println("Almacén lleno. Productor espera...");
            wait();
        }
        producto = p;
        System.out.println("Productor puso: " + p);
        notifyAll();
    }

    public synchronized String sacar() throws InterruptedException {
        while (producto == null) {
            System.out.println("Almacén vacío. Consumidor espera...");
            wait();
        }
        String p = producto;
        producto = null;
        System.out.println("Consumidor sacó: " + p);
        notifyAll();
        return p;
    }
}