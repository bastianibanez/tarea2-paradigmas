package model;

public class Pregunta {

    private TipoPregunta type;
    private NivelBloom level;
    private String text;
    private String[] options;
    private int correctAnswerIndex; // 0-based
    private int userAnswer = -1; // -1 si no respondió

    public Pregunta(TipoPregunta type, NivelBloom level, String text, String[] options, int correctAnswerIndex) {
        this.type = type;
        this.level = level;
        this.text = text;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

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

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public boolean isCorrect() {
        return userAnswer == correctAnswerIndex;
    }
}