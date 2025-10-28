package ejerciciosRepaso;

public class R2 {
    public static void main(String[] args) throws InterruptedException {

        Operador operador1 = new Operador("operador1","cogiendo", 200);
        Operador operador2 = new Operador("operador2","pintando", 100);
        Operador operador3 = new Operador("operador3","envolviendo", 100);

        operador1.start();
        operador1.join();

        operador2.start();
        operador2.join();

        operador3.start();
        operador3.join();
    }
}

class Operador extends Thread{
    private int tiempo;
    private String tarea;

    public Operador(String nombre, String tarea, int tiempo){
        super(nombre);
        this.tarea=tarea;
        this.tiempo=tiempo;
    }

    @Override
    public void run() {
        System.out.println(getName()+" "+tarea+" lata");
        try {
            sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
