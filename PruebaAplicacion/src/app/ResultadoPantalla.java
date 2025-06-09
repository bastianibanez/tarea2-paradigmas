package app;

import java.awt.*; // Para manejar la posición de la ventana.
import java.awt.event.*; // Para los eventos de los botones (clics, etc.).
import java.util.List; // Para trabajar con listas de preguntas.
import java.util.Map; // Para agrupar las preguntas por tipo.
import java.util.stream.Collectors; // Para usar operaciones de streams (groupingBy).
import javax.swing.*; // Clases principales para la interfaz gráfica.
import model.Pregunta; // La clase que representa una pregunta.
import model.TipoPregunta; // El enum para los tipos de pregunta (Múltiple, V/F).

/**
 * Pantalla que muestra los resultados de la prueba.
 * Calcula y exhibe el número de respuestas correctas e incorrectas por tipo de pregunta.
 */
public class ResultadoPantalla extends javax.swing.JFrame {
    // Variables para mover la ventana sin bordes (xMouse, yMouse)
    int xMouse, yMouse;
    /**
     * Constructor principal de la pantalla de resultados.
     * @param preguntas La lista de preguntas con las respuestas del usuario.
     * @param loc La ubicación previa de la ventana.
     */
    public ResultadoPantalla(List<Pregunta> preguntas, Point loc) {
        setUndecorated(true);// Quita los bordes de la ventana.
        initComponents();// Inicializa los componentes visuales (generado por NetBeans).
        this.preguntas = preguntas;
        this.preguntas = preguntas;
        this.loc = loc;
        
        if (loc != null) {
            this.setLocation(loc);// Restaura la posición de la ventana.
        }
         // Genera el resumen de resultados
        StringBuilder sb = new StringBuilder();
        sb.append("Resumen de resultados:\n\n");
        
        // Agrupa las preguntas por su tipo (MULTIPLE, VERDADERO_FALSO)
        Map<TipoPregunta, List<Pregunta>> grouped = preguntas.stream()
            .collect(Collectors.groupingBy(Pregunta::getType));
        
        // Itera sobre cada tipo de pregunta para calcular estadísticas
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

        
        ResultadoTexto.setText(sb.toString());// Muestra el resumen en el área de texto.
    }
    

    /**
     * Componentes generados automáticamente por el diseñador de NetBeans.
     * NO TOCAR.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Back = new javax.swing.JPanel();
        Head = new javax.swing.JPanel();
        Exit = new javax.swing.JPanel();
        Cerrar = new javax.swing.JLabel();
        minimizar = new javax.swing.JLabel();
        Bottom = new javax.swing.JPanel();
        BotonPrueba = new javax.swing.JLabel();
        Medium = new javax.swing.JPanel();
        ResultadoTexto = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Back.setBackground(new java.awt.Color(31, 30, 35));
        Back.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Head.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                HeadMouseDragged(evt);
            }
        });
        Head.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                HeadMousePressed(evt);
            }
        });

        Exit.setBackground(new java.awt.Color(31, 30, 35));
        Exit.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        Cerrar.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        Cerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/cerrar.png"))); // NOI18N
        Cerrar.setAlignmentY(0.0F);
        Cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CerrarMouseExited(evt);
            }
        });

        minimizar.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        minimizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/min.png"))); // NOI18N
        minimizar.setAlignmentY(0.0F);
        minimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        minimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minimizarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout ExitLayout = new javax.swing.GroupLayout(Exit);
        Exit.setLayout(ExitLayout);
        ExitLayout.setHorizontalGroup(
            ExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExitLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(Cerrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minimizar)
                .addContainerGap(722, Short.MAX_VALUE))
        );
        ExitLayout.setVerticalGroup(
            ExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExitLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout HeadLayout = new javax.swing.GroupLayout(Head);
        Head.setLayout(HeadLayout);
        HeadLayout.setHorizontalGroup(
            HeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeadLayout.createSequentialGroup()
                .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        HeadLayout.setVerticalGroup(
            HeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Back.add(Head, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 30));

        Bottom.setBackground(new java.awt.Color(31, 30, 35));
        Bottom.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BotonPrueba.setBackground(new java.awt.Color(31, 30, 35));
        BotonPrueba.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        BotonPrueba.setForeground(new java.awt.Color(242, 242, 242));
        BotonPrueba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonReviPrueb.png"))); // NOI18N
        BotonPrueba.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonPrueba.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonPruebaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotonPruebaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotonPruebaMouseExited(evt);
            }
        });
        Bottom.add(BotonPrueba, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));

        Back.add(Bottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 770, 90));

        Medium.setBackground(new java.awt.Color(31, 30, 35));

        ResultadoTexto.setBackground(new java.awt.Color(31, 30, 35));
        ResultadoTexto.setColumns(20);
        ResultadoTexto.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        ResultadoTexto.setForeground(new java.awt.Color(242, 242, 242));
        ResultadoTexto.setRows(5);
        ResultadoTexto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ResultadoTexto.setFocusable(false);

        javax.swing.GroupLayout MediumLayout = new javax.swing.GroupLayout(Medium);
        Medium.setLayout(MediumLayout);
        MediumLayout.setHorizontalGroup(
            MediumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MediumLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(ResultadoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        MediumLayout.setVerticalGroup(
            MediumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MediumLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(ResultadoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        Back.add(Medium, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 770, 280));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_CerrarMouseClicked

    private void CerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseEntered
        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/cerrarTarget.png")));
    }//GEN-LAST:event_CerrarMouseEntered

    private void CerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseExited
        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/cerrar.png")));
    }//GEN-LAST:event_CerrarMouseExited

    private void minimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarMouseClicked
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_minimizarMouseClicked

    private void minimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarMouseEntered
        minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/minTarget.png")));
    }//GEN-LAST:event_minimizarMouseEntered

    private void minimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarMouseExited
        minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/min.png")));
    }//GEN-LAST:event_minimizarMouseExited

    private void HeadMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeadMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
        loc = this.getLocation();
    }//GEN-LAST:event_HeadMouseDragged

    private void HeadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeadMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_HeadMousePressed

    private void BotonPruebaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonPruebaMouseClicked
        new PruebaPantalla(preguntas, true, loc).setVisible(true);
        this.dispose();        
    }//GEN-LAST:event_BotonPruebaMouseClicked

    private void BotonPruebaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonPruebaMouseEntered
        BotonPrueba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonReviPruebTarget.png")));
    }//GEN-LAST:event_BotonPruebaMouseEntered

    private void BotonPruebaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonPruebaMouseExited
        BotonPrueba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonReviPrueb.png")));
    }//GEN-LAST:event_BotonPruebaMouseExited

    private Point loc;// Guarda la ubicación de la ventana para mantenerla al cambiar.
    private List<Pregunta> preguntas;// Lista de preguntas con las respuestas del usuario.
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Back;
    private javax.swing.JLabel BotonPrueba;
    private javax.swing.JPanel Bottom;
    private javax.swing.JLabel Cerrar;
    private javax.swing.JPanel Exit;
    private javax.swing.JPanel Head;
    private javax.swing.JPanel Medium;
    private javax.swing.JTextArea ResultadoTexto;
    private javax.swing.JLabel minimizar;
    // End of variables declaration//GEN-END:variables
}
