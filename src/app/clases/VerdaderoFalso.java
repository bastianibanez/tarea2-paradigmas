package app.clases;

import app.interfaces.Pregunta;

public class VerdaderoFalso implements Pregunta {
    private String enunciado;
    int tiempoRespuesta;
    boolean respuesta;
    TaxonomiaBloom nivelTaxonomia;

    public void setEnunciado(String enunciado){
        this.enunciado = enunciado;
    }

    public void setTiempoRespuesta(int tiempoSegundos){
        this.tiempoRespuesta = tiempoSegundos;
    }

    public void setRespuesta(boolean respuesta){
        this.respuesta = respuesta;
    }

    public String getEnunciado(){
        return this.enunciado;
    }

    public int getTiempoRespuesta(){
        return this.tiempoRespuesta;
    }

    boolean getRespuesta(){
        return this.respuesta;
    }

    public void showPregunta(){
        System.out.println("Tipo de pregunta: Verdadero/Falso");
        System.out.println("Enunciado: " + this.getEnunciado());
        System.out.println("Respuesta: " + this.getRespuesta());
        System.out.println("Tiempo de respuesta: " + this.getTiempoRespuesta() + " segundos");
    }
}
