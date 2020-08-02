package memoriavirtual;

import java.util.Comparator;
import utilidad.Queue;
import proceso.Proceso;
import java.util.PriorityQueue;
import utilidad.ProcesoComparator;

public class ColaProcesos {

    
    private PriorityQueue<Proceso> procesosPrioridad;
    private Comparator<Proceso> comparator = new ProcesoComparator();

    public ColaProcesos() {       
        procesosPrioridad = new PriorityQueue<>(comparator);
    }
    public void addProceso(Proceso p){
        procesosPrioridad.offer(p);
//        System.out.println("-");
//        printColaProcesos();
        
    }
    public Proceso getProceso(){
        return procesosPrioridad.remove();
    }
    public boolean isEmpty(){
        return procesosPrioridad.isEmpty();
    }
    public void printColaProcesos(){
        for (Proceso proceso : procesosPrioridad) {
            System.out.println(proceso.getEtiqueta()+" "+proceso.getPrioridad());
        }
    }
}
