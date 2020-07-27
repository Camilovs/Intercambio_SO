package cpu;

import memoriavirtual.ColaProcesos;
import memoriavirtual.unidadMemoria;
import proceso.Proceso;
import utilidad.Sleeper;

public class Planificador {

    Sleeper sleeper;
    Cpu core1;
    ColaProcesos cola;
    unidadMemoria memoriamain;
    unidadMemoria almacen;

    public Planificador() {
        core1 = new Cpu();
        sleeper = new Sleeper();
        cola = new ColaProcesos();
        memoriamain = new unidadMemoria(6000);
        almacen = new unidadMemoria(20000);
    }

    public Proceso elegirProceso() {
        if (!cola.isEmpty()) {
            return cola.getProceso();
        } else {
            return null;
        }
    }

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
    private void crearProcesos() {
        Proceso p1 = crearProceso("explorer.exe", 100, 5000);
        Proceso p2 = crearProceso("adobe.exe", 500, 2500);
        Proceso p3 = crearProceso("firefox.exe", 2000, 500);
        Proceso p4 = crearProceso("antirus.exe", 700, 1000);
        Proceso p5 = crearProceso("discord.exe", 500, 3000);
        Proceso p6 = crearProceso("chrome.exe", 3000, 7000);
        Proceso p7 = crearProceso("cmd.exe", 50, 100);
        if (!memoriamain.addProceso(p1)) {
            almacen.addProceso(p1);
        }
        ;
        if (!memoriamain.addProceso(p2)) {
            almacen.addProceso(p2);
        }
        ;
        if (!memoriamain.addProceso(p3)) {
            almacen.addProceso(p3);
        }
        ;
        if (!memoriamain.addProceso(p4)) {
            almacen.addProceso(p4);
        }
        ;
        if (!memoriamain.addProceso(p5)) {
            almacen.addProceso(p5);
        }
        ;
        if (!memoriamain.addProceso(p6)) {
            almacen.addProceso(p6);
        }
        ;
        if (!memoriamain.addProceso(p7)) {
            almacen.addProceso(p7);
        }
        ;
    }

    private Proceso crearProceso(String nombre, int tamanio, int tc) {
        Proceso p = new Proceso(nombre, tamanio, tc);
        cola.addProceso(p);
        return p;
    }
    public void ejecucion() {
        crearProcesos();
        while (true) {
            sleeper.Sleep(1000);
            Proceso p = elegirProceso();
            if (p != null) {
                System.out.println("\nPlanificando " + p.getEtiqueta() + " " 
                        + p.getTamanio() + "MB" + " tiempo restante: " 
                        + p.getTiempo_computo() + "ms");
                if (memoriamain.isInMemoria(p)) {
                    ejecutarProceso(p);
                } else {
                    intercambio(p);
                }
            } else {
                System.out.println("===============Todos los procesos ejecutados=============");
                break;
            }
        }
    }
}
