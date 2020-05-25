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
public class Sms implements Serializable {

    private int id;
    private int thid;
    private String add;
    private int person;
    private long date;
    private int read;
    private String body;
    private int type;

    public Sms() {
    }

    public Sms(int id, int thid, String add, int person, long date, int read, String body, int type) {
        this.id = id;
        this.thid = thid;
        this.add = add;
        this.person = person;
        this.date = date;
        this.read = read;
        this.body = body;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThid() {
        return thid;
    }

    public void setThid(int thid) {
        this.thid = thid;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
