package ejercicios;

public class Tarea21 {
    public static void main(String[] args) {

        Parking garaje = new Parking();
        Coche[] coches = new Coche[30];

        for (int i = 0; i < 30; i++) {
            coches[i] = new Coche(garaje, i);
            coches[i].start();
        }

    }
}



class Coche extends Thread {
    private Parking garaje;
    private int numero;

    public Coche(Parking a, int numero) {
        this.garaje = a;
        this.numero = numero;
    }

    @Override
    public void run() {
        try {
            garaje.escribir(numero);
            Thread.sleep(1000);

        } catch (InterruptedException e) { }
    }
}

class Parking {
    private int[][] garaje = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
    };

    public synchronized void escribir(int mensaje) throws InterruptedException {
        int plazaLibre;
        int filaPlaza;
        int columnaPlaza;

        for (int i = 0; i < garaje.length; i++) {
            for (int j = 0; j < garaje[i].length; j++) {
                plazaLibre = garaje[i][j];
                if (plazaLibre == 0) {
                    filaPlaza = i;
                    columnaPlaza = j;
                }
            }
        }

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