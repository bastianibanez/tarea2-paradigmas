package app.interfaces;

import java.util.ArrayList;
import java.util.List;

public interface Prueba {
    Pregunta getPregunta(int idxPregunta);
    List<Pregunta> getPreguntas();

    void borrarPregunta(int idxPregunta);

    void showPreguntas();
}
