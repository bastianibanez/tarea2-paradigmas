package app.clases;

import app.interfaces.Pregunta;

public class VerdaderoFalso implements Pregunta {
    private String enunciado;
    private int tiempoRespuesta;
    private boolean respuesta;
    private TaxonomiaBloom nivelTaxonomia;

    public VerdaderoFalso(){
        this.enunciado = "";
        this.tiempoRespuesta = 0;
        this.respuesta = false;
        this.nivelTaxonomia = new TaxonomiaBloom();
    }

    @Override
    public void setEnunciado(String enunciado){
        this.enunciado = enunciado;
    }

    @Override
    public void setTiempoRespuesta(int tiempoSegundos){
        this.tiempoRespuesta = tiempoSegundos;
    }

    public void setRespuesta(boolean respuesta){
        this.respuesta = respuesta;
    }

    @Override
    public String getEnunciado(){
        return this.enunciado;
    }

    @Override
    public int getTiempoRespuesta(){
        return this.tiempoRespuesta;
    }

    boolean getRespuesta(){
        return this.respuesta;
    }

    @Override
    public void showPregunta(){
        System.out.println("Tipo de pregunta: Verdadero/Falso");
        System.out.println("Enunciado: " + this.getEnunciado());
        System.out.println("Respuesta: " + this.getRespuesta());
        System.out.println("Tiempo de respuesta: " + this.getTiempoRespuesta() + " segundos");
    }
}
