import cpu.Planificador;

public class Sistema {

    private Planificador cpuPlan;

    public Sistema() throws InterruptedException {
        cpuPlan = new Planificador();

    }

    void ejecucion() throws InterruptedException {
        System.out.println("\n===========Inicio ejecucion de procesos============");
        cpuPlan.ejecucion();
    }
}
