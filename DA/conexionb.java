import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class conexionb {

    // Configura los parámetros de conexión
    private static final String url = "jdbc:postgresql://10.0.2.15:5432/probas";
    private static final String user = "postgres";
    private static final String contraseña = "admin";

    public static void main(String[] args) throws SQLException {

        crearTablas();
        insertarDatos();

        }

    static Connection Conexionbd() throws SQLException {
        Connection conexion = null;
        try {

            System.out.println("Intentando conectar a la base de datos...");
            conexion = DriverManager.getConnection(url, user, contraseña);

            System.out.println("Conexión exitosa a la base de datos");

        } catch (SQLException e) {
            System.out.println(e);
        }

        return conexion;

    }
    static void crearTablas() {

        try (Connection conexion = DriverManager.getConnection(url, user, contraseña);
             Statement stmt = conexion.createStatement()) {

            System.out.println("Conexion echa");

            conexion.createStatement().execute("CREATE TABLE anime (" +
                    "nome VARCHAR(100), " +
                    "descripcion TEXT, " +
                    "data DATE, " +
                    "puntuacion INTEGER" +
                    ")");

            System.out.println("Tabla creada");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void insertarDatos() {
        try (Connection conexion = DriverManager.getConnection(url, user, contraseña);
             Statement stmt = conexion.createStatement()) {

            System.out.println("Conexion echa");

            conexion.createStatement().execute(
                    "INSERT INTO anime (nome, descripcion, data, puntuacion) VALUES " +
                            "('Evangelion', 'Serie de mechas que explora as emocións dos pilotos nunha ameaza global apocalíptica.', '1995-10-04', 95)," +
                            "('Ghost In the Shell', 'Anime de ciencia ficción cibernética sobre intelixencia artificial e a identidade.', '1995-11-18', 92)," +
                            "('Akira', 'Película postapocalíptica con acción e crítica social ambientada nunha Tokio futurista.', '1988-07-16', 90)," +
                            "('Dragon Ball', 'Serie clásica de aventuras e artes marciais con personaxes icónicos e épicos combates.', '1986-02-26', 88);"
            );
            System.out.println("Datos insertados");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
