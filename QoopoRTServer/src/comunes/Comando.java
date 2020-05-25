package comunes;

import java.io.Serializable;

public class Comando implements Serializable {

    private int comando;
    private int nParametros;
    private Object objeto;

    public Comando(int comando, int nParametros, Object objeto) {
        this.comando = comando;
        this.objeto = objeto;
        this.nParametros = nParametros;
    }

    public int getComando() {
        return comando;
    }

    public void setComando(int comando) {
        this.comando = comando;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    public int getnParametros() {
        return nParametros;
    }

    public void setnParametros(int nParametros) {
        this.nParametros = nParametros;
    }

}
