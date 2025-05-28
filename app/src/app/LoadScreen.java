package app;

import model.Question;
import utils.FileLoader;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class LoadScreen extends JFrame {

    private JLabel subjectLabel = new JLabel("Asignatura: ");
    private JLabel evalLabel = new JLabel("Evaluación: ");
    private JLabel statsLabel = new JLabel("Estadísticas: ");
    private JButton loadButton = new JButton("Cargar archivo");
    private JButton startButton = new JButton("Iniciar prueba");

    private List<Question> questions;

    public LoadScreen() {
        super("Carga de prueba");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLayout(new GridLayout(5, 1, 5, 5));
        setLocationRelativeTo(null);

        add(subjectLabel);
        add(evalLabel);
        add(statsLabel);
        add(loadButton);
        add(startButton);

        startButton.setEnabled(false);

        loadButton.addActionListener(e -> cargarArchivo());
        startButton.addActionListener(e -> iniciarPrueba());

        setVisible(true);
    }

    private void cargarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                questions = FileLoader.loadQuestionsFromFile(file);

                if (questions.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No se encontraron preguntas en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                subjectLabel.setText("Asignatura: " + FileLoader.getSubject());
                evalLabel.setText("Evaluación: " + FileLoader.getEvaluation());

                long multipleCount = questions.stream().filter(q -> q.getType() == model.QuestionType.MULTIPLE).count();
                long tfCount = questions.stream().filter(q -> q.getType() == model.QuestionType.TRUE_FALSE).count();

                statsLabel.setText("Preguntas: Multiple=" + multipleCount + ", Verdadero/Falso=" + tfCount + ", Total=" + questions.size());

                startButton.setEnabled(true);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void iniciarPrueba() {
        new QuizScreen(questions);
        this.dispose();
    }
}