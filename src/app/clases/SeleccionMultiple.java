package app.clases;

import app.interfaces.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class SeleccionMultiple implements Pregunta {
    private String enunciado;
    private int tiempoRespuesta;
    private String respuesta;
    private List<String> opciones;
    public TaxonomiaBloom nivelTaxonomia;

    public SeleccionMultiple(){
        this.enunciado = "";
        this.tiempoRespuesta = 0;
        this.respuesta = "";
        this.opciones = new ArrayList<>();
        this.nivelTaxonomia = new TaxonomiaBloom();
    }

    public SeleccionMultiple(
            String enunciado,
            int tiempoRespuesta,
            String respuesta,
            String nivelTaxonomia
    ){
            this.enunciado = enunciado;
            this.tiempoRespuesta = tiempoRespuesta;
            this.respuesta = respuesta;
            this.opciones = new ArrayList<>();
            this.nivelTaxonomia = new TaxonomiaBloom(nivelTaxonomia);

    }

    @Override
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    @Override
    public void setTiempoRespuesta(int tiempoSegundos){
        this.tiempoRespuesta = tiempoSegundos;
    }

    public void setRespuesta(String respuesta){
        this.respuesta = respuesta;
    }

    public void agregarOpcion(String opcion){
        this.opciones.add(opcion);
    }

    @Override
    public String getEnunciado(){
        return this.enunciado;
    }

    @Override
    public int getTiempoRespuesta(){
        return this.tiempoRespuesta;
    }

    public String getRespuesta(){
        return this.respuesta;
    }

    public List<String> getOpciones(){
        return this.opciones;
    }

    @Override
    public void showPregunta() {
        System.out.println("Tipo de pregunta: Verdadero/Falso");
        System.out.println("Nivel Taxonomía de Bloom: " + nivelTaxonomia.getNivel());
        System.out.println("Enunciado: " + this.getEnunciado());
        System.out.println("Respuesta: " + this.getRespuesta());
        this.showOpciones();
        System.out.println("Tiempo de respuesta: " + this.getTiempoRespuesta() + " segundos");
    }

    public void showOpciones(){
        System.out.println("Opciones:");
        for (int i = 0; i < this.opciones.size(); i++){
            System.out.println("    " + intToChar(i) + ". " + this.opciones.get(i));
        }
    }

    private char intToChar(int num){
        if (!((num + 97 >= 97) && (num + 97 <= 122))){
            throw new IllegalArgumentException("Valor ingresado es incorrecto se retorna string vacío.");
        }
        return (char)(num + 97);
    }
}
