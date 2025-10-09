package P1_3;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args){

        String ruta1 = "C:\\Users\\sanda\\IdeaProjects\\Dam2\\DA\\P1_3\\serial.txt";
        String ruta2 = "C:\\Users\\sanda\\IdeaProjects\\Dam2\\DA\\P1_3\\autores.xml";

        Producto producto1 = new Producto("filipinos", 10, 2.0);
        ProductoTrans producto2 = new ProductoTrans("Monster",16 , 1.60);

        escribirLeerProductos(ruta1, producto1);
        escribirLeerProductos(ruta1, producto2);

        xmlEscribir(ruta2);
    }

    public static void escribirLeerProductos (String ruta, Object tipo){

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {

            oos.writeObject(tipo);

        } catch (IOException e) {
            System.out.println("error, no se pudo almacenar el objeto");
        }

        tipo = null;

        try {ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));

            tipo = ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("error, no se pudo leer el objeto almacenado");
        }
        System.out.println("\n producto: " + tipo);
    }

    public static void xmlEscribir (String ruta){
        XMLOutputFactory XMLopf = XMLOutputFactory.newInstance();
        try {
            XMLStreamWriter XMLsw =  XMLopf.createXMLStreamWriter(new FileWriter(ruta));
            XMLsw.writeStartDocument("1.0");

            XMLsw.writeStartElement("autores");

                XMLsw.writeStartElement("autor");
                XMLsw.writeAttribute("codigo", "a1");

                    XMLsw.writeStartElement("nombre");
                    XMLsw.writeCharacters("Alexandre Dumas");
                    XMLsw.writeEndElement();

                    XMLsw.writeStartElement("titulo");
                    XMLsw.writeCharacters("El conde de montecristo");
                    XMLsw.writeEndElement();

                    XMLsw.writeStartElement("titulo");
                    XMLsw.writeCharacters("Los miserables");
                    XMLsw.writeEndElement();

                XMLsw.writeEndElement();

                XMLsw.writeStartElement("autor");
                XMLsw.writeAttribute("codigo", "a2");

                    XMLsw.writeStartElement("nombre");
                    XMLsw.writeCharacters("Fiodor Dostoyevski");
                    XMLsw.writeEndElement();

                    XMLsw.writeStartElement("titulo");
                    XMLsw.writeCharacters("El idiota");
                    XMLsw.writeEndElement();

                    XMLsw.writeStartElement("titulo");
                    XMLsw.writeCharacters("Noches blancas");
                    XMLsw.writeEndElement();

                XMLsw.writeEndElement();

            XMLsw.writeEndElement();

            XMLsw.close();

            System.out.println("\n se escribio correctamente el xml");

        } catch (Exception e) {
            System.out.println("\n error al intentar escribir el xml");        }
    }
}
