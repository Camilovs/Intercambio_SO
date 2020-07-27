package proceso;

public class Proceso {

    private String etiqueta;
    private int id;
    private int tamanio;

    private int tiempo_computo;

    private boolean estado;

    private int prioridad = 0;

    public Proceso(String e, int t, int tc) {
        etiqueta = e;
        tamanio = t;
        tiempo_computo = tc;
        estado = true;
    }

    public Proceso(String e, int t, int tc, int prio) {
        etiqueta = e;
        tamanio = t;
        tiempo_computo = tc;
        estado = true;
        prioridad = 0;
    }

    public void disminuirComputo(int tiempoCumplido) {
        if (getTiempo_computo() > 0) {
            setTiempo_computo(getTiempo_computo() - tiempoCumplido);
        } else
            System.out.println("Proceso/ Tiempo cumplido");
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getTiempo_computo() {
        return tiempo_computo;
    }

    public void setTiempo_computo(int tiempo_computo) {
        this.tiempo_computo = tiempo_computo;
    }

    public boolean isActivo() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}
