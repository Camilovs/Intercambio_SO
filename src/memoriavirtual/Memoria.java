package memoriavirtual;

import java.util.ArrayList;
import proceso.Proceso;

public class Memoria {

    private ArrayList<Proceso> procesos;

    private static int tamanio = 6000;
   
    private int tamanio_libre;

    public Memoria() {
        procesos = new ArrayList();
        tamanio_libre = tamanio;
    }

    public boolean addProceso(Proceso p) {
        if (p.getTamanio() <= getTamanio_libre()) {
            procesos.add(p);
            setTamanio_libre(getTamanio_libre() - p.getTamanio());
            System.out.println(p.getEtiqueta() + " agregado a memoria");
            System.out.println("Memoria restante: " + getTamanio_libre() + "MB");
            return true;
        } else {
            return false;
        }
    }

    public boolean quitarProceso(Proceso p) {
        if (procesos.remove(p)) {
            setTamanio_libre(getTamanio_libre() + p.getTamanio());
            System.out.println(p.getTamanio() + "MB de espacio liberado");
            System.out.println("Memoria libre: " + getTamanio_libre() + "MB");
            return true;
        } else {
            System.out.println("No se encontro proceso en memoria principal");
            return false;
        }
    }

    public ArrayList<Proceso> getProcesos() {
        return procesos;
    }

    public void setProcesos(ArrayList<Proceso> procesos) {
        this.procesos = procesos;
    }

    public boolean isInMemoria(Proceso p) {
        return procesos.contains(p);
    }

    public boolean hayMemoriaPara(int t) {
        if (tamanio_libre >= t)
            return true;
        else
            return false;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getTamanio_libre() {
        return tamanio_libre;
    }

    public void setTamanio_libre(int tamanio_libre) {
        this.tamanio_libre = tamanio_libre;
    }
}
