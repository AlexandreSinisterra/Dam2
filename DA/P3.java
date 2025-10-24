import java.math.BigDecimal;
import java.sql.Connection; // CONEXION ACTIVA CON BD
import java.sql.DriverManager; // CONTROLA DRIVERS PERMITE CREAR CONEXIONES
import java.sql.SQLException; // EXCEPCIÓN EN CASO EN CASO DE ERROR CON BD ( CREDENCIALES.. )
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Collections;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
// ESTA CLASE USA LA LIBRERÍA DE POSTGRES, GRACIAS A ESTE LIBRERÍA PODEMOS IMPORTAR
// CLASES QUE NOS PERMITEN ESTABLECER LA CONEXIÓN
class P3{
    public static void main(String[] args) {

        // CREAMOS LAS INSTANCIAS DAO PARA ACCEDER A LA BASE DE DATOS
        VehiculoDAO vehiculoDAO = new VehiculoDAO();
        InventarioTendaDAO inventarioDAO = new InventarioTendaDAO();

        // INSERTAMOS LOS VEHÍCULOS INDICADOS EN EL ENUNCIADO... ( SIN Id PORQUE ES AUTOGENERADO )
        System.out.println("Insertando vehículos...");
        vehiculoDAO.insertar(new Vehiculo("Ford Mustang", "Ford", 2021, "Deportivos americanos icónicos."));
        vehiculoDAO.insertar(new Vehiculo("Tesla Model S", "Tesla", 2023, "Sedán eléctrico de luxo con gran autonomía."));
        vehiculoDAO.insertar(new Vehiculo("Honda Civic", "Honda", 2020, "Compacto de gran fiabilidade."));
        vehiculoDAO.insertar(new Vehiculo("Chevrolet Corvette", "Chevrolet", 2022, "Deportivo americano con motor V8."));
        vehiculoDAO.insertar(new Vehiculo("Toyota Prius", "Toyota", 2022, "Híbrido de baixo consumo e ecolóxico."));

        // CONSULTAMOS LOS Ids DE LOS VEHÍCULOS INSERTADOS ANTERIORMENTE
        System.out.println("\n IDs de los vehículos insertados:");

        // HACEMOS LA LISTA vehiculos DE vehiculo QUE ES IGUAL A LA INSTANCIA DAO CREADA ANTERIORMENTE USANDO EL MÉTODO leerTodos
        List<Vehiculo> vehiculos = vehiculoDAO.leerTodos();

        // CREAMOS BUCLE QUE ITERARÁ EN LA LISTA CREADA ANTERIORMENTE IMPRIMIENDO EL ID DE CADA COCHE ( JUNTO CON EL MODELO PARA VERLO CON CLARIDAD )
        for (Vehiculo v : vehiculos) {
            System.out.println("ID: " + v.getId() + " → " + v.getModelo());
        }

        // INSERTAMOS LOS DATOS SOLICITADOS EN InventarioTenda
        System.out.println("\nInsertando en InventarioTenda...");

        // VERIFICAMOS QUE HAYA AL MENOS 5 VEHÍCULOS
        if (vehiculos.size() >= 5) {

            // DESGLOSAMOS LO SIGUIENTE ->
            // LE INDICAMOS A LA BASE QUE TIENE QUE INSERTAR LO SIGUIENTE
            // LA VARIABLE vehiculos ( List<vehiculo> vehiculos=...) CONTIENE 5 OBJETOS
            // UNO POR CADA COCHE INSERTADO
            // AL NO HABER OTROS COCHES EN LA TABLA leerTodos DEVUELVE EN ORDEN DE Id CRECIENTE
            // POR LO TANTO SABEMOS QUE EN LA POSICION 0 ESTÁ LA PRIMERA INSERCIÓN -> MUSTANG
            // LA BASE PONDRÁ EL ID DEL INVENTARIO, NOSOTROS SOLO LE PASAMOS EL Id DEL VEHÍCULO
            // LA INSTANCIA DAO EJECUTARÁ LA INSERCIÓN SQL QUE HAY EN EL MÉTODO INSERTAR DE LA CLASE
            inventarioDAO.insertar(new InventarioTienda(
                    vehiculos.get(0).getId(), // MUSTANG
                    new BigDecimal("25000.00"),
                    new BigDecimal("30000.00"),
                    10
            ));
            inventarioDAO.insertar(new InventarioTienda(
                    vehiculos.get(1).getId(), // TESLA
                    new BigDecimal("40000.00"),
                    new BigDecimal("50000.00"),
                    12
            ));
            inventarioDAO.insertar(new InventarioTienda(
                    vehiculos.get(2).getId(), // HONDA
                    new BigDecimal("18000.00"),
                    new BigDecimal("22000.00"),
                    5
            ));
            inventarioDAO.insertar(new InventarioTienda(
                    vehiculos.get(3).getId(), // CHEVROLET
                    new BigDecimal("60000.00"),
                    new BigDecimal("70000.00"),
                    8
            ));
            inventarioDAO.insertar(new InventarioTienda(
                    vehiculos.get(4).getId(), // TOYOTA
                    new BigDecimal("25000.00"),
                    new BigDecimal("30000.00"),
                    6
            ));
        } else {
            System.err.println("No se encontraron suficientes vehículos para vincular al inventario.");
            return;
        }

        // EXPORTAR A XML
        System.out.println("\nExportando vehículos a XML...");
        // SOLO TENDREMOS QUE PASARLE POR PARÁMETRO LA LISTA YA CARGADA Y EL NOMBRE DEL XML DONDE QUEREMOS QUE SE EXPORTE
        ExportadorXML.exportarVehiculosXML(vehiculos, "vehiculos.xml");

        // SERIALIZAMOS EL FICHERO A BINARIO ( MEDIANTE LA INSTANCIA CREADA ANTERIORMENTE )
        System.out.println("\nSerializando vehículos a fichero...");
        Serializador.serializarVehiculos(vehiculos, "vehiculos.dat");

        // MONSTRAMOS EL MENSAJE OFERTAS COCHES (?)
        System.out.println("\nOFERTAS COCHES");


        System.out.println("\nAumentando ofertas en 15 puntos...");
        // DESPUÉS DE LA SIGUIENTE LINEA LA VARIABLE inventarios CONTIENE 5 OBJETOS
        // UNO POR CADA COCHE ( CON SUS DATOS ANTES DEL AUMENTO
        List<InventarioTienda> inventarios = inventarioDAO.leerTodos();
        // CREAMOS UN BUCLE PARA RECORRER EL REGISTRO
        // POR CADA COCHE EN EL INVENTARIO SE HACE LO SIGUIENTE
        for (InventarioTienda i : inventarios) {
            // OBTIENE EL VALOR ACTUAL DEL PORCENTAJE
            // LO INCREMENTA 15 UNTOS
            // Y CAMBIA EL VALOR EN EL OBJETO ( EN MEMORIA )
            i.setPorcentaxeOferta(i.getPorcentaxeOferta() + 15);
            // GUARDA EL CAMBIO EN LA BASE DE DATOS
            inventarioDAO.actualizar(i);
        }


        System.out.println("\nPrecio actual del Ford Mustang:");
        // OBTIENE EL REGISTRO DE INVENTARIO DE FORD ( USA MISMO Id QUE ANTES )
        // OBTENEMOS FORD SEGÚN EL ID, vehiculos.get(0).getId()
        // BUSCAMOS REGISTRO EN INVENTARIO CON List<InventarioTienda> inventarioFord = inventarioDAO.leerPorVehiculo('ANTERIOR LINEA')
        List<InventarioTienda> inventarioFord = inventarioDAO.leerPorVehiculo(vehiculos.get(0).getId());
        // SI EL FICHERO NO ESTÁ VACÍO
        if (!inventarioFord.isEmpty()) {

            InventarioTienda inv = inventarioFord.get(0);

            // OBTENEMOS PRECIO VENTA
            BigDecimal prezoVenta = inv.getPrezoVenta();

            // OBTENEMOS EL PORCENTAJE ( EL CUAL TIENE +15 PUNTOS SOBRE EL VALOR INICIAL ACTUALMENTE )
            int porcentaxe = inv.getPorcentaxeOferta();

            // DESCUENTO = ? , PREZOVENTA ( 30000 ) , PORCENTAXE 25 , DIVIDIR ENTRE 100 ( DESCUENTO )
            // 30000 * 25 / 100
            BigDecimal descuento = prezoVenta.multiply(BigDecimal.valueOf(porcentaxe)).divide(BigDecimal.valueOf(100));

            // PRECIO FINAL ( ACTUAL ) = 30000 - 7500 = 22500
            BigDecimal precioFinal = prezoVenta.subtract(descuento);
            System.out.printf("   Prezo venta: %.2f€ | Oferta: %d%% | Precio final: %.2f€\n",
                    prezoVenta, porcentaxe, precioFinal);
        } else {
            System.out.println("No se encontró inventario para el Ford Mustang.");
        }

        // ABRE EL FICHERO vehiculos.dat CREADO ANTERIORMENTE
        // LEE EL OBJETO BINARIO QUE CONTIENE UNA List<Vehiculo>
        // LO CONVIERTE DE NUEVO EN UNA LISTA DE OBJETOS DE JAVA Y DEVUELVE LA LISTA
        System.out.println("\nLeyendo vehículos del fichero serializado...");
        List<Vehiculo> vehiculosDeserializados = Deserializador.deserializarVehiculos("vehiculos.dat");

        System.out.println("\nEscribiendo vehículos en fichero de texto...");
        // CREA O SOBRESCRIBE UN FICHERO LLAMADO vehiculos_leidos.txt
        // PRINTWRITTER NOS PERMITE ESCRIBIR LÍNEAS DE TEXTO FÁCILMENTE
        try (PrintWriter pw = new PrintWriter(new FileWriter("vehiculos_leidos.txt"))) {
            // ESCRIBIMOS CADA VEHÍCULO
            for (Vehiculo v : vehiculosDeserializados) {
                pw.println(v.toString());
            }
            System.out.println("Fichero de texto generado: vehiculos_leidos.txt");
        } catch (IOException e) {
            System.err.println("Error al escribir fichero de texto: " + e.getMessage());
        }

        System.out.println("\nSimulacro completado con éxito.");
    }
}

