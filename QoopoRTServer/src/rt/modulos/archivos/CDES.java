package rt.modulos.archivos;

import network.Conexion;
//cola descarga

public class CDES {

    private String a;//archivo
    private String r;//ruta
    private String rO;//ruta a omitir
    private Conexion c;//conexion
    private Long offset;
    private int bufferSize;

    public CDES(String a, String r, Long offset) {
        this.a = a;
        this.r = r;
        this.offset = offset;
    }

    public CDES(String a, String r, String rO, Long offset) {
        this.a = a;
        this.r = r;
        this.rO = rO;
        this.offset = offset;
    }

    public CDES(String a, String r, Conexion c, int bufferSize, Long offset) {
        this.a = a;
        this.r = r;
        this.c = c;
        this.bufferSize = bufferSize;
        this.offset = offset;
    }

    public CDES(String a, String r, String rO, Conexion c, int bufferSize, Long offset) {
        this.a = a;
        this.r = r;
        this.rO = rO;
        this.c = c;
        this.bufferSize = bufferSize;
        this.offset = offset;
    }

    public String getArchivo() {
        return a;
    }

    public void setArchivo(String archivo) {
        this.a = archivo;
    }

    public String getRuta() {
        return r;
    }

    public void setRuta(String ruta) {
        this.r = ruta;
    }

    public String getRutaOmitir() {
        return rO;
    }

    public void setRutaOmitir(String rutaOmitir) {
        this.rO = rutaOmitir;
    }

    public Conexion getConexion() {
        return c;
    }

    public void setConexion(Conexion conexion) {
        this.c = conexion;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

}
