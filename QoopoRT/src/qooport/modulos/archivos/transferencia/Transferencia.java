/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.modulos.archivos.transferencia;

import comunes.Accion;
import java.io.File;
import java.io.Serializable;
import javax.swing.ImageIcon;
import network.Conexion;
import qooport.Global;
import qooport.asociado.Asociado;
import qooport.utilidades.Util;
import qooport.utilidades.contador.ContadorBPS;

/**
 *
 * @author aigarcia
 */
public abstract class Transferencia extends Thread implements Serializable {

    protected Asociado asociado;
    protected String idInformacion;

    protected long tInicio;//guarda e tiemp q en inicio
    protected long tFinal;// guarda el tiempo en q finalizo
    protected long total;// total en bytes
    protected long actual;//actual en bytes
    protected int avance;//avance en porcentaje
    protected ContadorBPS contador;
    protected String nombreArchivo;
    protected ImageIcon icono;
    //el archivo local
    //si es descarga es el archivo donde se guarda, si es carga es el archivo que se envi
    protected File archivo;
    protected Conexion conexion;
    protected Accion accionFinalizar;
    protected Accion accionProgreso;
    protected Accion accionInicial;
    protected boolean ok;
    protected boolean activo;
    protected boolean pausado;
    protected int bufferSize;
    protected boolean detener=false;//si se detuvo intencionalmente
    
    protected String hash;// es el hash md5 del archivo para poder identificar la descarga/carga para reanudaciones

    public abstract void iniciar();

    public abstract void detener();

    public abstract void pausar();

    public abstract void continuar();

    public abstract void reanudar();

    public void agregarAlGestor() {
        try {
            Global.transferencias.agregarTransferencia(this);
        } catch (Exception e) {

        }
    }

    public int getAvance() {
        return avance;
    }

//    public abstract void ejecutar();
    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    public Accion getAccionFinalizar() {
        return accionFinalizar;
    }

    public void setAccionFinalizar(Accion accionFinalizar) {
        this.accionFinalizar = accionFinalizar;
    }

    public Accion getAccionProgreso() {
        return accionProgreso;
    }

    public void setAccionProgreso(Accion accionProgreso) {
        this.accionProgreso = accionProgreso;
    }

    public Accion getAccionInicial() {
        return accionInicial;
    }

    public void setAccionInicial(Accion accionInicial) {
        this.accionInicial = accionInicial;
    }

    public ImageIcon getIcono() {
        return icono;
    }

    public void setIcono(ImageIcon icono) {
        this.icono = icono;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public ContadorBPS getContador() {
        return contador;
    }

    public void setContador(ContadorBPS contador) {
        this.contador = contador;
    }

    public boolean isPausado() {
        return pausado;
    }

    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getActual() {
        return actual;
    }

    public void setActual(long actual) {
        this.actual = actual;
    }

    public String getIdInformacion() {
        return idInformacion;
    }

    public void setIdInformacion(String idInformacion) {
        this.idInformacion = idInformacion;
    }

    public Asociado getAsociado() {
        return asociado;
    }

    public void setAsociado(Asociado asociado) {
        this.asociado = asociado;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getTiempoTranscurrido() {
        if (tFinal == 0) {
            return Util.calcularTiempo(System.currentTimeMillis() - tInicio);
        } else {
            return Util.calcularTiempo(tFinal - tInicio);
        }
    }

    public String getTiempoRestante() {
        return Util.calcularTiempo((long) ((total - actual) * 1000 / contador.getTasa()));
    }

}
