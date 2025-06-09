package app;

import java.awt.*; // Para gráficos y ubicación.
import java.util.List; // Para la lista de preguntas.
import javax.swing.*; // Clases principales de Swing.
import model.Pregunta; // Clase Pregunta.
import model.TipoPregunta; // Enum TipoPregunta.
import java.awt.Point; // Para la posición de la ventana.
import javax.swing.Timer; // Para las animaciones de botones.

/**
 * Pantalla donde el usuario interactúa con las preguntas de la prueba o las revisa.
 * Maneja la navegación, la visualización de preguntas y opciones, y la retroalimentación.
 */
public class PruebaPantalla extends javax.swing.JFrame {
    
    // Variables para mover la ventana.
    int xMouse, yMouse;
    
    /**
     * Constructor de la pantalla de prueba/revisión.
     * @param preguntas La lista de preguntas a usar.
     * @param modoRevision Indica si es modo revisión.
     * @param loc La ubicación previa de la ventana.
     */
    public PruebaPantalla(List<Pregunta> preguntas, boolean modoRevision, Point loc) {
        initComponents();
        this.preguntas = preguntas;
        this.modoRevision = modoRevision;
        this.loc = loc;
        this.botonesOpciones = new JRadioButton[]{opcion1, opcion2, opcion3, opcion4};
        
        if (loc != null) {
            this.setLocation(loc);// Establece la posición de la ventana.
        }
        
        // Añade un Listener a cada botón de opción para guardar la respuesta.
        for (int i = 0; i < botonesOpciones.length; i++) {
            final int index = i;
            botonesOpciones[i].addActionListener(e -> guardarRespuesta(index));
        }
        
        actualizarPregunta();// Muestra la primera pregunta.
    }
    
