//Ariel Sanchez
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame menuFrame = new JFrame("Login");
        menuFrame.setContentPane(new login().PLogin);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(500, 300);
        menuFrame.setPreferredSize(new Dimension(500, 300));
        menuFrame.setLocationRelativeTo(null);
        menuFrame.pack();
        menuFrame.setVisible(true);
    }
}