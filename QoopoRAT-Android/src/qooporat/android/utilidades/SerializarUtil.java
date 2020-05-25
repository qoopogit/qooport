/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooporat.utilidades;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;

/**
 * Utilidad para la serializacion de objetos
 * @author alberto
 */
public class SerializarUtil {

    public static void write(Object object, String rutaArchivo) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
        out.writeObject(object);
        out.close();
    }

    public static Object read(String rutaArchivo) throws IOException, ClassNotFoundException {
        ObjectInputStream in
                = new ObjectInputStream(new FileInputStream(rutaArchivo));
        Object object = in.readObject();
        in.close();
        return object;
    }
    
     public static Object read(InputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream in
                = new ObjectInputStream(stream);
        Object object = in.readObject();
        in.close();
        return object;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List list = new ArrayList();
        list.add("uno");
        list.add("dos");
        list.add("tres");
        list.add("cuatro");
        SerializarUtil.write(list, "prueba.txt");
        List otherList = (List) SerializarUtil.read("prueba.txt");
        System.out.println("La lista original es igual a la leida ? " + list.equals(otherList));
    }
}