    /**
     * Actualiza la UI para mostrar la pregunta actual, sus opciones, progreso y feedback.
     * Controla la visibilidad de los botones de navegación y "Terminar".
     */
    private void actualizarPregunta() {
        // Obtiene la pregunta actual de la lista usando el índice actual.
        Pregunta preguntaActual = preguntas.get(indiceActual);
        
        // Actualiza el texto de la pregunta y el progreso.
        pregunta.setText("(" + preguntaActual.getType() + " - " + preguntaActual.getLevel() + ") " + preguntaActual.getText());
        progreso.setText("Pregunta " + (indiceActual + 1) + " de " + preguntas.size());
        
        // Obtiene las opciones de respuesta para la pregunta actual.
        String[] opciones = preguntaActual.getOptions();
        
        // Itera sobre los botones de opción para configurar su texto y visibilidad.
        // Esto es útil si una pregunta tiene menos de 4 opciones.
        for (int i = 0; i < botonesOpciones.length; i++) {
            if (i < opciones.length) {
                botonesOpciones[i].setText(opciones[i]); // Establece el texto de la opción.
                botonesOpciones[i].setVisible(true); // Hace visible el botón.
            } else {
                botonesOpciones[i].setVisible(false);// Oculta los botones no utilizados.
            }
        }
        // Lógica para animar el botón "Siguiente" a "Terminar" cuando se acerca el final de la prueba.
        grupoOpciones.clearSelection();// Deselecciona opciones anteriores.
        int respuestaAnterior = preguntaActual.getUserAnswer();
        if (respuestaAnterior >= 0 && respuestaAnterior < botonesOpciones.length && botonesOpciones[respuestaAnterior].isVisible()) {
            botonesOpciones[respuestaAnterior].setSelected(true);
        }
        // Lógica para animar el botón "Siguiente" a "Terminar" cuando se acerca el final de la prueba.
        if (indiceActual == preguntas.size() - 2){ // Si estamos en la penúltima pregunta
            if (term == true){ // Si el botón "Terminar" ya estaba animado
                timer = new Timer(70, null); // Crea un nuevo temporizador
                final int[] frameIndex = {0}; // Inicializa el índice para la animación

                // Añade un ActionListener al temporizador para cambiar el icono del botón.
                timer.addActionListener(e -> {
                    if (frameIndex[0] < fadeOut.length) {
                        // Cambia el icono del botón Siguiente para mostrar la animación de "fade out".
                        SiguientePB.setIcon(new javax.swing.ImageIcon(getClass().getResource(fadeOut[frameIndex[0]])));
                        frameIndex[0]++; // Incrementa el índice para el siguiente frame de la animación.
                    } else {
                        timer.stop(); // Detiene el temporizador cuando la animación termina.
                        term = false; // Restablece la bandera 'term'.
                    }
                });
                timer.start(); // Inicia el temporizador.
            }
        }
        
        // Lógica para animar el botón "Terminar" cuando se llega a la última pregunta.
        if (indiceActual == preguntas.size() - 1) { // Si estamos en la última pregunta
            timer = new Timer(70, null); // Crea un nuevo temporizador
            final int[] frameIndex = {0}; // Inicializa el índice para la animación

            // Añade un ActionListener al temporizador para cambiar el icono del botón.
            timer.addActionListener(e -> {
                if (frameIndex[0] < fadeIn.length) {
                    // Cambia el icono del botón Siguiente para mostrar la animación de "fade in" del botón Terminar.
                    SiguientePB.setIcon(new javax.swing.ImageIcon(getClass().getResource(fadeIn[frameIndex[0]])));
                    frameIndex[0]++; // Incrementa el índice para el siguiente frame.
                } else {
                    timer.stop(); // Detiene el temporizador.
                    term = true; // Establece la bandera 'term' a true.
                }
            });
            timer.start(); // Inicia el temporizador.
        }
        // Lógica específica para el modo revisión vs. modo prueba.
          // Lógica para el modo de revisión: muestra la respuesta correcta e incorrecta.
        if (modoRevision) {
            Pregunta p = preguntas.get(indiceActual); // Obtiene la pregunta actual.
            int correcta = p.getCorrectAnswerIndex(); // Obtiene el índice de la respuesta correcta.
            int usuario = p.getUserAnswer(); // Obtiene el índice de la respuesta del usuario.

            // Itera sobre todos los botones de opción para aplicar el feedback visual.
            for (int i = 0; i < botonesOpciones.length; i++) {
                JRadioButton boton = botonesOpciones[i];
                boton.setEnabled(false); // Deshabilita los botones en modo revisión para evitar cambios.

                // Marca la opción correcta con color verde.
                if (i == correcta) {
                    boton.setBackground(Color.GREEN);
                    boton.setOpaque(true); // Hace que el fondo sea visible.
                }
                // Si la respuesta del usuario fue incorrecta, la marca con color rojo.
                else if (i == usuario && usuario != correcta) {
                    boton.setBackground(Color.RED);
                    boton.setOpaque(true);
                }
                // Las demás opciones se restablecen o se dejan sin color de fondo.
                else {
                    boton.setBackground(null); // Elimina cualquier color de fondo.
                    boton.setOpaque(false); // Hace que el fondo sea transparente.
                }
            }
            // Muestra un mensaje de retroalimentación en la etiqueta.
            feedbackLabel.setText(usuario == correcta ? "Correcto" : "Incorrecto. La respuesta correcta está en verde!!.");
        } else {
            feedbackLabel.setText(""); // En modo normal, la etiqueta de feedback está vacía.
        }
    }
    /**
     * Guarda la respuesta seleccionada por el usuario para la pregunta actual.
     * Solo guarda la respuesta si la pantalla no está en modo de revisión.
     * @param indiceSeleccionado El índice de la opción que el usuario seleccionó.
     */
    private void guardarRespuesta(int indiceSeleccionado) {
        if (!modoRevision) { // Solo guarda la respuesta si NO está en modo revisión.
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
    // Métodos de manejo de eventos para los botones de la barra superior (Cerrar y Minimizar).

    /**
     * Cierra la aplicación cuando se hace clic en el botón de cerrar.
     * @param evt El evento de mouse.
     */
    private void CerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseClicked
        System.exit(0);// Termina la aplicación.
    }//GEN-LAST:event_CerrarMouseClicked

    /**
     * Cambia el icono del botón de cerrar cuando el mouse entra en su área.
     * @param evt El evento de mouse.
     */
    private void CerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseEntered
        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/cerrarTarget.png")));
    }//GEN-LAST:event_CerrarMouseEntered
    
