package utils;

import model.NivelBloom;
import model.Pregunta;
import model.TipoPregunta;

import java.io.*;
import java.util.*;

public class CargaArchivo {

    private static String subject = "";
    private static String evaluation = "";

    public static String getSubject() {
        return subject;
    }

    public static String getEvaluation() {
        return evaluation;
    }

    public static List<Pregunta> loadQuestionsFromFile(File file) throws IOException {
        List<Pregunta> questions = new ArrayList<>();

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
                        TipoPregunta type = TipoPregunta.valueOf(parts[1].toUpperCase());
                        NivelBloom level = NivelBloom.valueOf(parts[2].toUpperCase());
                        String text = parts[3];
                        String[] options;
                        int correctIndex;

                        if (type == TipoPregunta.MULTIPLE && parts.length >= 9) {
                            options = Arrays.copyOfRange(parts, 4, 8);
                            correctIndex = Integer.parseInt(parts[8]) - 1;
                        } else if (type == TipoPregunta.VERDADERO_FALSO && parts.length >= 7) {
                            options = Arrays.copyOfRange(parts, 4, 6);
                            correctIndex = Integer.parseInt(parts[6]) - 1;
                        } else {
                            continue; // Línea malformada o incompleta
                        }

                        questions.add(new Pregunta(type, level, text, options, correctIndex));
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
