/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools && Templates
 * and open the template in the editor.
 */
package plugin.p4;

import java.util.ArrayList;
import plugin.Plugin;

/**
 *
 * @author alberto
 */
public class PluginDef extends Plugin {

    public PluginDef() {
        setId("4");
        setNombre("Utilidades Nirsoft");
        setVersion("1.0");
        setDescripcion("Conjunto de utilidades Nirsoft (Nirsoft.com). Recuperadores de contraseñas");
        setPlataformas("Windows");
        setListaArchivo(new ArrayList<String>());
        getListaArchivo().add("QoopoRTPlugNirsoft.jar&&<server>/lib");
    }
}
