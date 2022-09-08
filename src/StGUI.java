import javax.swing.*;
import java.awt.*;

public class StGUI extends JFrame {

    StGUI(int x, int y) {
        JFrame stFrame = new JFrame("TowerOfHanoi");
        stFrame.setBounds(x, y, 500, 300);
        setDefaultLookAndFeelDecorated(true);
        stFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        stFrame.setLayout(null);
        stFrame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 465, 240);
        panel.setLayout(null);
        panel.setVisible(true);
        stFrame.add(panel);

        JLabel label = new JLabel("Введите количество колец ");
        label.setBounds(83, 10, 300, 25);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBackground(Color.gray);
        label.setVisible(true);
        panel.add(label);

        JLabel label1 = new JLabel();
        label1.setBounds(83, 30, 300, 25);
        label.setHorizontalAlignment(JLabel.CENTER);
        label1.setBackground(Color.gray);
        label1.setVisible(true);
        panel.add(label1);

        JTextField textField = new JTextField();
        textField.setBounds(217, 60, 30, 25);
        textField.setBackground(Color.WHITE);
        textField.setVisible(true);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.requestFocusInWindow();
        panel.add(textField);

        JButton button = new JButton("Начинаем");
        button.setBounds(182, 100, 100, 25);
        button.setVisible(true);
        panel.add(button);
        stFrame.repaint();
        button.addActionListener(e -> {
            String s = textField.getText();
            if (s.isEmpty()) {
                label1.setText("Введите число");
            } else {
               int countRings = Integer.parseInt(s);
                stFrame.setVisible(false);

              new MainGUI(10,10,countRings);
            }
        });
    }
}

