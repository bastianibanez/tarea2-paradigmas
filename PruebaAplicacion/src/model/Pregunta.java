package model; // Indica que esta clase forma parte del paquete 'model'.

/**
 * Clase Pregunta: Representa una pregunta individual dentro de una evaluación o prueba.
 * Encapsula todos los datos relevantes de una pregunta, incluyendo su tipo, nivel de dificultad,
 * texto, opciones de respuesta, y la capacidad de registrar y verificar la respuesta del usuario.
 */
public class Pregunta {

    // Atributos de la pregunta:
    private TipoPregunta type; // El tipo de pregunta (e.g., MÚLTIPLE, VERDADERO_FALSO).
    private NivelBloom level;  // El nivel cognitivo según la Taxonomía de Bloom (e.g., RECORDAR, APLICAR).
    private String text;       // El enunciado o texto de la pregunta.
    private String[] options;  // Un array con las opciones de respuesta disponibles para la pregunta.
    private int correctAnswerIndex; // El índice (posición) de la respuesta correcta dentro del array 'options'.
    private int userAnswer = -1;    // La respuesta seleccionada por el usuario (-1 si aún no ha respondido).

    /**
     * Constructor de la clase Pregunta.
     * Permite crear una nueva instancia de pregunta inicializando sus propiedades básicas.
     * @param type El tipo de pregunta.
     * @param level El nivel de Bloom de la pregunta.
     * @param text El texto de la pregunta.
     * @param options Las opciones de respuesta.
     * @param correctAnswerIndex El índice de la respuesta correcta.
     */
    public Pregunta(TipoPregunta type, NivelBloom level, String text, String[] options, int correctAnswerIndex) {
        this.type = type;
        this.level = level;
        this.text = text;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    // Métodos "getter" para acceder a las propiedades de la pregunta:
    public TipoPregunta getType() {
        return type;
    }

    public NivelBloom getLevel() {
        return level;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    // Métodos para gestionar la respuesta del usuario:
    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer; // Establece la respuesta del usuario.
    }

    public int getUserAnswer() {
        return userAnswer; // Obtiene la respuesta del usuario.
    }

    public boolean isCorrect() {
        return userAnswer == correctAnswerIndex; // Verifica si la respuesta del usuario es correcta.
    }
}