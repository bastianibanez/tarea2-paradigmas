package model; // Define el paquete al que pertenece esta enumeración

/**
 * La enumeración NivelBloom representa los diferentes niveles cognitivos
 * de la Taxonomía de Bloom. Estos niveles se utilizan para clasificar
 * el tipo de habilidad o conocimiento que una pregunta evalúa.
 *
 * Cada elemento de la enumeración corresponde a un nivel específico:
 * RECORDAR: Recuperar información o conocimiento.
 * ENTENDER: Comprender el significado de la información.
 * APLICAR: Usar la información en una nueva situación.
 * ANALIZAR: Descomponer la información en partes y relacionarlas.
 * EVALUAR: Juzgar el valor de la información o ideas.
 * CREAR: Juntar partes para formar un todo nuevo y coherente.
 */
public enum NivelBloom {
    RECORDAR,   // Nivel más básico: implica el reconocimiento de información.
    ENTENDER,   // Implica la interpretación y comprensión de la información.
    APLICAR,    // Uso de la información en contextos nuevos o específicos.
    ANALIZAR,   // Descomposición de la información en sus componentes para entender su estructura.
    EVALUAR,    // Hacer juicios basados en criterios y estándares.
    CREAR       // El nivel más alto: implica la generación de nuevas ideas, productos o formas de ver las cosas.
}