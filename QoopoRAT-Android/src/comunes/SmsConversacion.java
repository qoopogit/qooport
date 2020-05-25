/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
/**
 *
 * @author alberto
 */
public class SmsConversacion {
    private int idHilo;
    private List<Sms> mensajes;
    public SmsConversacion() {
    }
    public SmsConversacion(int idHilo) {
        this.idHilo = idHilo;
    }
    public SmsConversacion(int idHilo, List<Sms> mensajes) {
        this.idHilo = idHilo;
        this.mensajes = mensajes;
    }
    public int getIdHilo() {
        return idHilo;
    }
    public void setIdHilo(int idHilo) {
        this.idHilo = idHilo;
    }
    public List<Sms> getMensajes() {
        return mensajes;
    }
    public void setMensajes(List<Sms> mensajes) {
        this.mensajes = mensajes;
    }
    public void agregarSms(Sms sms) {
        if (mensajes == null) {
            mensajes = new ArrayList<Sms>();
        }
        mensajes.add(sms);
    }
    public Sms getUltimo() {
        Collections.sort(mensajes, new Comparator() {
            @Override
            public int compare(Object p1, Object p2) {
                return new Date(((Sms) p1).getDate()).compareTo(
                        new Date(((Sms) p2).getDate())
                );
            }
        });
        if (mensajes != null && !mensajes.isEmpty()) {
            return mensajes.get(mensajes.size() - 1);
        }
        return new Sms();
    }
}
