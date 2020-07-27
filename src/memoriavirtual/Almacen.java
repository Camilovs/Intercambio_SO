package memoriavirtual;

import java.util.ArrayList;
import proceso.Proceso;

public class Almacen {

    private ArrayList<Proceso> almacenProcesos;

    private int tamanio;

    public Almacen() {
        almacenProcesos = new ArrayList();
        tamanio = 20000;
    }

    public void almacenarProceso(Proceso p) {
        if (asignarMemoria(p.getTamanio()))
            ;
        almacenProcesos.add(p);
    }

    public boolean asignarMemoria(int tamanioProceso) {
        if ((getTamanio() - tamanioProceso) >= 0) {
            setTamanio(getTamanio() - tamanioProceso);
            return true;
        } else {
            System.out.println("No hay espacio para almacenar proceso");
            return false;
        }
    }

    public void quitarProceso(Proceso p) {
        almacenProcesos.remove(p);
    }

    public ArrayList<Proceso> getAlmacenProcesos() {
        return almacenProcesos;
    }

    public void setAlmacenProcesos(ArrayList<Proceso> almacenProcesos) {
        this.almacenProcesos = almacenProcesos;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
}