    /**
     * Restaura el icono del botón de cerrar cuando el mouse sale de su área.
     * @param evt El evento de mouse.
     */
    private void CerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseExited
        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/cerrar.png")));
    }//GEN-LAST:event_CerrarMouseExited

    /**
     * Minimiza la ventana cuando se hace clic en el botón de minimizar.
     * @param evt El evento de mouse.
     */
    private void minimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarMouseClicked
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_minimizarMouseClicked

    /**
     * Cambia el icono del botón de minimizar cuando el mouse entra en su área.
     * @param evt El evento de mouse.
     */
    private void minimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarMouseEntered
        minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/minTarget.png")));
    }//GEN-LAST:event_minimizarMouseEntered
    
    /**
     * Restaura el icono del botón de minimizar cuando el mouse sale de su área.
     * @param evt El evento de mouse.
     */
    private void minimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarMouseExited
        minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/min.png")));
    }//GEN-LAST:event_minimizarMouseExited
    // Métodos para mover la ventana sin bordes arrastrando el panel superior.

    /**
     * Mueve la ventana cuando el mouse es arrastrado sobre el panel superior.
     * @param evt El evento de arrastre del mouse.
     */
    private void HeadMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeadMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
        loc = this.getLocation();
    }//GEN-LAST:event_HeadMouseDragged

    /**
     * Captura las coordenadas del mouse al presionar el botón sobre el panel superior.
     * Esto se usa como referencia para calcular el desplazamiento al arrastrar.
     * @param evt El evento de presión del mouse.
     */
    private void HeadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeadMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_HeadMousePressed

    // Métodos de manejo de eventos para los botones de navegación (Anterior y Siguiente).
    /**
     * Maneja el clic en el botón "Anterior".
     * Retrocede a la pregunta anterior si no es la primera pregunta.
     * @param evt El evento de clic del mouse.
     */
    private void AnteriorPBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AnteriorPBMouseClicked
        if (indiceActual > 0) {
                AnteriorPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonAnterior.png")));
                indiceActual--;
                actualizarPregunta();
            }
    }//GEN-LAST:event_AnteriorPBMouseClicked
    
    /**
     * Cambia el icono del botón "Anterior" al pasar el mouse por encima.
     * Solo si no es la primera pregunta.
     * @param evt El evento de mouse.
     */    
    private void AnteriorPBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AnteriorPBMouseEntered
        if (indiceActual > 0){// Solo si no estamos en la primera pregunta.
            AnteriorPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonAnteriorTarget.png")));// Icono resaltado.
        }
    }//GEN-LAST:event_AnteriorPBMouseEntered

    /**
     * Restaura el icono del botón "Anterior" al salir el mouse.
     * Gestiona si el botón debe estar deshabilitado visualmente (primera pregunta).
     * @param evt El evento de mouse.
     */
    private void AnteriorPBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AnteriorPBMouseExited
        if (indiceActual > 0) {// Si no es la primera pregunta.
            AnteriorPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonAnterior.png")));// Icono normal.
        }
        else {
            AnteriorPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonAnteriorOs.png")));// Icono deshabilitado.
            AnteriorPB.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR)); // Restaura el cursor predeterminado.
        }
    }//GEN-LAST:event_AnteriorPBMouseExited

    /**
     * Maneja el clic en el botón "Siguiente".
     * Avanza a la siguiente pregunta o finaliza la prueba.
     * @param evt El evento de clic del mouse.
     */
    private void SiguientePBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SiguientePBMouseClicked
        // En modo prueba, verifica si el usuario ha seleccionado una respuesta.
        if (preguntas.get(indiceActual).getUserAnswer() == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una respuesta.");// Muestra un mensaje de advertencia.
                return;// No avanza si no hay respuesta.
            }
            AnteriorPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonAnterior.png")));// Restaura el icono del botón Anterior.
            AnteriorPB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));// Cambia el cursor del botón Anterior.
            if (indiceActual < preguntas.size() - 1) {// Si no es la última pregunta.
                indiceActual++;// Incrementa el índice para ir a la siguiente pregunta.
                actualizarPregunta();// Actualiza la UI.
            } else {
                if (modoRevision == true){
                    this.dispose();
                } else {// Si es la última pregunta.
                    JOptionPane.showMessageDialog(this, "Has terminado la prueba.");
                    new ResultadoPantalla(preguntas, loc).setVisible(true);// Abre la pantalla de resultados.
                    this.dispose();// Cierra la pantalla actual.
                }
            }
    }//GEN-LAST:event_SiguientePBMouseClicked

    /**
     * Cambia el icono del botón "Siguiente" o "Terminar" al pasar el mouse por encima.
     * Gestiona la animación si es la última pregunta.
     * @param evt El evento de mouse.
     */
    private void SiguientePBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SiguientePBMouseEntered
        if (indiceActual == preguntas.size() - 1){// Si es la última pregunta.
            if (term == true) {// Si la animación de "Terminar" está activa.
                SiguientePB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonTerminarTarget.png")));// Icono de "Terminar" al pasar el mouse.
            }
        }
        else {
            SiguientePB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonSiguienteTarget.png")));// Icono de "Siguiente" al pasar el mouse.
        }
        
        
    }//GEN-LAST:event_SiguientePBMouseEntered
    
    /**
     * Restaura el icono del botón "Siguiente" o "Terminar" al salir el mouse.
     * Gestiona la animación si es la última pregunta.
     * @param evt El evento de mouse.
     */
    private void SiguientePBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SiguientePBMouseExited
        if (indiceActual == preguntas.size() - 1) {// Si es la última pregunta.
            if (term == true){// Si la animación de "Terminar" está activa.
                SiguientePB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonTerminar5.png"))); // Icono de "Terminar" (último frame de animación).
            }
        }
        else {
            SiguientePB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonSiguiente.png")));// Icono de "Siguiente" normal.
        }
        
    }//GEN-LAST:event_SiguientePBMouseExited


    // Declaración de variables miembro de la clase.
    private Timer timer;// Objeto Timer para las animaciones.
    private boolean term;// Indica si el botón Terminar está visible.
    // Arrays de rutas de imágenes para las animaciones del botón "Terminar".
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
    private Point loc; // Ubicación de la ventana.
    private int indiceActual = 0; // Índice de la pregunta mostrada actualmente.
    private boolean modoRevision; // True si está en modo revisión, false si es prueba.
    private List<Pregunta> preguntas; // Lista de todas las preguntas.
    private JRadioButton[] botonesOpciones; // Array de JRadioButtons para las opciones.
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
