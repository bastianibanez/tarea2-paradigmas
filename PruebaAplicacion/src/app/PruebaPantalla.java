package app;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import model.Pregunta;
import model.TipoPregunta;
import java.awt.Point;
import javax.swing.Timer;

public class PruebaPantalla extends javax.swing.JFrame {

    int xMouse, yMouse;
    
    public PruebaPantalla(List<Pregunta> preguntas, boolean modoRevision, Point loc) {
        initComponents();
        this.preguntas = preguntas;
        this.modoRevision = modoRevision;
        this.loc = loc;
        this.botonesOpciones = new JRadioButton[]{opcion1, opcion2, opcion3, opcion4};
        
        if (loc != null) {
            this.setLocation(loc);
        }
        
        for (int i = 0; i < botonesOpciones.length; i++) {
            final int index = i;
            botonesOpciones[i].addActionListener(e -> guardarRespuesta(index));
        }
        
        actualizarPregunta();
    }
    
    private void actualizarPregunta() {
        Pregunta preguntaActual = preguntas.get(indiceActual);

        pregunta.setText("(" + preguntaActual.getType() + " - " + preguntaActual.getLevel() + ") " + preguntaActual.getText());
        progreso.setText("Pregunta " + (indiceActual + 1) + " de " + preguntas.size());

        String[] opciones = preguntaActual.getOptions();

        for (int i = 0; i < botonesOpciones.length; i++) {
            if (i < opciones.length) {
                botonesOpciones[i].setText(opciones[i]);
                botonesOpciones[i].setVisible(true);
            } else {
                botonesOpciones[i].setVisible(false);
            }
        }

        grupoOpciones.clearSelection();
        int respuestaAnterior = preguntaActual.getUserAnswer();
        if (respuestaAnterior >= 0 && respuestaAnterior < botonesOpciones.length && botonesOpciones[respuestaAnterior].isVisible()) {
            botonesOpciones[respuestaAnterior].setSelected(true);
        }
        if (indiceActual == preguntas.size() - 2){
            if (term == true){
                timer = new Timer(70, null); 
                final int[] frameIndex = {0};

                timer.addActionListener(e -> {
                    if (frameIndex[0] < fadeOut.length) {
                        SiguientePB.setIcon(new javax.swing.ImageIcon(getClass().getResource(fadeOut[frameIndex[0]])));
                        frameIndex[0]++;
                    } else {
                        timer.stop();
                        term = false;
                    }
                });
                timer.start();
            }
        }
        
        if (indiceActual == preguntas.size() - 1) {
            timer = new Timer(70, null); 
            final int[] frameIndex = {0};

            timer.addActionListener(e -> {
                if (frameIndex[0] < fadeIn.length) {
                    SiguientePB.setIcon(new javax.swing.ImageIcon(getClass().getResource(fadeIn[frameIndex[0]])));
                    frameIndex[0]++;
                } else {
                    timer.stop();
                    term = true;
                }
            });
            timer.start();
        }
        
        if (modoRevision) {
            Pregunta p = preguntas.get(indiceActual);
            int correcta = p.getCorrectAnswerIndex();
            int usuario = p.getUserAnswer();

            for (int i = 0; i < botonesOpciones.length; i++) {
                JRadioButton boton = botonesOpciones[i];
                boton.setEnabled(false); // Desactiva selección

                if (i == correcta) {
                    boton.setBackground(Color.GREEN); // Correcta
                    boton.setOpaque(true);
                } else if (i == usuario && usuario != correcta) {
                    boton.setBackground(Color.RED); // Incorrecta marcada
                    boton.setOpaque(true);
                } else {
                    boton.setBackground(null);
                    boton.setOpaque(false);
                }
    }

    feedbackLabel.setText(usuario == correcta ? "Correcto" : "Incorrecto. La respuesta correcta está en verde!!.");
} else {
    feedbackLabel.setText(""); // Oculta feedback en modo normal
}

    }
    
