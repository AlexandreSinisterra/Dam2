package ejercicios;

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
        System.out.println(RESET);
    }


    public synchronized int[] aparcar(int coche) throws InterruptedException {
        int plazaLibre = 1;
        int filaPlaza = 0;
        int columnaPlaza = 0;
        int[] posicion= {0,0};

        do {
            for (int i = 0; i < garaje.length; i++) {
                for (int j = 0; j < garaje[i].length; j++) {
                    plazaLibre = garaje[i][j];
                    if (plazaLibre == 0) {
                        filaPlaza = i;
                        columnaPlaza = j;
                        break;
                    }
                }
                if (plazaLibre == 0) break;
            }
            if (plazaLibre == 0)break;
            else {
                System.out.println(RED+"Espere coche "+coche+", el garaje está lleno"+RESET);
                wait();
            }
        }while (true);

        garaje[filaPlaza][columnaPlaza] = coche;

        System.out.println("+ El coche " + coche + " aparcó en [" + filaPlaza + "][" + columnaPlaza + "]");

        posicion[0] = filaPlaza;
        posicion[1] = columnaPlaza;
        return posicion;
    }

    public synchronized void irse(int coche, int[] posicion) throws InterruptedException {
        garaje[posicion[0]][posicion[1]] = 0;
        System.out.println("- El coche " + coche + " se fue en [" + posicion[0] + "][" + posicion[1] + "]");
        notifyAll();
    }
}