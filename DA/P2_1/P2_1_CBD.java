package P2_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class P2_1_CBD {
    private static final String url = "jdbc:postgresql://10.0.2.15:5432/probas";
    private static final String user = "postgres";
    private static final String contraseña = "admin";

    public static void main(String[] args) throws SQLException {
        Connection bD = conexion();
        crear(bD);
        insertar(bD);
        select(bD);
        insertar2(bD);
        select2(bD);
        update(bD);
        delete(bD);
        select(bD);
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
        String sql = "CREATE TABLE anime (" +
                "nome VARCHAR(100), " +
                "descripcion TEXT, " +
                "data DATE, " +
                "puntuacion INTEGER" +
                ")";
        prueba1.createStatement().execute(sql);
    }

    public static void insertar(Connection prueba1) throws SQLException {
        String sql = "INSERT INTO anime (nome, descripcion, data, puntuacion) VALUES " +
                "('Evangelion', 'Serie de mechas que explora as emocións dos pilotos nunha ameaza global apocalíptica.', '1995-10-04', 95)," +
                "('Ghost In the Shell', 'Anime de ciencia ficción cibernética sobre intelixencia artificial e a identidade.', '1995-11-18', 92)," +
                "('Akira', 'Película postapocalíptica con acción e crítica social ambientada nunha Tokio futurista.', '1988-07-16', 90)," +
                "('Dragon Ball', 'Serie clásica de aventuras e artes marciais con personaxes icónicos e épicos combates.', '1986-02-26', 88);";
        prueba1.createStatement().executeUpdate(sql);
    }

    public static void select(Connection prueba1) throws SQLException {
        String sql = "select * from anime";
        ResultSet salida = prueba1.createStatement().executeQuery(sql);
        while (salida.next()) {
            System.out.println("");
            System.out.println("Nombre: " + salida.getString("nome"));
            System.out.println("Descripcion: " + salida.getString("descripcion"));
            System.out.println("Fecha: " + salida.getDate("data"));
            System.out.println("Puntuacion: " + salida.getInt("puntuacion"));
            System.out.println("");
        }
    }

    public static void insertar2(Connection prueba1) throws SQLException {
        String sql = "INSERT INTO anime (nome, descripcion, data, puntuacion) VALUES ('DevilMan Crybaby', 'Serie de una Profecia apocaliptica de demonios', '2018-01-05', 100);";
        prueba1.createStatement().executeUpdate(sql);
    }

    public static void select2(Connection prueba1) throws SQLException {
        String sql = "select * from anime where puntuacion = '100'";
        ResultSet salida = prueba1.createStatement().executeQuery(sql);
        while (salida.next()){
            System.out.println("Nome: " + salida.getString("nome"));
        }
    }

    public static void update(Connection prueba1) throws SQLException {
        String sql = "update anime set puntuacion = 95 where nome = 'DevilMan Crybaby'";
        prueba1.createStatement().executeUpdate(sql);
    }

    public static void delete(Connection prueba1) throws SQLException {
        String sql = "delete from anime where nome = 'Dragon Ball'";
        prueba1.createStatement().executeUpdate(sql);
    }

}
