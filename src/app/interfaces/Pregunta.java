package app.interfaces;

public interface Pregunta {
    void setEnunciado(String enunciado);
    void setTiempoRespuesta(int segundos);

    String getEnunciado();
    int getTiempoRespuesta();

    void showPregunta();
}