class Conexion {

    private static final String URL = "jdbc:postgresql://IPMAQUINA:5432/simulacro";
    private static final String USUARIO = "saul";
    private static final String PASS = "admin";

    // ESTÁTICO PARA LLAMARLO SIN CREAR UNA INSTANCIA DE LA CLASE ( Connection conex = Conexion.conexionBase(); )
    public static Connection conexionBase() {
        try {
            // UTILIZAMOS EL DRIVER DE POSTGRES
            Class.forName("org.postgresql.Driver");

            // MEDIANTE LOS PARÁMETROS DEFINIDOS ANTERIORMENTE DEFINIMOS LA CONEXIÓN
            Connection conex = DriverManager.getConnection(URL, USUARIO, PASS);
            System.out.println("Conexión exitosa con la base de datos");

            // SE DEVUELVE LA CONEXIÓN
            return conex;

        } catch (ClassNotFoundException e) {
            // EN CASO DE QUE NO ENCUENTRE EL DRIVER ( ERROR DE LA CLASE )
            System.out.println("Error: driver PostgreSQL no encontrado: " + e.getMessage());
            return null;

        } catch (SQLException e) {
            // ERROR AL CONECTAR A LA BASE DEBIDO A ALGÚN ERROR EN LOS PARÁMETROS, RED...
            System.out.println("Error al conectar: " + e.getMessage());
            return null;

        }
    }
}

