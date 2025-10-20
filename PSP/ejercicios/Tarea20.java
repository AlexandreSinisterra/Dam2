package ejercicios;

public class Tarea20 {
    public static void main(String[] args) {

        Buzon buzon = new Buzon();

        Escritor escritor1 = new Escritor(buzon,"escritor1", "holiii");
        Escritor escritor2 = new Escritor(buzon,"escritor2", "buenos dias");
        Escritor escritor3 = new Escritor(buzon,"escritor3", "hey");

        Lector lector1 = new Lector(buzon, "lector1");
        Lector lector2 = new Lector(buzon, "lector2");
        Lector lector3 = new Lector(buzon, "lector3");

        escritor1.start();
        escritor2.start();
        escritor3.start();
        lector1.start();
        lector2.start();
        lector3.start();

    }
}



class Escritor extends Thread {
    private Buzon buzon;
    private String mensaje;

    public Escritor(Buzon a, String nombre, String mensaje) {
        super(nombre);
        this.buzon = a;
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        try {

                buzon.escribir(mensaje, getName());
                Thread.sleep(1000);

        } catch (InterruptedException e) { }
    }
}



class Lector extends Thread {
    private Buzon buzon;

    public Lector(Buzon a, String nombre) {
        super(nombre);
        this.buzon = a;
    }

    @Override
    public void run() {
        try {

                buzon.leer(getName());
                Thread.sleep(2000);

        } catch (InterruptedException e) { }
    }
}



class Buzon {
    private String mensajeBuzon = null;

    public synchronized void escribir(String mensaje, String persona) throws InterruptedException {
        while (mensajeBuzon != null) {
            System.out.println("El buzon esta lleno,"+persona+" espere a que alguien lo lea...\n");
            wait();
        }
        mensajeBuzon = mensaje;
        System.out.println(persona+" escribio el siguiente mensaje: " + mensaje+"\n");
        notifyAll();
    }

    public synchronized String leer(String persona) throws InterruptedException {
        while (mensajeBuzon == null) {
            System.out.println("Buzon vacio,"+persona+" espere a recibir un mensaje...\n");
            wait();
        }
        String mensaje = mensajeBuzon;
        mensajeBuzon = null;
        System.out.println(persona+"leyó el siguiente mensaje: " + mensaje+"\n");
        notifyAll();
        return mensaje;
    }
}