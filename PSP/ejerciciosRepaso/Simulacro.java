package ejerciciosRepaso;

public class Simulacro {
    public static void main(String[] args) throws InterruptedException {
        Pista pista = new Pista();

        Tortuga tortuga = new Tortuga(pista,"tortuga");
        Liebre liebre = new Liebre(pista,"liebre");

        System.out.println("empieza la Stell Ball Run");

        tortuga.start();
        liebre.start();

        tortuga.join();
        liebre.join();

        System.out.println("el ganador es: "+pista.getGanador());
    }
}

class Tortuga extends Thread{
    Pista pista;

    public Tortuga(Pista pista, String nombre){
        super(nombre);
        this.pista=pista;
    }


    @Override
    public void run() {
        int decision;
        int casillas;
        boolean ganador=false;
        while (!ganador){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            decision = (int) ((Math.random()*100)+1);

            if (decision <= 50) casillas = 3;
            else if (decision <= 70) casillas = -6;
            else casillas = 1;

            ganador=pista.moverTortuga(casillas);
        }
    }
}

class Liebre extends Thread{
    Pista pista;

    public Liebre(Pista pista, String nombre){
        super(nombre);
        this.pista=pista;
    }


    @Override
    public void run() {
        int decision;
        int casillas;
        boolean ganador=false;
        while (!ganador){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            decision = (int) ((Math.random()*100)+1);

            if (decision<=20)casillas=0;
            else if (decision<=40)casillas=9;
            else if (decision<=50)casillas=-12;
            else if (decision<=80)casillas=1;
            else casillas=-2;

            ganador=pista.moverLiebre(casillas);
        }
    }
}

class Pista{
    private boolean carreraTerminada= false;
    private String ganador = null;
    private Boolean turnoExtra = true;
    private int posicionTortuga = 1;
    private int posicionLiebre = 1;

    public synchronized boolean moverLiebre(int casillas){
        if(carreraTerminada&&!(turnoExtra)) return true;
        if (carreraTerminada&&turnoExtra)turnoExtra= false;
        posicionLiebre+=casillas;
        if (posicionLiebre<1)posicionLiebre=1;

        System.out.println("liebre - "+posicionLiebre);

        return verificarGanador();
    }

    public synchronized boolean moverTortuga(int casillas){
        if(carreraTerminada&&!(turnoExtra)) return true;
        if (carreraTerminada&&turnoExtra)turnoExtra= false;
        posicionTortuga+=casillas;
        if (posicionTortuga<1)posicionTortuga=1;

        System.out.println("tortuga - "+posicionTortuga);

        return verificarGanador();
    }

    private synchronized boolean verificarGanador(){
            if (posicionLiebre >= 70 && posicionTortuga >= 70){
                ganador = "empate";
                return true;
            }else if (posicionTortuga >= 70) {
                carreraTerminada = true;
                ganador = "T";
                return true;
            } else if (posicionLiebre >= 70) {
                carreraTerminada = true;
                ganador = "L";
                return true;
            }
        return false;
    }

    public String getGanador() {
        return ganador;
    }
}