    private void guardarRespuesta(int indiceSeleccionado) {
        if (!modoRevision) {
            preguntas.get(indiceActual).setUserAnswer(indiceSeleccionado);
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoOpciones = new javax.swing.ButtonGroup();
        Back = new javax.swing.JPanel();
        Head = new javax.swing.JPanel();
        Exit = new javax.swing.JPanel();
        Cerrar = new javax.swing.JLabel();
        minimizar = new javax.swing.JLabel();
        Bottom = new javax.swing.JPanel();
        AnteriorPB = new javax.swing.JLabel();
        SiguientePB = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        opcion1 = new javax.swing.JRadioButton();
        opcion2 = new javax.swing.JRadioButton();
        opcion3 = new javax.swing.JRadioButton();
        opcion4 = new javax.swing.JRadioButton();
        pregunta = new javax.swing.JLabel();
        progreso = new javax.swing.JLabel();
        feedbackLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        Back.setBackground(new java.awt.Color(31, 30, 35));
        Back.setPreferredSize(new java.awt.Dimension(770, 400));
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
        Cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        minimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        AnteriorPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonAnteriorOs.png"))); // NOI18N
        AnteriorPB.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        AnteriorPB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AnteriorPBMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AnteriorPBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AnteriorPBMouseExited(evt);
            }
        });

        SiguientePB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonSiguiente.png"))); // NOI18N
        SiguientePB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SiguientePB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SiguientePBMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SiguientePBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SiguientePBMouseExited(evt);
            }
        });

        javax.swing.GroupLayout BottomLayout = new javax.swing.GroupLayout(Bottom);
        Bottom.setLayout(BottomLayout);
        BottomLayout.setHorizontalGroup(
            BottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BottomLayout.createSequentialGroup()
                .addGap(297, 297, 297)
                .addComponent(AnteriorPB, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(SiguientePB)
                .addContainerGap(313, Short.MAX_VALUE))
        );
        BottomLayout.setVerticalGroup(
            BottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AnteriorPB)
                    .addComponent(SiguientePB))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        Back.add(Bottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 770, 90));

        Menu.setBackground(new java.awt.Color(31, 30, 35));
        Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        opcion1.setBackground(new java.awt.Color(31, 30, 35));
        grupoOpciones.add(opcion1);
        opcion1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        opcion1.setForeground(new java.awt.Color(255, 255, 255));
        opcion1.setText("jRadioButton1");
        opcion1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        opcion1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        Menu.add(opcion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 30));

        opcion2.setBackground(new java.awt.Color(31, 30, 35));
        grupoOpciones.add(opcion2);
        opcion2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        opcion2.setForeground(new java.awt.Color(255, 255, 255));
        opcion2.setText("jRadioButton2");
        opcion2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        opcion2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        Menu.add(opcion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, 30));

        opcion3.setBackground(new java.awt.Color(31, 30, 35));
        grupoOpciones.add(opcion3);
        opcion3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        opcion3.setForeground(new java.awt.Color(255, 255, 255));
        opcion3.setText("jRadioButton3");
        opcion3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Menu.add(opcion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 30));

        opcion4.setBackground(new java.awt.Color(31, 30, 35));
        grupoOpciones.add(opcion4);
        opcion4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        opcion4.setForeground(new java.awt.Color(255, 255, 255));
        opcion4.setText("jRadioButton4");
        opcion4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Menu.add(opcion4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, 30));

        pregunta.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        pregunta.setForeground(new java.awt.Color(255, 255, 255));
        pregunta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pregunta.setText("Pregunta");
        Menu.add(pregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 60));

        progreso.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        progreso.setForeground(new java.awt.Color(255, 255, 255));
        progreso.setText("Pregunta 0 de 0 ");
        Menu.add(progreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 110, 20));

        feedbackLabel.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        feedbackLabel.setForeground(new java.awt.Color(255, 255, 255));
        feedbackLabel.setText("Feedback");
        Menu.add(feedbackLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, -1, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BarraProgreso.png"))); // NOI18N
        Menu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 75, -1, -1));

        Back.add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 690, 270));

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

    private void AnteriorPBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AnteriorPBMouseClicked
        if (indiceActual > 0) {
                AnteriorPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonAnterior.png")));
                indiceActual--;
                actualizarPregunta();
            }
    }//GEN-LAST:event_AnteriorPBMouseClicked

    private void SiguientePBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SiguientePBMouseClicked
        if (preguntas.get(indiceActual).getUserAnswer() == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una respuesta.");
                return;
            }
            AnteriorPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonAnterior.png")));
            AnteriorPB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            if (indiceActual < preguntas.size() - 1) {
                indiceActual++;
                actualizarPregunta();
            } else {
                if (modoRevision == true){
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Has terminado la prueba.");
                    new ResultadoPantalla(preguntas, loc).setVisible(true);
                    this.dispose();
                }
            }
    }//GEN-LAST:event_SiguientePBMouseClicked

    private void SiguientePBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SiguientePBMouseEntered
        if (indiceActual == preguntas.size() - 1){
            if (term == true) {
                SiguientePB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonTerminarTarget.png")));
            }
        }
        else {
            SiguientePB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonSiguienteTarget.png")));
        }
        
        
    }//GEN-LAST:event_SiguientePBMouseEntered

    private void SiguientePBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SiguientePBMouseExited
        if (indiceActual == preguntas.size() - 1) {
            if (term == true){
                SiguientePB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonTerminar5.png")));
            }
        }
        else {
            SiguientePB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonSiguiente.png")));
        }
        
    }//GEN-LAST:event_SiguientePBMouseExited

    private void AnteriorPBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AnteriorPBMouseEntered
        if (indiceActual > 0){
            AnteriorPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonAnteriorTarget.png")));
        }
    }//GEN-LAST:event_AnteriorPBMouseEntered

    private void AnteriorPBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AnteriorPBMouseExited
        if (indiceActual > 0) {
            AnteriorPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonAnterior.png")));
        }
        else {
            AnteriorPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonAnteriorOs.png")));
            AnteriorPB.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR)); 
        }
    }//GEN-LAST:event_AnteriorPBMouseExited
    private Timer timer;
    private boolean term;
    private final String[] fadeIn = {
    "/imagCom/BotonTerminar1.png",
    "/imagCom/BotonTerminar2.png",
    "/imagCom/BotonTerminar3.png",
    "/imagCom/BotonTerminar4.png",
    "/imagCom/BotonTerminar5.png"
    };
    private final String[] fadeOut = {
    "/imagCom/BotonTerminar5.png",
    "/imagCom/BotonTerminar4.png",
    "/imagCom/BotonTerminar3.png",
    "/imagCom/BotonTerminar2.png",
    "/imagCom/BotonTerminar1.png"
    };
    private Point loc;
    private int indiceActual = 0;
    private boolean modoRevision;
    private List<Pregunta> preguntas;
    private JRadioButton[] botonesOpciones;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AnteriorPB;
    private javax.swing.JPanel Back;
    private javax.swing.JPanel Bottom;
    private javax.swing.JLabel Cerrar;
    private javax.swing.JPanel Exit;
    private javax.swing.JPanel Head;
    private javax.swing.JPanel Menu;
    private javax.swing.JLabel SiguientePB;
    private javax.swing.JLabel feedbackLabel;
    private javax.swing.ButtonGroup grupoOpciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel minimizar;
    private javax.swing.JRadioButton opcion1;
    private javax.swing.JRadioButton opcion2;
    private javax.swing.JRadioButton opcion3;
    private javax.swing.JRadioButton opcion4;
    private javax.swing.JLabel pregunta;
    private javax.swing.JLabel progreso;
    // End of variables declaration//GEN-END:variables
}