class Vehiculo {

    // ATRIBUTOS DE LA TABLA
    private int id;
    private String modelo;
    private String marca;
    private int ano;
    private String descripcion;

    // CONSTRUCTOR VACÍO NECESARIO PARA FRAMEWORKS Y PARA LEER DATOS DE LA BD Y COMPLETARLOS POCO A POCO
    public Vehiculo() {}

    // CONSTRUCTOR DE LA CLASE SIN ID YA QUE LA BASE DE DATOS LO GENERA AUTOMÁTICAMENTE
    // EJEMPLO DE USO : Vehiculo v = new Vehiculo("Mustang", "Ford", 2021, "Deportivos americanos icónicos.");
    public Vehiculo(String modelo, String marca, int ano, String descripcion) {
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.descripcion = descripcion;
    }


    // GETTERS Y SETTERS QUE NOS PERMITAN ACCEDER Y MODIFICAR LOS ATRIBUTOS DESDE OTRAS CLASES
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }


    // MÉTODO PARA IMPRIMIR EL OBJETO FACILMENTE : "System.out.println(miVehiculo);"
    @Override
    public String toString() {
        return "Vehiculo [id=" + id + ", modelo=" + modelo + ", marca=" + marca +
                ", ano=" + ano + ", descripcion=" + descripcion + "]";
    }
}

