package cpu;

import proceso.ColaProcesos;
import memoria.UnidadMemoria;
import proceso.Proceso;
import utilidad.Sleeper;

public class Planificador {

    private final Sleeper sleeper;
    private final Cpu core1;
    private final ColaProcesos cola;
    private final UnidadMemoria memoriamain;
    private final UnidadMemoria almacen;
    private int pid = 1;

    public Planificador() {
        core1 = new Cpu();
        sleeper = new Sleeper();
        cola = new ColaProcesos();
        memoriamain = new UnidadMemoria(6000);
        almacen = new UnidadMemoria(20000);
    }
    /**
     * obtiene el proceso de la cola de prioridad
     * @return proeceso elegido
     */
    public Proceso elegirProceso() {
        if (!cola.isEmpty()) {
            return cola.getProceso();
        } else {
            return null;
        }
    }
    /**
     * Ejecuta proceso elegido asignandole el procesador.
     * @param p proceso a ejecutar
     */
    public void ejecutarProceso(Proceso p) {
        core1.ejecutarProceso(p);
        if (p.getTiempo_computo() > 0) {
            cola.addProceso(p);
        } else {
            System.out.println(p.getEtiqueta() + " terminado, liberando memoria...");
            sleeper.Sleep(1500);
            memoriamain.quitarProceso(p);
        }
    }
    /**
     * Intercambio de proceso desde almacen hacia memoria principal
     * @param p proceso de intercambio
     */
    public void intercambio(Proceso p) {
        if (memoriamain.hayMemoriaPara(p.getTamanio())) {
            System.out.println("quitando " + p.getEtiqueta() + " de almacen");
            almacen.quitarProceso(p);
            System.out.println("moviendo " + p.getEtiqueta() + " a memoria");
            memoriamain.addProceso(p);
            ejecutarProceso(p);
        } else {
            System.out.println("No hay memoria suficiente para ejecucion");
            cola.addProceso(p);
        }
    }
    /**
     * Crear procesos de prueba
     */
    private void crearProcesos() {
        Proceso p1 = crearProceso("explorer.exe", 100, 5000,0);
        Proceso p2 = crearProceso("adobe.exe", 500, 2500,1);
        Proceso p3 = crearProceso("firefox.exe", 2000, 500,0);
        Proceso p4 = crearProceso("antivirus.exe", 700, 1000,1);
        Proceso p5 = crearProceso("discord.exe", 500, 3000,0);
        Proceso p6 = crearProceso("chrome.exe", 3000, 7000,1);
        Proceso p7 = crearProceso("cmd.exe", 50, 100);
        if (!memoriamain.addProceso(p1)) {
            almacen.addProceso(p1);
        }

        if (!memoriamain.addProceso(p2)) {
            almacen.addProceso(p2);
        }

        if (!memoriamain.addProceso(p3)) {
            almacen.addProceso(p3);
        }

        if (!memoriamain.addProceso(p4)) {
            almacen.addProceso(p4);
        }

        if (!memoriamain.addProceso(p5)) {
            almacen.addProceso(p5);
        }

        if (!memoriamain.addProceso(p6)) {
            almacen.addProceso(p6);
        }

        if (!memoriamain.addProceso(p7)) {
            almacen.addProceso(p7);
        }
        cola.printColaProcesos();
        
    }
    
    /**
     * obtiene un id nuevo para un proceso
     * @return id nuevo (aumento de una unidad del id anterior)
     */
    private int getPid(){
        return pid++;
    }
    /**
     * Crear proceso sin prioridad, o con prioridad por defecto
     * @param nombre nombre del proceso
     * @param tamanio tamanio del proceso en mb
     * @param tc tiempo de computo necesario para ejecucion
     * @return proceso nuevo
     */
    private Proceso crearProceso(String nombre, int tamanio, int tc) {
        
        Proceso p = new Proceso(getPid(),nombre, tamanio, tc);
        cola.addProceso(p);
        return p;
    }
    /**
     * Crear proceso con prioridad definida en parametro.
     * @param nombre nombre del proceso
     * @param tamanio tamanio del proceso en mb
     * @param tc tiempo de computo necesario para ejecucion
     * @param prio prioridad del proceso (0 maxima prioridad, 1 menor prioridad)
     * @return proceso nuevo
     */
    private Proceso crearProceso(String nombre, int tamanio, int tc,int prio) {
        
        Proceso p = new Proceso(getPid(),nombre, tamanio, tc, prio);
        cola.addProceso(p);
        return p;
    }
    /**
     * ejecucion del planificador, ejecuta procesos, intercambia de memoria y elimina procesos.
     */
    public void ejecucion() {
        crearProcesos();
        while (true) {
            sleeper.Sleep(1000);
            Proceso proceso = elegirProceso();
            if (proceso != null) {
                System.out.println("\nPlanificando " + proceso.getEtiqueta() + " " 
                        + proceso.getTamanio() + "MB" + " tiempo restante: " 
                        + proceso.getTiempo_computo() + "ms");
                if (memoriamain.isInMemoria(proceso)) {
                    ejecutarProceso(proceso);
                } else {
                    intercambio(proceso);
                }
            } else {
                System.out.println("===============Todos los procesos ejecutados=============");
                break;
            }
        }
    }
}
