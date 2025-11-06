package ejercicios;

import kotlin.jvm.Synchronized;

public class Tarea21 {
    public static void main(String[] args) throws InterruptedException {

        Parking garaje = new Parking();
        Coche[] coches = new Coche[30];

        for (int i = 0; i < 30; i++) {
            coches[i] = new Coche(garaje, i+1);
            coches[i].start();
        }

        while (true) {
            garaje.mostrarEstado();
            Thread.sleep(10000);
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
            while (true) {

                int[] plazas= garaje.aparcar(numero);
                try {
                    Thread.sleep((long) ((Math.random() * (20_000 - 10_000 + 1)) + 10_000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                garaje.irse(numero, plazas);
                try {
                    Thread.sleep((long) ((Math.random() * (20_000 - 10_000 + 1)) + 10_000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (InterruptedException e) { }
    }
}

class Parking {
    private int nPlazas=0;
    //private static final Object candadoEstado = new Object();
    /**
     *
     * Se deberia pedir el tamaño del garaje de antemano,
     * yo haria que solo te pudieran decir el tamaño de los lados,
     * asi podriamos seguir con un garaje rectangular. Despues pedir
     * la cantidad de coches que sea mayor a las plazas y listo.
     *
     * int[][] garaje = new int[alto][ancho];
     *
     * Despues lo llenamos de 0 con un array
     *
     */
    private int[][] garaje = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
    };
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";


    public synchronized void mostrarEstado() throws InterruptedException {

            System.out.println("\nEstado actual del garaje:\n"+GREEN);

            System.out.print("    ");
            for (int col = 0; col < garaje[0].length; col++) {
                System.out.printf(" %3d", col);
            }
            System.out.println();
            System.out.println("   +" + "---+".repeat(garaje[0].length));
       // synchronized (candadoEstado) {
        /**
         *
         * tengo que pensar como optimizar el printeo del garaje
         * ya que de la forma actual se bloquean los coches durante
         * todo el proceso.
         *
         * He imaginado la opcion de printearlo en un mensaje, pero no creo
         * que pueda ser muy extrapolable. Mi principal problema es que
         * si lo pongo el lock desde que se comienza a printear, algun coche podría
         * interactuar y estropear el mensaje que muestra el garaje, por ello el lock
         * y por eso queria una forma de hacerlo solo en un mismo mensaje.
         *
         * Tengo la opcion de printear la hora en todos los mensajes y que el garaje
         * por ejemplo lo muestre solo en punto, lockear solo la copia del array
         * garaje en otro garaje para poder manipularlo sin bloquear a los coches
         * asi el hilo se puede tomar su tiempo preparando el mensaje de muestra
         * de estado. De mientras, los coches seguiran saliendo y entrando, pero
         * nosotros al haber copiado el garaje e indicar la hora de ese estado,
         * no da igual.
         *
         */
            for (int i = 0; i < garaje.length; i++) {
                System.out.printf("%2d |", i);
                for (int j = 0; j < garaje[i].length; j++) {
                    if (garaje[i][j] == 0) {
                        System.out.print("  . ");
                    } else {
                        System.out.printf("%3d ", garaje[i][j]);
                    }
                }
                System.out.println();
            }
      //  }
        System.out.println(RESET);
    }


    public synchronized int[] aparcar(int coche) throws InterruptedException {
        int plazaLibre;
        int filaPlaza;
        int columnaPlaza;
        int[] posicion= {0,0};

        salir:
        do {
            if(nPlazas<30) {
                for (int i = 0; i < garaje.length; i++) {
                    for (int j = 0; j < garaje[i].length; j++) {
                        plazaLibre = garaje[i][j];
                        if (plazaLibre == 0) {
                            filaPlaza = i;
                            columnaPlaza = j;
                            break salir;
                        }
                    }
                }
            }
                System.out.println(RED+"Espere coche "+coche+", el garaje está lleno"+RESET);
                wait();
        }while (true);

        nPlazas++;
        garaje[filaPlaza][columnaPlaza] = coche;

        System.out.println("+ El coche " + coche + " aparcó en [" + filaPlaza + "][" + columnaPlaza + "]");

        posicion[0] = filaPlaza;
        posicion[1] = columnaPlaza;
        return posicion;
    }

    public synchronized void irse(int coche, int[] posicion) throws InterruptedException {
        nPlazas--;
        garaje[posicion[0]][posicion[1]] = 0;
        System.out.println("- El coche " + coche + " se fue en [" + posicion[0] + "][" + posicion[1] + "]");
        notifyAll();
    }
}