````mermaid
sequenceDiagram
    participant Cliente
    participant Servidor

    Cliente->>Servidor: Request de conexión al server
    Servidor->>Cliente: Acepta la conexion y espera a una operacion

    loop 
        Cliente->>Servidor: Envia la operacion
        Servidor->>Servidor: Valida y procesa la operacion
        Servidor->>Cliente: envia el resultado o un error y espera a un mensaje
        Cliente->>Cliente: muestra el mensaje por pantalla
    end

    Cliente->>Servidor: Envia "salir"
    Servidor->>Cliente: "Cerrando calculadora"
    Servidor->>Cliente: Cierra la conexión
    Cliente->>Cliente: Cierrar el socket
````