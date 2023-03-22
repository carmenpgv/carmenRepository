import com.carmenpg.juegosdemesahibernate.gui.Controlador;
import com.carmenpg.juegosdemesahibernate.gui.Modelo;
import com.carmenpg.juegosdemesahibernate.gui.Vista;

public class Principal {

    public static void main(final String[] args)  {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista,modelo);

    }
}