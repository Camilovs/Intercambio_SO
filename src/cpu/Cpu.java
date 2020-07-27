package cpu;

import proceso.Proceso;
import utilidad.Sleeper;

public class Cpu {

    private Sleeper sleeper;

    private int tempo_computo = 500;

    public Cpu() {
        sleeper = new Sleeper();
    }

    public void ejecutarProceso(Proceso p) {
        System.out.println("Ejecutando " + p.getEtiqueta() + "....");
        sleeper.Sleep(tempo_computo);
        p.disminuirComputo(tempo_computo);
    }


}
