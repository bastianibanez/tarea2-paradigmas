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
 * Pantalla de Resultados (`ResultadoPantalla`):
 * Esta clase representa la ventana de la interfaz gráfica de usuario (GUI)
 * que se muestra al finalizar una evaluación. Su propósito principal es:
 * 1. Presentar un resumen detallado del desempeño del estudiante.
 * 2. Calcular y exhibir el número y porcentaje de respuestas correctas e incorrectas,
 * desglosado por cada `TipoPregunta` (Opción Múltiple y Verdadero/Falso).
 * 3. Ofrecer la opción de revisar la prueba nuevamente.
 *
 * Hereda de `javax.swing.JFrame` para crear una ventana principal.
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
            // Obtiene la lista de preguntas para el tipo actual. Si no hay preguntas de ese tipo,
            // retorna una lista vacía para evitar errores de NullPointerException.
            List<Pregunta> list = grouped.getOrDefault(type, List.of());
            // Calcula el número de respuestas correctas para el tipo de pregunta actual.
            long correctCount = list.stream().filter(Pregunta::isCorrect).count();
            // Calcula el número total de preguntas de este tipo.
            long total = list.size();
            // Calcula el número de respuestas incorrectas.
            long incorrectCount = total - correctCount;

            // Calcula los porcentajes de respuestas correctas e incorrectas.
            // Se maneja el caso de 'total == 0' para evitar división por cero.
            double correctPercent = total == 0 ? 0 : (correctCount * 100.0 / total);
            double incorrectPercent = total == 0 ? 0 : (incorrectCount * 100.0 / total);

            // Añade la línea de resumen formateada para el tipo de pregunta actual al `StringBuilder`.
            // Se usa String.format para una salida limpia y alineada.
            sb.append(String.format("%-15s: Correctas: %2d / %2d (%.1f%%) | Incorrectas: %2d (%.1f%%)\n",
                type == TipoPregunta.MULTIPLE ? "Opción múltiple" : "Verdadero/Falso", // Texto descriptivo para el tipo.
                correctCount, total, correctPercent,
                incorrectCount, incorrectPercent));
}

        // --- Cálculos de estadísticas generales de la prueba ---
        sb.append("\nTotal preguntas: ").append(preguntas.size()); // Añade el número total de preguntas en la evaluación.
        long totalCorrect = preguntas.stream().filter(Pregunta::isCorrect).count(); // Calcula el total de respuestas correctas en toda la prueba.
        long totalIncorrect = preguntas.size() - totalCorrect; // Calcula el total de respuestas incorrectas.
        sb.append("\nTotal correctas: ").append(totalCorrect);
        sb.append("\nTotal incorrectas: ").append(totalIncorrect);
        
        // Calcula y añade el porcentaje total de respuestas correctas.
        sb.append("\nPorcentaje total: ")
            .append(String.format("%.1f%%", (totalCorrect * 100.0 / preguntas.size())));

        // Asigna el texto generado al componente JTextArea `ResultadoTexto` para su visualización.
        ResultadoTexto.setText(sb.toString());
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

    /**
     * Manejador de evento al hacer clic en el botón "Cerrar".
     * Termina la ejecución de la aplicación.
     */
    private void CerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseClicked
        System.exit(0);// Sale de la aplicación.
    }//GEN-LAST:event_CerrarMouseClicked

     /**
     * Manejador de evento al entrar el ratón en el botón "Cerrar".
     * Cambia el ícono para indicar que el botón es interactivo.
     */
    private void CerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseEntered
        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/cerrarTarget.png")));
    }//GEN-LAST:event_CerrarMouseEntered

     /**
     * Manejador de evento al salir el ratón del botón "Cerrar".
     * Restaura el ícono original del botón.
     */
    private void CerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CerrarMouseExited
        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/cerrar.png")));
    }//GEN-LAST:event_CerrarMouseExited

    /**
     * Manejador de evento al hacer clic en el botón "Minimizar".
     * Minimiza la ventana a la barra de tareas.
     */
    private void minimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarMouseClicked
        setState(Frame.ICONIFIED);
    }//GEN-LAST:event_minimizarMouseClicked

     /**
     * Manejador de evento al entrar el ratón en el botón "Minimizar".
     * Cambia el ícono para indicar que el botón es interactivo.
     */
    private void minimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarMouseEntered
        minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/minTarget.png")));
    }//GEN-LAST:event_minimizarMouseEntered

    /**
     * Manejador de evento al salir el ratón del botón "Minimizar".
     * Restaura el ícono original del botón.
     */
    private void minimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizarMouseExited
        minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/min.png")));
    }//GEN-LAST:event_minimizarMouseExited

    /**
     * Manejador de evento para arrastrar la ventana.
     * Permite mover la ventana por la pantalla al arrastrar el panel 'Head'.
     *
     * @param evt El evento del ratón que indica el arrastre.
     */
    private void HeadMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeadMouseD\ragged
        int x = evt.getXOnScreen();// Coordenada X actual del puntero en la pantalla.
        int y = evt.getYOnScreen();// Coordenada Y actual del puntero en la pantalla.
        this.setLocation(x - xMouse, y - yMouse);// Establece la nueva posición de la ventana.
        loc = this.getLocation();// Actualiza la ubicación guardada de la ventana.
    }//GEN-LAST:event_HeadMouseDragged

    /**
     * Manejador de evento para presionar el ratón.
     * Registra las coordenadas iniciales del clic para calcular el desplazamiento al arrastrar.
     *
     * @param evt El evento del ratón que indica la presión.
     */
    private void HeadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeadMousePressed
        xMouse = evt.getX();// Guarda la posición X del ratón dentro del componente.
        yMouse = evt.getY();// Guarda la posición Y del ratón dentro del componente.
    }//GEN-LAST:event_HeadMousePressed

     /**
     * Manejador de evento al hacer clic en el botón "Revisar Prueba".
     * Abre una nueva `PruebaPantalla` en modo revisión y cierra la ventana actual.
     *
     * @param evt El evento del ratón que indica el clic.
     */
    private void BotonPruebaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonPruebaMouseClicked
        // Crea una nueva instancia de 'PruebaPantalla', pasando las preguntas originales,
        // indicando que es modo revisión (true), y la ubicación actual de la ventana.
        new PruebaPantalla(preguntas, true, loc).setVisible(true);
        this.dispose(); // Cierra la ventana actual de resultados.       
    }//GEN-LAST:event_BotonPruebaMouseClicked

    /**
     * Manejador de evento al entrar el ratón en el botón "Revisar Prueba".
     * Cambia el ícono para indicar que el botón es interactivo.
     */
    private void BotonPruebaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonPruebaMouseEntered
        BotonPrueba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagCom/BotonReviPruebTarget.png")));
    }//GEN-LAST:event_BotonPruebaMouseEntered

    /**
     * Manejador de evento al salir el ratón del botón "Revisar Prueba".
     * Restaura el ícono original del botón.
     */
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