class InventarioTienda {
    // CLAVE PRIMARIA AUTOGENERADA
    private int id;
    // CLAVE FORÁNEA
    private int idVehiculo;
    private BigDecimal prezoMayorista;
    private BigDecimal prezoVenta;
    private int porcentaxeOferta;


    // DOS CONSTRUCTORES, UNO CON ID PARA LEER LA BASE DE DATOS ( SELECT ) Y OTRO VACÍO

    // PARA INSERTAR SIN EL CONSTRUCTOR "sin id" ->

    // InventarioTienda inv = new InventarioTienda(); // CONSTRUCTOR VACÍO
    // inv.setIdVehiculo(idVehiculo);
    // inv.setPrezoMayorista(mayorista);
    // inv.setPrezoVenta(venta);
    // inv.setPorcentaxeOferta(oferta);
    // el 'id' se queda en 0 (no importa, porque la BD lo genera)

    public InventarioTienda(){}

    public InventarioTienda(int id, int idVehiculo, BigDecimal prezoMayorista, BigDecimal prezoVenta, int porcentaxeOferta) {
        this.id = id;
        this.idVehiculo = idVehiculo;
        this.prezoMayorista = prezoMayorista;
        this.prezoVenta = prezoVenta;
        this.porcentaxeOferta = porcentaxeOferta;
    }

    public InventarioTienda( int idVehiculo, BigDecimal prezoMayorista, BigDecimal prezoVenta, int porcentaxeOferta) {
        this.idVehiculo = idVehiculo;
        this.prezoMayorista = prezoMayorista;
        this.prezoVenta = prezoVenta;
        this.porcentaxeOferta = porcentaxeOferta;
    }

    // GETTERS Y SETTERS
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdVehiculo() { return idVehiculo; }
    public void setIdVehiculo(int idVehiculo) { this.idVehiculo = idVehiculo; }

    public BigDecimal getPrezoMayorista() { return prezoMayorista; }
    public void setPrezoMayorista(BigDecimal prezoMayorista) { this.prezoMayorista = prezoMayorista; }

    public BigDecimal getPrezoVenta() { return prezoVenta; }
    public void setPrezoVenta(BigDecimal prezoVenta) { this.prezoVenta = prezoVenta; }

    public int getPorcentaxeOferta() { return porcentaxeOferta; }
    public void setPorcentaxeOferta(int porcentaxeOferta) { this.porcentaxeOferta = porcentaxeOferta; }

    @Override
    public String toString() {
        return "InventarioTenda [id=" + id + ", idVehiculo=" + idVehiculo +
                ", prezoMayorista=" + prezoMayorista +
                ", prezoVenta=" + prezoVenta +
                ", porcentaxeOferta=" + porcentaxeOferta + "]";
    }
}


// PERMITE HACER CRUD ( Crear, Leer, Actualizar, Eliminar ) SOBRE LA TABLA
class VehiculoDAO {
    public boolean insertar(Vehiculo vehiculo) {
        // INSERCIÓN SQL, NO INCLUYE ID PORQUE ES SERIAL ( AUTOGENERADA EN POSRGRES )
        String sql = "INSERT INTO Vehiculo (Modelo, Marca, Ano, Descripcion) VALUES (?, ?, ?, ?)";

        // ACTIVAMOS LA CONEXIÓN
        try (Connection conn = Conexion.conexionBase();

             // LE DECIMOS A JAVA QUE NOS PREPARE LA CONSULTA INDICADA PARA EJECUTARLA LUEGO
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // RELLENAMOS LOS HUECOS CON MÉTODOS COMO setString SetInt...
            // LA PRIMERA '?' -> Modelo ( 1er PARÁMETRO DE LA TABLA )... ASÍ SUCESÍBAMENTE...
            ps.setString(1, vehiculo.getModelo());
            ps.setString(2, vehiculo.getMarca());
            ps.setInt(3, vehiculo.getAno());
            ps.setString(4, vehiculo.getDescripcion());

            // PARA INSERT, UPDATE, DELETE -> executeUpdate(); ( PARA SELECT -> ps.executeQuery )
            // EJECUTA INSERT
            int filasAfectadas = ps.executeUpdate();
            // DEVUELVE CUANTAS FILAS FUERON INSERTADAS
            return filasAfectadas > 0;
            // SI TODO ESTÁ BIEN DEVUELVE TRUE
        } catch (SQLException e) {
            System.err.println("Error al insertar vehículo: " + e.getMessage());
            return false;
        }
    }

