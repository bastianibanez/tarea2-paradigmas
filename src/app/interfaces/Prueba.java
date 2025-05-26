package app.interfaces;

import java.util.ArrayList;

public interface Prueba {
    void agregarPregunta(Pregunta pregunta);

    Pregunta getPregunta(int idxPregunta);
    ArrayList<Pregunta> getPreguntas();

    void actualizarPregunta(int idxPregunta, Pregunta preguntaActualizada);

    void borrarPregunta(int idxPregunta);

    void showPreguntas();
}
