//Ariel Sanchez
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class historial {
    public JPanel PHistorial;
    public JTextArea textArea1;
    public JButton mostrarHistorialButton;
    public JButton volverAlRegistroButton;

    public historial() {
        mostrarHistorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/BaseDos";
                String user = "root";
                String password = "1234";

                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    String query = "SELECT * FROM Students";
                    try (PreparedStatement BDD = conn.prepareStatement(query);
                         ResultSet resultado = BDD.executeQuery()) {

                        StringBuilder resultados = new StringBuilder();
                        while (resultado.next()) {
                            resultados.append("Cedula: ").append(resultado.getInt("cedula"))
                                    .append("   , Calificacion 1: ").append(resultado.getString("materia1"))
                                    .append("   , Calificacion 2: ").append(resultado.getString("materia2"))
                                    .append("   , Calificacion 3: ").append(resultado.getString("materia3"))
                                    .append("   , Calificacion 4: ").append(resultado.getString("materia4"))
                                    .append("   , Calificacion 5: ").append(resultado.getString("materia5"))
                                    .append("   , Promedio: ").append(resultado.getString("promedio")).append("\n");

                        }
                        textArea1.setText(resultados.toString());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al mostrar el historial");
                }
            }
        });
        volverAlRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame cargarDFrame = (JFrame) SwingUtilities.getWindowAncestor(PHistorial);
                if (cargarDFrame != null) {
                    cargarDFrame.dispose();
                }

                JFrame frame = new JFrame("Gestion de Calificaciones");
                frame.setContentPane(new gestion().PGestion);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 700);
                frame.setPreferredSize(new Dimension(500, 700));
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
