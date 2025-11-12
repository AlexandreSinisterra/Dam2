package ejercicios.Tarea27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GestordeClientes extends Thread{
    private int nCliente;
    private Socket socket;

    public GestordeClientes(Socket socket, int nCliente){
        this.nCliente = nCliente;
        this.socket = socket;
    }

    @Override
    public void run() {

        try(BufferedReader recibir = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter enviar = new PrintWriter(socket.getOutputStream(), true)) {

            String recibido;

            while (true) {
                recibido = recibir.readLine();
                System.out.println("cliente "+nCliente+": "+recibido);

                if (recibido.equalsIgnoreCase("")) {
                    System.out.println("se ha detectado un mensaje vacio, cerrando bucle del servidor");
                    break;
                }

                if (recibido.equalsIgnoreCase("adios")) {
                    break;
                }

                enviar.println("ECO: " + recibido);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
                System.out.println("Cliente "+nCliente+" desconectado");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