    // MÉTODO PARA LEER TODOS LOS VEHÍCULOS DE LA BASE ( DEVUELVE UNA LISTA DE OBJETOS )
    public List<Vehiculo> leerTodos() {
        // CREA UNA LISTA PARA GUARDAR LOS RESULTADOS
        List<Vehiculo> vehiculos = new ArrayList<>();
        // CONSULTA QUE TRAE TODAS LAS COLUMNAS Y FILAS DE LA TABLA
        String sql = "SELECT * FROM Vehiculo";
        // ABRIMOS LA CONEXIÓN, CREAMOS UN STATEMENT ( NO NECESITA '?' ASÍ QUE NO USA PREPAREDSTATEMENT ) )
        // AL EJECUTAR LA CONSULTA SE GUARDA EL RESULTADO EN 'rs' ( ResultSet )
        try (Connection conn = Conexion.conexionBase();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // rs.next() ITERA POR LAS FILAS, ESTE PROCESO SE REPITE MIENTRAS EXISTAN FILAS
            while (rs.next()) {
                Vehiculo v = new Vehiculo();
                // USA SETTERS PARA ASIGNAR LOS VALORES QUE VIENEN DE LA BASE, ASIGNÁNDOLOS EN LA LISTA v
                v.setId(rs.getInt("Id"));
                v.setModelo(rs.getString("Modelo"));
                v.setMarca(rs.getString("Marca"));
                v.setAno(rs.getInt("Ano"));
                v.setDescripcion(rs.getString("Descripcion"));
                vehiculos.add(v);
            }
        }
        // SI HAY ERROR, LO MUESTRA, PERO NO LANZA LA EXCEPCIÓN, DEVUELVE EN TODO CASO LA LISTA ( PUDIENDO ESTA ESTAR VACÍA )
        catch (SQLException e) {
            System.err.println("Error al leer todos los vehículos: " + e.getMessage());
        }
        return vehiculos;
    }

    // BUSCAR VEHÍCULOS POR NOMBRE DE MODELO
    public List<Vehiculo> leerPorModelo(String modelo) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        // CONSULTA CON CONDICIÓN, USA '?' PORQUE EL VALOR DE MODELO ES VARIABLE EN ESTA CONSULTA
        String sql = "SELECT * FROM Vehiculo WHERE Modelo = ?";
        try (Connection conn = Conexion.conexionBase();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // RELLENA '?' CON EL VALOR DE MODELO ( MODELO SE ENCUENTRA EN LA PRIMERA POSICIÓN DE LA TABLA )
            ps.setString(1, modelo);
            ResultSet rs = ps.executeQuery();

            // IGUAL QUE EN EL ANTERIOR MÉTODO, CREA OBJETOS Y LOS AÑADE A LA LISTA
            while (rs.next()) {
                Vehiculo v = new Vehiculo();
                v.setId(rs.getInt("Id"));
                v.setModelo(rs.getString("Modelo"));
                v.setMarca(rs.getString("Marca"));
                v.setAno(rs.getInt("Ano"));
                v.setDescripcion(rs.getString("Descripcion"));
                vehiculos.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar vehículos por modelo: " + e.getMessage());
        }
        return vehiculos;
    }

    // MÉTODO QUE ACTUALIZA UN VEHÍCULO EXISTENTE MEDIANTE SU 'id'
    public boolean actualizar(Vehiculo vehiculo) {
        // ACTUALIZA TODOS LOS CAMPOS EXCEPTO 'id', QUE USA WHERE
        String sql = "UPDATE Vehiculo SET Modelo = ?, Marca = ?, Ano = ?, Descripcion = ? WHERE Id = ?";
        try (Connection conn = Conexion.conexionBase();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // RELLENA LOS 5 PARÁMETROS, LOS PRIMEROS 4, VALORES NUEVOS, EL 5º SE TRATA DEL 'id' DEL VEHÍCULO A ACTUALIZAR
            ps.setString(1, vehiculo.getModelo());
            ps.setString(2, vehiculo.getMarca());
            ps.setInt(3, vehiculo.getAno());
            ps.setString(4, vehiculo.getDescripcion());
            ps.setInt(5, vehiculo.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar vehículo: " + e.getMessage());
            return false;
        }
    }


    // BORRAR UN VEHÍCULO DE LA TABLA SEGÚN SU ID
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Vehiculo WHERE Id = ?";
        try (Connection conn = Conexion.conexionBase();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar vehículo: " + e.getMessage());
            return false;
        }
    }
}

