/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools && Templates
 * and open the template in the editor.
 */
package plugin.p3;

import java.util.ArrayList;
import plugin.Plugin;

/**
 *
 * @author alberto
 */
public class PluginDef extends Plugin {

    public PluginDef() {
        setId("3");
        setNombre("QoopoRT Nativo");
        setVersion("1.1");
        setDescripcion("Permite el acceso a funcionalidades nativas del sistema operativo como Obtener el nobmre de la ventana actual, Windows API entre otros");
        setPlataformas("Windows, Linux");
        setListaArchivo(new ArrayList<String>());
        getListaArchivo().add("QoopoRTPlugJNA.jar&&<server>/lib");
        getListaArchivo().add("jna-4.2.1.jar&&<server>/lib/lib");
        getListaArchivo().add("jna-platform-4.2.1.jar&&<server>/lib/lib");
    }

}
