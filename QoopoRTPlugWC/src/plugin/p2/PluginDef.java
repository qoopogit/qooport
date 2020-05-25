/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools && Templates
 * and open the template in the editor.
 */
package plugin.p2;

import java.util.ArrayList;
import plugin.Plugin;

/**
 *
 * @author alberto
 */
public class PluginDef extends Plugin {

    public PluginDef() {
        setId("2");
        setNombre("QoopoRT WebCam");
        setVersion("1.1");
        setDescripcion("Permite la captura de la webcam.");
        setPlataformas("Windows, Linux");
        setListaArchivo(new ArrayList<String>());
        getListaArchivo().add("QoopoRTPlugWC.jar&&<server>/lib");
        getListaArchivo().add("bridj-0.7-20130703.103049-42.jar&&<server>/lib/lib");        
    }

}
