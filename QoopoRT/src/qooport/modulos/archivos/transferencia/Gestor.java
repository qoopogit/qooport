/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.archivos.transferencia;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aigarcia
 */
public class Gestor {

    private List<Transferencia> lista = new ArrayList<>();

    public void agregar(Transferencia transferencia) {
        lista.add(transferencia);
    }

    public void eliminar(Transferencia transferencia) {
        lista.remove(transferencia);
    }

    public void detenerTodas() {
        for (Transferencia t : lista) {
            t.detener();
        }
    }

    public void limpiar() {
        detenerTodas();
        lista.clear();
    }

    public List<Transferencia> getLista() {
        return lista;
    }

    public void setLista(List<Transferencia> lista) {
        this.lista = lista;
    }

}
