package P1_3;

import java.io.Serializable;

public class ProductoTrans implements Serializable {
    String nombre;
    transient int num1;
    double num2;


    public ProductoTrans (String nombre,  int num1, double num2){
        this.nombre = nombre;
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public String toString() {
        return "Producto " +
                "nombre='" + nombre + '\'' +
                ",cantidad=" + num1 +
                ",precio=" + num2;
    }

}