class InventarioTendaDAO {

    public boolean insertar(InventarioTienda inventario) {
        String sql = "INSERT INTO InventarioTenda (IdVehiculo, PrezoMayorista, PrezoVenta, PorcentaxeOferta) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexion.conexionBase();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, inventario.getIdVehiculo());
            ps.setBigDecimal(2, inventario.getPrezoMayorista());
            ps.setBigDecimal(3, inventario.getPrezoVenta());
            ps.setInt(4, inventario.getPorcentaxeOferta());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar en inventario: " + e.getMessage());
            return false;
        }
    }

    public List<InventarioTienda> leerTodos() {
        List<InventarioTienda> inventarios = new ArrayList<>();
        String sql = "SELECT * FROM InventarioTenda";
        try (Connection conn = Conexion.conexionBase();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                InventarioTienda i = new InventarioTienda(
                        rs.getInt("Id"),
                        rs.getInt("IdVehiculo"),
                        rs.getBigDecimal("PrezoMayorista"),
                        rs.getBigDecimal("PrezoVenta"),
                        rs.getInt("PorcentaxeOferta")
                );
                inventarios.add(i);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer todo el inventario: " + e.getMessage());
        }
        return inventarios;
    }

    public List<InventarioTienda> leerPorVehiculo(int idVehiculo) {
        List<InventarioTienda> inventarios = new ArrayList<>();
        String sql = "SELECT * FROM InventarioTenda WHERE IdVehiculo = ?";
        try (Connection conn = Conexion.conexionBase();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idVehiculo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                InventarioTienda i = new InventarioTienda(
                        rs.getInt("Id"),
                        rs.getInt("IdVehiculo"),
                        rs.getBigDecimal("PrezoMayorista"),
                        rs.getBigDecimal("PrezoVenta"),
                        rs.getInt("PorcentaxeOferta")
                );
                inventarios.add(i);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar inventario por vehículo: " + e.getMessage());
        }
        return inventarios;
    }

    public boolean actualizar(InventarioTienda inventario) {
        String sql = "UPDATE InventarioTenda SET PrezoMayorista = ?, PrezoVenta = ?, PorcentaxeOferta = ? WHERE Id = ?";
        try (Connection conn = Conexion.conexionBase();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBigDecimal(1, inventario.getPrezoMayorista());
            ps.setBigDecimal(2, inventario.getPrezoVenta());
            ps.setInt(3, inventario.getPorcentaxeOferta());
            ps.setInt(4, inventario.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar inventario: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM InventarioTenda WHERE Id = ?";
        try (Connection conn = Conexion.conexionBase();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar inventario: " + e.getMessage());
            return false;
        }
    }
}


class Serializador {

    // LISTA DE COCHES A GUARDAR -> lista de coches a guardar - vehiculos, vehiculos.dat
    public static boolean serializarVehiculos(List<Vehiculo> vehiculos, String nombreFichero) {

        // OBJECT OUTPUT ENVUELVE EL FLUJO PARA PODER ESCRIBIR OBJETOS, FILEOUTPUT VA A CREAR O ABRIR EL FICHERO EN MODO ESCRITURA BINARIA
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreFichero))) {

            // ESCRIBE TODA LA LISTA COMO UN SOLO OBJETO EN EL FICHERO
            oos.writeObject(vehiculos);
            System.out.println("Vehículos serializados en: " + nombreFichero);
            return true;

        } catch (IOException e) {
            System.err.println("Error al serializar vehículos: " + e.getMessage());
            return false;
        }
    }
}

class Deserializador {

