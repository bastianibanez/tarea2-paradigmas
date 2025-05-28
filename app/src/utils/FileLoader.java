package utils;

import model.BloomLevel;
import model.Question;
import model.QuestionType;

import java.io.*;
import java.util.*;

public class FileLoader {

    private static String subject = "";
    private static String evaluation = "";

    public static String getSubject() {
        return subject;
    }

    public static String getEvaluation() {
        return evaluation;
    }

    public static List<Question> loadQuestionsFromFile(File file) throws IOException {
        List<Question> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Asignatura:")) {
                    subject = line.split(":", 2)[1].trim();
                } else if (line.startsWith("Evaluación:")) {
                    evaluation = line.split(":", 2)[1].trim();
                } else if (line.contains("|")) {
                    String[] parts = line.split("\\|");

                    try {
                        QuestionType type = QuestionType.valueOf(parts[1].toUpperCase());
                        BloomLevel level = BloomLevel.valueOf(parts[2].toUpperCase());
                        String text = parts[3];
                        String[] options;
                        int correctIndex;

                        if (type == QuestionType.MULTIPLE && parts.length >= 9) {
                            options = Arrays.copyOfRange(parts, 4, 8);
                            correctIndex = Integer.parseInt(parts[8]) - 1;
                        } else if (type == QuestionType.TRUE_FALSE && parts.length >= 7) {
                            options = Arrays.copyOfRange(parts, 4, 6);
                            correctIndex = Integer.parseInt(parts[6]) - 1;
                        } else {
                            continue; // Línea malformada o incompleta
                        }

                        questions.add(new Question(type, level, text, options, correctIndex));
                    } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                        // Línea malformada: ignorar
                        System.err.println("Línea ignorada por error de formato: " + line);
                    }
                }
            }
        }

        return questions;
    }
}
