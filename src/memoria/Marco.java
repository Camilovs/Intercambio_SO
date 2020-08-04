/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoria;

import proceso.Proceso;

/**
 * Clase que respresenta la equivalencia de una pagina pero en memoria fisica.
 */
public class Marco {

    private int id;
    private int tamanio;
    private boolean ocupado;

    public Marco(){
        id=0;
        tamanio = 100;
        ocupado = false;
    }

    public void ocuparBloque(Proceso p){
        setId(p.getId());
        setOcupado(true);
    }
    public void desocuparBloque(Proceso p){
        if(getId()==p.getId()){
            setId(0);
            setOcupado(false);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}
