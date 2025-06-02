
package app;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import model.Pregunta;
import utils.CargaArchivo;

public class InicioPantalla extends javax.swing.JFrame {

    int xMouse, yMouse;
    
    public InicioPantalla() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Back = new javax.swing.JPanel();
        Head = new javax.swing.JPanel();
        Exit = new javax.swing.JPanel();
        Cerrar = new javax.swing.JLabel();
        minimizar = new javax.swing.JLabel();
        Botones = new javax.swing.JPanel();
        CargarB = new javax.swing.JLabel();
        Iniciar = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        AsignaturaPanel = new javax.swing.JPanel();
        Asignatura = new javax.swing.JLabel();
        AsignaturaR = new javax.swing.JLabel();
        barra = new javax.swing.JLabel();
        EvaluacionPanel = new javax.swing.JPanel();
        Evaluacion = new javax.swing.JLabel();
        EvaluacionR = new javax.swing.JLabel();
        barra1 = new javax.swing.JLabel();
        EstadisticaPanel = new javax.swing.JPanel();
        Estadistica = new javax.swing.JLabel();
        EstadisticaR = new javax.swing.JLabel();
        barra2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
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

        Botones.setBackground(new java.awt.Color(31, 30, 35));
        Botones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CargarB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonCARGAR.png"))); // NOI18N
        CargarB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CargarBMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CargarBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CargarBMouseExited(evt);
            }
        });
        Botones.add(CargarB, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        Iniciar.setBackground(new java.awt.Color(179, 179, 179));
        Iniciar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Iniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonINICIARos.png"))); // NOI18N
        Iniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IniciarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                IniciarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                IniciarMouseExited(evt);
            }
        });
        Botones.add(Iniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        Back.add(Botones, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 270, 370));

        Menu.setBackground(new java.awt.Color(31, 30, 35));

        AsignaturaPanel.setBackground(new java.awt.Color(31, 30, 35));
        AsignaturaPanel.setPreferredSize(new java.awt.Dimension(140, 123));
        AsignaturaPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Asignatura.setBackground(new java.awt.Color(0, 0, 0));
        Asignatura.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        Asignatura.setForeground(new java.awt.Color(255, 255, 255));
        Asignatura.setText("Asignatura");
        AsignaturaPanel.add(Asignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        AsignaturaR.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        AsignaturaR.setForeground(new java.awt.Color(255, 255, 255));
        AsignaturaPanel.add(AsignaturaR, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 55, 420, 30));

        barra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/Barra.png"))); // NOI18N
        AsignaturaPanel.add(barra, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 452, -1));

        EvaluacionPanel.setBackground(new java.awt.Color(31, 30, 35));
        EvaluacionPanel.setPreferredSize(new java.awt.Dimension(140, 123));
        EvaluacionPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Evaluacion.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        Evaluacion.setForeground(new java.awt.Color(255, 255, 255));
        Evaluacion.setText("Evaluación");
        EvaluacionPanel.add(Evaluacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        EvaluacionR.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        EvaluacionR.setForeground(new java.awt.Color(255, 255, 255));
        EvaluacionPanel.add(EvaluacionR, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 55, 420, 30));

        barra1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/Barra.png"))); // NOI18N
        EvaluacionPanel.add(barra1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 452, -1));

        EstadisticaPanel.setBackground(new java.awt.Color(31, 30, 35));
        EstadisticaPanel.setPreferredSize(new java.awt.Dimension(140, 123));
        EstadisticaPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Estadistica.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        Estadistica.setForeground(new java.awt.Color(255, 255, 255));
        Estadistica.setText("Estadísticas");
        EstadisticaPanel.add(Estadistica, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        EstadisticaR.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        EstadisticaR.setForeground(new java.awt.Color(255, 255, 255));
        EstadisticaPanel.add(EstadisticaR, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 55, 420, 30));

        barra2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/Barra.png"))); // NOI18N
        EstadisticaPanel.add(barra2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 452, -1));

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MenuLayout.createSequentialGroup()
                        .addComponent(AsignaturaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EvaluacionPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EstadisticaPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AsignaturaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EvaluacionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EstadisticaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Back.add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 32, 480, 370));

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

    private void HeadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeadMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_HeadMousePressed

    private void HeadMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeadMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_HeadMouseDragged

    private void CerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_CerrarMouseClicked

    private void minimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarMouseClicked
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_minimizarMouseClicked

    private void CerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseEntered
        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/cerrarTarget.png")));
    }//GEN-LAST:event_CerrarMouseEntered

    private void CerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseExited
        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/cerrar.png")));
    }//GEN-LAST:event_CerrarMouseExited

    private void minimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarMouseEntered
        minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/minTarget.png")));
    }//GEN-LAST:event_minimizarMouseEntered

    private void minimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarMouseExited
        minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/min.png")));
    }//GEN-LAST:event_minimizarMouseExited

    private void IniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IniciarMouseClicked
        if (pruebaCargada != false){
            new PruebaPantalla(preguntas, false).setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_IniciarMouseClicked

    private void CargarBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CargarBMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                preguntas = CargaArchivo.loadQuestionsFromFile(file);

                if (preguntas.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No se encontraron preguntas en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                AsignaturaR.setText(CargaArchivo.getSubject());
                EvaluacionR.setText(CargaArchivo.getEvaluation());

                long multipleCount = preguntas.stream().filter(q -> q.getType() == model.TipoPregunta.MULTIPLE).count();
                long tfCount = preguntas.stream().filter(q -> q.getType() == model.TipoPregunta.VERDADERO_FALSO).count();

                EstadisticaR.setText("Multiple: " + multipleCount + " Verdadero/Falso: " + tfCount + " Total: " + preguntas.size());
                pruebaCargada = true;
                Iniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonINICIAR.png")));

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_CargarBMouseClicked

    private void IniciarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IniciarMouseEntered
        if (pruebaCargada != false){
            Iniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonINICIARcl.png")));
        }
    }//GEN-LAST:event_IniciarMouseEntered

    private void IniciarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IniciarMouseExited
        if (pruebaCargada != false){
            Iniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonINICIAR.png")));
        }
    }//GEN-LAST:event_IniciarMouseExited

    private void CargarBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CargarBMouseEntered
        CargarB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonCARGARcl.png")));
    }//GEN-LAST:event_CargarBMouseEntered

    private void CargarBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CargarBMouseExited
        CargarB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonCARGAR.png")));
    }//GEN-LAST:event_CargarBMouseExited
    private boolean pruebaCargada = false;
    private List<Pregunta> preguntas;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Asignatura;
    private javax.swing.JPanel AsignaturaPanel;
    private javax.swing.JLabel AsignaturaR;
    private javax.swing.JPanel Back;
    private javax.swing.JPanel Botones;
    private javax.swing.JLabel CargarB;
    private javax.swing.JLabel Cerrar;
    private javax.swing.JLabel Estadistica;
    private javax.swing.JPanel EstadisticaPanel;
    private javax.swing.JLabel EstadisticaR;
    private javax.swing.JLabel Evaluacion;
    private javax.swing.JPanel EvaluacionPanel;
    private javax.swing.JLabel EvaluacionR;
    private javax.swing.JPanel Exit;
    private javax.swing.JPanel Head;
    private javax.swing.JLabel Iniciar;
    private javax.swing.JPanel Menu;
    private javax.swing.JLabel barra;
    private javax.swing.JLabel barra1;
    private javax.swing.JLabel barra2;
    private javax.swing.JLabel minimizar;
    // End of variables declaration//GEN-END:variables
}
