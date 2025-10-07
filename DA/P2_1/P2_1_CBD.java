package P2_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class P2_1_CBD {
    private static final String url = "jdbc:postgresql://10.0.2.15:5432/probas";
    private static final String user = "postgres";
    private static final String contraseña = "admin";

    public static void main(String[] args) throws SQLException {
        crearInsertar(conexion());
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

    public static void crearInsertar(Connection prueba1) throws SQLException {
        String sql = "CREATE TABLE anime (" +
                "nome VARCHAR(100), " +
                "descripcion TEXT, " +
                "data DATE, " +
                "puntuacion INTEGER" +
                ")";
        prueba1.createStatement().execute(sql);
        sql = "INSERT INTO anime (nome, descripcion, data, puntuacion) VALUES " +
                "('Evangelion', 'Serie de mechas que explora as emocións dos pilotos nunha ameaza global apocalíptica.', '1995-10-04', 95)," +
                "('Ghost In the Shell', 'Anime de ciencia ficción cibernética sobre intelixencia artificial e a identidade.', '1995-11-18', 92)," +
                "('Akira', 'Película postapocalíptica con acción e crítica social ambientada nunha Tokio futurista.', '1988-07-16', 90)," +
                "('Dragon Ball', 'Serie clásica de aventuras e artes marciais con personaxes icónicos e épicos combates.', '1986-02-26', 88);";
        prueba1.createStatement().execute(sql);
    }
}
