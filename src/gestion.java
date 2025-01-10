//Ariel Sanchez
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class gestion {
    public JPanel PGestion;
    public JTextField textField1;
    public JTextField textField2;
    public JTextField textField3;
    public JTextField textField4;
    public JTextField textField5;
    public JTextField textField6;
    public JButton okButton;
    public JButton registrarCalificacionesButton;
    public JLabel LBLPromedio;
    public JLabel LBLAprobacion;
    public JButton mostrarHistorialDeCalificacionesButton;

    public gestion() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ((textField1.getText()).isEmpty() || (textField2.getText().isEmpty()) || (textField3.getText().isEmpty()) || (textField4.getText().isEmpty()) || (textField5.getText().isEmpty()) || (textField6.getText().isEmpty())){
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos");
                }

                double calf1 = Double.parseDouble(textField2.getText());
                double calf2 = Double.parseDouble(textField3.getText());
                double calf3 = Double.parseDouble(textField4.getText());
                double calf4 = Double.parseDouble(textField5.getText());
                double calf5 = Double.parseDouble(textField6.getText());

                if ((calf1 < 0 || calf1 > 20) || (calf2 < 0 || calf2 > 20) || (calf3 < 0 || calf3 > 20) || (calf4 < 0 || calf4 > 20) || (calf5 < 0 || calf5 > 20)) {
                    JOptionPane.showMessageDialog(null, "Las calificaciones deben ser entre 0 y 20");
                }else{
                    double promedio = (calf1 + calf2 + calf3 + calf4 + calf5) / 5;
                    if (promedio >= 14){
                        LBLAprobacion.setText("Aprobado");
                    }else{
                        LBLAprobacion.setText("Reprobado");
                    }
                    String promedioText = String.valueOf(promedio);
                    LBLPromedio.setText(promedioText);
                }
            }
        });

        registrarCalificacionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/BaseDos";
                String user = "root";
                String password = "1234";

                String cedula = textField1.getText();
                double  mat1 = Double.parseDouble(textField2.getText());
                double  mat2 = Double.parseDouble(textField3.getText());
                double  mat3 = Double.parseDouble(textField4.getText());
                double  mat4 = Double.parseDouble(textField5.getText());
                double  mat5 = Double.parseDouble(textField6.getText());
                double promedio = (mat1 + mat2 + mat3 + mat4 + mat5) / 5;


                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    String query = "INSERT INTO Students (cedula, materia1, materia2, materia3, materia4, materia5, promedio) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement BDD = conn.prepareStatement(query); {
                        BDD.setString(1, cedula);
                        BDD.setDouble(2, mat1);
                        BDD.setDouble(3, mat2);
                        BDD.setDouble(4, mat3);
                        BDD.setDouble(5, mat4);
                        BDD.setDouble(6, mat5);
                        BDD.setDouble(7, promedio);
                        BDD.execute();
                        JOptionPane.showMessageDialog(null, "Calificaciones guardadas correctamente");
                        textField1.setText("");
                        textField2.setText("");
                        textField3.setText("");
                        textField4.setText("");
                        textField5.setText("");
                        textField6.setText("");
                        LBLPromedio.setText("");
                        LBLAprobacion.setText("");
                    }
                } catch (SQLException ex) {
                    System.out.println("Error el MySQL");
                }
            }
        });

        mostrarHistorialDeCalificacionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame gestionFrame = (JFrame) SwingUtilities.getWindowAncestor(PGestion);
                if (gestionFrame != null) {
                    gestionFrame.dispose();
                }

                JFrame frame = new JFrame("Historial de calificaciones registradas");
                frame.setContentPane(new historial().PHistorial);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(900, 300);
                frame.setPreferredSize(new Dimension(900, 300));
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
