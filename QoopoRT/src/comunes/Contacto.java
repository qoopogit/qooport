/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author alberto
 */
public class Contacto implements Serializable {

    private long id;
    private long last_time_contacted;
    private int times_contacted;
    private String display_name;
    private int starred;
    private ArrayList<String> phones;
    private ArrayList<String> emails;
    private ArrayList<String> notes;
    private String street;
    private String city;
    private String region;
    private String postalcode;
    private String country;
    private int type_addr;
    private ArrayList<String> messaging;
    private String organisationName;
    private String organisationStatus;
    private byte[] photo;

    public Contacto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLast_time_contacted() {
        return last_time_contacted;
    }

    public void setLast_time_contacted(long last_time_contacted) {
        this.last_time_contacted = last_time_contacted;
    }

    public int getTimes_contacted() {
        return times_contacted;
    }

    public void setTimes_contacted(int times_contacted) {
        this.times_contacted = times_contacted;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public int getStarred() {
        return starred;
    }

    public void setStarred(int starred) {
        this.starred = starred;
    }

    public ArrayList<String> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<String> phones) {
        this.phones = phones;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getType_addr() {
        return type_addr;
    }

    public void setType_addr(int type_addr) {
        this.type_addr = type_addr;
    }

    public ArrayList<String> getMessaging() {
        return messaging;
    }

    public void setMessaging(ArrayList<String> messaging) {
        this.messaging = messaging;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getOrganisationStatus() {
        return organisationStatus;
    }

    public void setOrganisationStatus(String organisationStatus) {
        this.organisationStatus = organisationStatus;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
