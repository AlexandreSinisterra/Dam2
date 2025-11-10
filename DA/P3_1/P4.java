package P3_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class P4 {
    private static final String url = "jdbc:postgresql://10.0.9.194:5432/probas";
    private static final String user = "postgres";
    private static final String contraseña = "admin";

    public static void main(String[] args) throws SQLException, IOException {
        Connection bD = conexion();
        crear(bD);
        insertar(bD);
        String codigos = copiarArchivo("codigosUnidades.txt");
        String texto = select2(bD, codigos);
        escribirTexto("puntosOrdeados.txt",texto);
    }

    static Connection conexion() throws SQLException {
        Connection prueba1 = null;
        try {
            prueba1 = DriverManager.getConnection(url,user,contraseña);
            System.out.println("conexion bien");
        }catch (SQLException e) {
            System.out.println(e);
            System.out.println("conexion fallida");
        }

        return prueba1;
    }

    public static void crear(Connection prueba1) throws SQLException {
        String sql = "CREATE TABLE adeptaSororitas (" +
                "cod integer, " +
                "nome varchar(15), " +
                "puntos integer" +
                ")";
        prueba1.createStatement().execute(sql);
    }

    public static void insertar(Connection prueba1) throws SQLException {
        String sql = "insert into adeptaSororitas VALUES " +
                "('0','squad',105)," +
                "('1','dominion',125)," +
                "('2','seraphim',90)," +
                "('3','zephyrim',85),"+
                "('4','exorcist',190),"+
                "('5','celestine',160),"+
                "('6','imagifier',65);";
        prueba1.createStatement().executeUpdate(sql);
    }

    public static String copiarArchivo(String ruta) throws IOException {
        String lectura;
        String contenido = "";
        String contenido2 = "";
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            while ((lectura = br.readLine()) != null) {
                contenido += (lectura + ",");
            }
        } catch (IOException e) {
        }
        for (int i=0; i<(contenido.length()-1);i++){
            contenido2 += contenido.charAt(i);
        }
        return contenido2;
    }

    public static String select2(Connection prueba1, String codigos) throws SQLException {
        String texto="";
        String sql = "select * from adeptaSororitas where cod in ("+codigos+") order by puntos desc";
        ResultSet salida = prueba1.createStatement().executeQuery(sql);
        while (salida.next()){
            System.out.printf(salida.getString("nome"));
            System.out.printf(" - ");
            System.out.println(salida.getString("puntos"));
            texto += (salida.getString("nome")+" - "+salida.getString("puntos")+"\n");
        }
        return texto;
    }

    public static void escribirTexto(String ruta, String texto) {
        try (FileWriter fw = new FileWriter(ruta)) {
            fw.write(texto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
