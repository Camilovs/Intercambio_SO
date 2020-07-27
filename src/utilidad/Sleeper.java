package utilidad;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Sleeper {

    public Sleeper() {
    }

    public void Sleep(int numero) {
        try {
            Thread.sleep(numero);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sleeper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
