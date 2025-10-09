package P1_3;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        String ruta1 = "C:\\Users\\sanda\\IdeaProjects\\Dam2\\DA\\P1_3\\serial.txt";
        String ruta2 = "C:\\Users\\sanda\\IdeaProjects\\Dam2\\DA\\P1_3\\autores.xml";

        escribirLeerProductos(ruta1, "Monster",16 , 1.60, Producto.class);
        escribirLeerProductos(ruta1, "filipinos", 10, 2.0, ProductoTrans.class);
        xmlEscribir(ruta2);
    }

    public static <tipoClase> void escribirLeerProductos (String ruta, String nombre, int cantidad, double precio, Class<tipoClase> tipo) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        /**
         * Si paso una clase como parámetro se tiene que escribir así,
         * algo importante es que si se pasa como parámetro la clase, luego no puedes utilizarla
         * como variable para instanciarla, para eso tienes que llamar a su constructor y crear una nueva instancia manualmente
         * no sirve con simplemente new.
         */
        tipoClase productoAlmacenar = tipo.getConstructor(String.class, int.class, double.class).newInstance(nombre,cantidad,precio);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {

            oos.writeObject(productoAlmacenar);

        } catch (IOException e) {
            System.out.println("error, no se pudo almacenar el objeto");
        }

        tipoClase productoLeer = null;

        try {ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));

            productoLeer = (tipoClase) ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("error, no se pudo leer el objeto almacenado");
        }
        System.out.println("\n producto: " + productoLeer);
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
