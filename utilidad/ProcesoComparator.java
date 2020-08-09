/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidad;

import java.util.Comparator;
import proceso.Proceso;

public class ProcesoComparator implements Comparator<Proceso> {
    
    @Override
    public int compare(Proceso p1, Proceso p2) {
        if(p1.getPrioridad()<p2.getPrioridad()){
            return -1;
        }
        else if (p1.getPrioridad()>p2.getPrioridad()){
            return 1;
        }     
        return 0;
        
    }
    
}
