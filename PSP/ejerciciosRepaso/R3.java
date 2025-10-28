package ejerciciosRepaso;

public class R3 {
    public static void main(String[] args) {
        Buzon buzon = new Buzon();

        Cliente cliente = new Cliente(buzon);
        Repartidor repartidor = new Repartidor(buzon);

        cliente.start();
        repartidor.start();
    }
}

class Cliente extends Thread{
    Buzon buzon;

    public Cliente(Buzon buzon){
        this.buzon=buzon;
    }

    @Override
    public void run() {
        buzon.cogerPaquete();
    }

}

class Repartidor extends Thread{
    Buzon buzon;

    public Repartidor(Buzon buzon){
        this.buzon=buzon;
    }

    @Override
    public void run() {
        buzon.dejarPaquete();
    }

}

class Buzon{
    private boolean paquete=false;

    public synchronized void cogerPaquete() {
        if(!paquete){
            System.out.println("espere a que llegue el paquete");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        paquete=false;
        System.out.println("paquete recogido");
    }

    public synchronized void dejarPaquete(){
        paquete=true;
        System.out.println("Se ha dejado un paquete");
        notifyAll();
    }
}