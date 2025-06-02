package app;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.*;
import model.Pregunta;
import model.TipoPregunta;

public class ResultadoPantalla extends JFrame {
    
    public ResultadoPantalla(List<Pregunta> preguntas) {
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

        Map<TipoPregunta, List<Pregunta>> grouped = preguntas.stream()
                .collect(Collectors.groupingBy(Pregunta::getType));

        for (TipoPregunta type : TipoPregunta.values()) {
            List<Pregunta> list = grouped.getOrDefault(type, List.of());
            long correctCount = list.stream().filter(Pregunta::isCorrect).count();
            long total = list.size();
            long incorrectCount = total - correctCount;

            double correctPercent = total == 0 ? 0 : (correctCount * 100.0 / total);
            double incorrectPercent = total == 0 ? 0 : (incorrectCount * 100.0 / total);

            sb.append(String.format("%-15s: Correctas: %2d / %2d (%.1f%%) | Incorrectas: %2d (%.1f%%)\n",
                    type == TipoPregunta.MULTIPLE ? "Opción múltiple" : "Verdadero/Falso",
                    correctCount, total, correctPercent,
                    incorrectCount, incorrectPercent));
        }

        sb.append("\nTotal preguntas: ").append(preguntas.size());
        long totalCorrect = preguntas.stream().filter(Pregunta::isCorrect).count();
        long totalIncorrect = preguntas.size() - totalCorrect;
        sb.append("\nTotal correctas: ").append(totalCorrect);
        sb.append("\nTotal incorrectas: ").append(totalIncorrect);
        sb.append("\nPorcentaje total: ")
                .append(String.format("%.1f%%", (totalCorrect * 100.0 / preguntas.size())));

        resultArea.setText(sb.toString());

        JButton reviewButton = new JButton("Revisar respuestas");
        reviewButton.addActionListener(e -> {
            /*new PruebaPantalla(questions, true);*/
            new PruebaPantalla(preguntas, true).setVisible(true);
            this.dispose();
        });

        add(new JScrollPane(resultArea), BorderLayout.CENTER);
        add(reviewButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