    // NECESARIO PORQUE JAVA NO PUEDE VERIFICAR EN TIEMPO DE COMPILACIÓN QUE EL OBJETO LEÍDO ES List<Vehiculo>
    @SuppressWarnings("unchecked")
    // DEVUELVE UNA LISTA
    public static List<Vehiculo> deserializarVehiculos(String nombreFichero) {
        // ABRE EL FICHERO EN MODO LECTURA BINARIA ( ObjectIn.. -> PERMITE LEER OBJETOS EN JAVA, FileInputSream -> ABRE EL FICHERO EN MODO LECTURA BINARIA
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreFichero))) {

            // LEE EL OBJETO DEL FICHERO -> DEBE SER LA LISTA
            List<Vehiculo> vehiculos = (List<Vehiculo>) ois.readObject();

            // DEVUELVE LA DESERIALIZACIÓN EN EL FICHERO INDICADO
            System.out.println("Vehículos deserializados desde: " + nombreFichero);
            return vehiculos;

        } catch (IOException e) {
            System.err.println("Error de E/S al deserializar: " + e.getMessage());

        } catch (ClassNotFoundException e) {
            System.err.println("Clase no encontrada al deserializar (¿cambió Vehiculo?): " + e.getMessage());
        }

        // PARA DEVOLVER UNA LISTA VACÍA INMUTABLE SI FALLA
        return Collections.emptyList();
    }
}


class ExportadorXML {

    // CREAMOS LA LISTA VEHICULOS - VIENE DE LA PROPIA BASE DE DATOS
    public static void exportarVehiculosXML(List<Vehiculo> vehiculos, String nombreFichero) {

        // MENSAJE DE AVISO
        System.out.println("\n EXPORTANDO VEHÍCULOS A XML...\n");

        // ABRIMOS O CREAMOS UN FICHERO CON FileWritter
        try (FileWriter ficheroXML = new FileWriter(nombreFichero)) {
            // CREAMOS EL ESCRITOR XML, PRIMERO LA FÁBRICA ESTANDAR DE ESCRITORES
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            // AHORA EL ESCRITOR QUE USARÁ FILEWRITTER PARA GUARDAR EL XML
            XMLStreamWriter writer = factory.createXMLStreamWriter(ficheroXML);

            // ESCRIBIR LA DECLARACIÓN XML
            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeCharacters("\n");

            // ABRIMOS EL ELEMENTO RAÍZ
            writer.writeStartElement("vehiculos");
            writer.writeCharacters("\n");

            // BUCLE PARA CADA VEHÍCULO - RECORRE CADA VEHÍCULO DE LA LISTA
            for (Vehiculo v : vehiculos) {
                // ESCRIBIMOS DOS ESPACIOS PARA LA INDENTACIÓN
                writer.writeCharacters("  ");
                // ESCRIBIMOS EL ELEMENTO VEHÍCULO
                writer.writeStartElement("vehiculo");
                // AÑADE EL ATRIBUTO 'id', getId ES int ASÍ QUE SE CONVIERTE EN A STRING CON String.valueOf()
                writer.writeAttribute("id", String.valueOf(v.getId()));
                writer.writeCharacters("\n");

                // MISMO PROCESO PARA EL RESTO DE FILAS
                writer.writeCharacters("    ");
                writer.writeStartElement("modelo");
                writer.writeCharacters(v.getModelo());
                writer.writeEndElement();
                writer.writeCharacters("\n");

                writer.writeCharacters("    ");
                writer.writeStartElement("marca");
                writer.writeCharacters(v.getMarca());
                writer.writeEndElement();
                writer.writeCharacters("\n");

                writer.writeCharacters("    ");
                writer.writeStartElement("ano");
                writer.writeCharacters(String.valueOf(v.getAno()));
                writer.writeEndElement();
                writer.writeCharacters("\n");

                writer.writeCharacters("    ");
                writer.writeStartElement("descripcion");
                writer.writeCharacters(v.getDescripcion());
                writer.writeEndElement();
                writer.writeCharacters("\n");

                writer.writeCharacters("  ");
                // CERRAMOS EL ELEMENTO vehículo
                writer.writeEndElement();
                writer.writeCharacters("\n");
            }
            // CERRAMOS EL ELEMENTO vehículos
            writer.writeEndElement();
            // INDICAMOS QUE SE FINALIZA EL XML
            writer.writeEndDocument();
            // CERRAMOS EL ESCRITOR
            writer.close();

            System.out.println("XML generado: " + nombreFichero);

        } catch (Exception e) {
            System.err.println("Error al generar XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


