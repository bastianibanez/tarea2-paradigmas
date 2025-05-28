package app;

import model.Question;
import model.QuestionType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class QuizScreen extends JFrame {

    private List<Question> questions;
    private int currentIndex = 0;
    private boolean reviewMode = false;

    private JLabel questionLabel = new JLabel();
    private JLabel progressLabel = new JLabel();
    private JLabel feedbackLabel = new JLabel();
    private JRadioButton[] optionButtons = new JRadioButton[4];
    private ButtonGroup optionGroup = new ButtonGroup();
    private JButton prevButton = new JButton("Anterior");
    private JButton nextButton = new JButton("Siguiente");

    public QuizScreen(List<Question> questions) {
        this(questions, false);
    }

    public QuizScreen(List<Question> questions, boolean reviewMode) {
        super(reviewMode ? "Revisión de respuestas" : "Prueba - Preguntas");
        this.questions = questions;
        this.reviewMode = reviewMode;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel superior
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        progressLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        questionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(progressLabel);
        topPanel.add(Box.createVerticalStrut(5));
        topPanel.add(questionLabel);
        topPanel.add(Box.createVerticalStrut(10));
        add(topPanel, BorderLayout.NORTH);

        // Opciones
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JRadioButton();
            optionGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
            int index = i;
            optionButtons[i].addActionListener(e -> guardarRespuesta(index));
        }
        add(optionsPanel, BorderLayout.CENTER);

        // Panel inferior: feedback + navegación
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        feedbackLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(feedbackLabel);
        bottomPanel.add(Box.createVerticalStrut(10));

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        prevButton.setPreferredSize(new Dimension(120, 30));
        nextButton.setPreferredSize(new Dimension(120, 30));
        navPanel.add(prevButton);
        navPanel.add(nextButton);

        bottomPanel.add(navPanel);
        add(bottomPanel, BorderLayout.SOUTH);

        // Listeners
        prevButton.addActionListener(this::botonAnterior);
        nextButton.addActionListener(this::botonSiguiente);

        actualizarPregunta();
        setVisible(true);
    }

    private void actualizarPregunta() {
        Question q = questions.get(currentIndex);

        questionLabel.setText("(" + q.getType() + " - " + q.getLevel() + ") " + q.getText());
        progressLabel.setText("Pregunta " + (currentIndex + 1) + " de " + questions.size());

        if (q.getType() == QuestionType.TRUE_FALSE) {
            optionButtons[0].setText(q.getOptions()[0]);
            optionButtons[1].setText(q.getOptions()[1]);
            optionButtons[0].setVisible(true);
            optionButtons[1].setVisible(true);
            optionButtons[2].setVisible(false);
            optionButtons[3].setVisible(false);
        } else {
            for (int i = 0; i < optionButtons.length; i++) {
                optionButtons[i].setText(q.getOptions()[i]);
                optionButtons[i].setVisible(true);
            }
        }

        optionGroup.clearSelection();
        int prevAnswer = q.getUserAnswer();
        if (prevAnswer >= 0 && optionButtons[prevAnswer].isVisible()) {
            optionButtons[prevAnswer].setSelected(true);
        }

        if (reviewMode) {
            for (JRadioButton button : optionButtons) {
                button.setEnabled(false);
            }

            if (q.isCorrect()) {
                feedbackLabel.setText("Correcto ✅");
                feedbackLabel.setForeground(new Color(0, 128, 0));
            } else {
                feedbackLabel.setText("Incorrecto ❌ - Respuesta correcta: " + q.getOptions()[q.getCorrectAnswerIndex()]);
                feedbackLabel.setForeground(Color.RED);
            }
        } else {
            feedbackLabel.setText(" ");
            for (JRadioButton button : optionButtons) {
                button.setEnabled(true);
            }
        }

        prevButton.setEnabled(currentIndex > 0);
        nextButton.setText(currentIndex == questions.size() - 1 ? (reviewMode ? "Cerrar" : "Finalizar") : "Siguiente");
    }

    private void guardarRespuesta(int selectedIndex) {
        if (!reviewMode) {
            Question q = questions.get(currentIndex);
            q.setUserAnswer(selectedIndex);
        }
    }

    private void botonAnterior(ActionEvent e) {
        if (currentIndex > 0) {
            currentIndex--;
            actualizarPregunta();
        }
    }

    private void botonSiguiente(ActionEvent e) {
        Question q = questions.get(currentIndex);

        if (!reviewMode && q.getUserAnswer() < 0) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una respuesta antes de continuar.", "Respuesta requerida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (currentIndex < questions.size() - 1) {
            currentIndex++;
            actualizarPregunta();
        } else {
            if (reviewMode) {
                this.dispose();
            } else {
                new ResultScreen(questions);
                this.dispose();
            }
        }
    }
}
