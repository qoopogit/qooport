/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;

import java.io.Serializable;

/**
 *
 * @author alberto
 */
public class Llamada implements Serializable {

    private int id;
    private int type;
    private long date;
    private long duration;
    private String number;
    private String name;
    private int raw_contact_id;

    public Llamada(int id, int type, long date, long duration, String number, String name, int raw_contact_id) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.duration = duration;
        this.number = number;
        this.name = name;
        this.raw_contact_id = raw_contact_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRaw_contact_id() {
        return raw_contact_id;
    }

    public void setRaw_contact_id(int raw_contact_id) {
        this.raw_contact_id = raw_contact_id;
    }
}
