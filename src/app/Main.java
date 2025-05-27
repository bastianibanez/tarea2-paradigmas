package app;
/*pico*/
import app.clases.Prueba;
import app.clases.SeleccionMultiple;
import app.clases.TaxonomiaBloom;
import app.clases.VerdaderoFalso;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Prueba p = new Prueba();

        VerdaderoFalso uno = new VerdaderoFalso("Uno?", 10, true, "Crear");
        SeleccionMultiple dos = new SeleccionMultiple("Dos?", 20, "a", "Entender");
        dos.agregarOpcion("o1");
        dos.agregarOpcion("o2");
        dos.agregarOpcion("o3");

        p.agregarPregunta(uno);
        p.agregarPregunta(dos);

        p.showPreguntas();

        p.borrarPregunta(1);
        p.showPreguntas();
    }
}
