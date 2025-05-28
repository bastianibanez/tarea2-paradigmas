package app;

import model.Question;
import model.QuestionType;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultScreen extends JFrame {

    public ResultScreen(List<Question> questions) {
        super("Resumen de resultados");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        StringBuilder sb = new StringBuilder();
        sb.append("Resumen de resultados:\n\n");

        Map<QuestionType, List<Question>> grouped = questions.stream()
                .collect(Collectors.groupingBy(Question::getType));

        for (QuestionType type : QuestionType.values()) {
            List<Question> list = grouped.getOrDefault(type, List.of());
            long correctCount = list.stream().filter(Question::isCorrect).count();
            long total = list.size();
            long incorrectCount = total - correctCount;

            double correctPercent = total == 0 ? 0 : (correctCount * 100.0 / total);
            double incorrectPercent = total == 0 ? 0 : (incorrectCount * 100.0 / total);

            sb.append(String.format("%-15s: Correctas: %2d / %2d (%.1f%%) | Incorrectas: %2d (%.1f%%)\n",
                    type == QuestionType.MULTIPLE ? "Opción múltiple" : "Verdadero/Falso",
                    correctCount, total, correctPercent,
                    incorrectCount, incorrectPercent));
        }

        sb.append("\nTotal preguntas: ").append(questions.size());
        long totalCorrect = questions.stream().filter(Question::isCorrect).count();
        long totalIncorrect = questions.size() - totalCorrect;
        sb.append("\nTotal correctas: ").append(totalCorrect);
        sb.append("\nTotal incorrectas: ").append(totalIncorrect);
        sb.append("\nPorcentaje total: ")
                .append(String.format("%.1f%%", (totalCorrect * 100.0 / questions.size())));

        resultArea.setText(sb.toString());

        JButton reviewButton = new JButton("Revisar respuestas");
        reviewButton.addActionListener(e -> {
            new QuizScreen(questions, true);
            this.dispose();
        });

        add(new JScrollPane(resultArea), BorderLayout.CENTER);
        add(reviewButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
