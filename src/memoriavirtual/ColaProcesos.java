package memoriavirtual;

import utilidad.Queue;
import proceso.Proceso;

public class ColaProcesos {

    private Queue<Proceso> procesos;

    public ColaProcesos() {
        procesos = new Queue();
    }

    public void addProceso(Proceso p) {
        procesos.enqueue(p);
    }

    public Proceso getProceso() {
        return procesos.dequeue();
    }

    public boolean isEmpty() {
        return procesos.isEmpty();
    }
}
