package utils; // Indica que esta clase es una utilidad.

import model.NivelBloom;   // Importa la enumeración para los niveles de Bloom.
import model.Pregunta;     // Importa la clase Pregunta.
import model.TipoPregunta; // Importa la enumeración para los tipos de pregunta.

import java.io.*;   // Para manejo de archivos (lectura).
import java.util.*; // Para el uso de listas y arrays.

/**
 * Clase CargaArchivo: Esta clase se encarga de leer preguntas desde un archivo de texto.
 * Actúa como un "lector" de evaluaciones, extrayendo la información de cada pregunta
 * y los detalles generales de la prueba (asignatura y evaluación) de un formato específico.
 * Es crucial para la inicialización del sistema de evaluación.
 */
public class CargaArchivo {

    // Atributos estáticos para almacenar la asignatura y la evaluación leídas del archivo.
    private static String subject = "";   // Almacena el nombre de la asignatura.
    private static String evaluation = ""; // Almacena el nombre de la evaluación.

    /**
     * Método para obtener el nombre de la asignatura cargada.
     * @return El nombre de la asignatura.
     */
    public static String getSubject() {
        return subject;
    }

    /**
     * Método para obtener el nombre de la evaluación cargada.
     * @return El nombre de la evaluación.
     */
    public static String getEvaluation() {
        return evaluation;
    }

    /**
     * Método principal para cargar preguntas desde un archivo.
     * Lee el archivo línea por línea y parsea la información para crear objetos Pregunta.
     * Ignora las líneas malformadas.
     *
     * @param file El archivo de texto que contiene las preguntas.
     * @return Una lista de objetos Pregunta creados a partir del archivo.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public static List<Pregunta> loadQuestionsFromFile(File file) throws IOException {
        List<Pregunta> questions = new ArrayList<>(); // Lista donde se almacenarán las preguntas.

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // Lee el archivo línea por línea.
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Asignatura:")) {
                    // Si la línea comienza con "Asignatura:", extrae el nombre de la asignatura.
                    subject = line.split(":", 2)[1].trim();
                } else if (line.startsWith("Evaluación:")) {
                    // Si la línea comienza con "Evaluación:", extrae el nombre de la evaluación.
                    evaluation = line.split(":", 2)[1].trim();
                } else if (line.contains("|")) {
                    // Si la línea contiene '|', se asume que es una pregunta y se parsea.
                    String[] parts = line.split("\\|"); // Divide la línea en partes usando '|' como delimitador.

                    try {
                        // Extrae el tipo de pregunta, nivel de Bloom, texto y opciones.
                        TipoPregunta type = TipoPregunta.valueOf(parts[1].toUpperCase());
                        NivelBloom level = NivelBloom.valueOf(parts[2].toUpperCase());
                        String text = parts[3];
                        String[] options;
                        int correctIndex;

                        // Diferencia el parsing según el tipo de pregunta (MÚLTIPLE o VERDADERO_FALSO).
                        if (type == TipoPregunta.MULTIPLE && parts.length >= 9) {
                            options = Arrays.copyOfRange(parts, 4, 8); // Opciones de la 4 a la 7.
                            correctIndex = Integer.parseInt(parts[8]) - 1; // Índice de respuesta correcta.
                        } else if (type == TipoPregunta.VERDADERO_FALSO && parts.length >= 7) {
                            options = Arrays.copyOfRange(parts, 4, 6); // Opciones de la 4 a la 5.
                            correctIndex = Integer.parseInt(parts[6]) - 1; // Índice de respuesta correcta.
                        } else {
                            continue; // Si el formato no coincide, se ignora la línea.
                        }
                        // Crea un nuevo objeto Pregunta y lo añade a la lista.
                        questions.add(new Pregunta(type, level, text, options, correctIndex));
                    } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                        // Captura errores si la línea está malformada y la ignora, mostrando un mensaje.
                        System.err.println("Línea ignorada por error de formato: " + line);
                    }
                }
            }
        }
        return questions; // Devuelve la lista de preguntas cargadas.
    }
}