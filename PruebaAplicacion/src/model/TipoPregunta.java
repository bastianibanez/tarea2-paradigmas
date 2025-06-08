package model; // Define el paquete al que pertenece esta enumeración

/**
 * La enumeración TipoPregunta representa los diferentes tipos de preguntas
 * que pueden ser utilizadas en una evaluación o prueba.
 *
 * Cada elemento de la enumeración corresponde a un tipo específico:
 * MULTIPLE: Preguntas con múltiples opciones de respuesta, donde solo una es correcta.
 * VERDADERO_FALSO: Preguntas que requieren determinar si una afirmación es verdadera o falsa.
 */
public enum TipoPregunta {
    MULTIPLE,       // Representa una pregunta de selección múltiple.
    VERDADERO_FALSO // Representa una pregunta de verdadero o falso.
}