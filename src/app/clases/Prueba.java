package app.clases;

import app.interfaces.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class Prueba implements app.interfaces.Prueba {
    private List<Pregunta> preguntas;

    public Prueba(){
        this.preguntas = new ArrayList<>();
    }

    public void agregarVerdaderoFalso(
            String enunciado,
            int tiempoRespuesta,
            boolean respuesta,
            String nivelTaxonomia
    ){
        VerdaderoFalso pregunta = new VerdaderoFalso(
                enunciado,
                tiempoRespuesta,
                respuesta,
                nivelTaxonomia
        );
        this.preguntas.add(pregunta);
    }

    public void agregarSeleccionMultiple(
            String enunciado,
            int tiempoRespuesta,
            String respuesta,
            List<String> opciones,
            String nivelTaxonomia
    ){
        SeleccionMultiple pregunta = new SeleccionMultiple(
                enunciado,
                tiempoRespuesta,
                respuesta,
                nivelTaxonomia
                );

        this.preguntas.add(pregunta);
    }

    public void agregarPregunta(Pregunta p){
        this.preguntas.add(p);
    }

    @Override
    public Pregunta getPregunta(int idxPregunta){
        return this.preguntas.get(idxPregunta);
    }

    @Override
    public List<Pregunta> getPreguntas(){
        return this.preguntas;
    }

    @Override
    public void borrarPregunta(int idxPregunta){
        this.preguntas.remove(idxPregunta);
    }

    @Override
    public void showPreguntas(){
        for (int i = 0; i < this.preguntas.size(); i++){
            System.out.println("[Pregunta " + (i+1) + "]");
            this.preguntas.get(i).showPregunta();
            System.out.println();
        }
    }
}
