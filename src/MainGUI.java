import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainGUI {

    private int from1;
    private int from;
    private int to;
    private final int countRings;
    private final int hBut = 26;
    private final int hPan;
    private final int wButt;
    private final int wPan;
    private final int wScene;
    private final JPanel panel = new JPanel();
    private final JLabel infoLabel;
    private JButton[] sceneButtons;
    private GameLogic game;

    MainGUI(int x, int y, int countRings) {
        this.countRings = countRings;
        hPan = countRings * hBut + 50;
        wButt = 2 * countRings * hBut;
        wScene=wButt+40;
        wPan = wButt + 50;
        int wFrame = 3 * wScene + 60;

        JFrame stFrame = new JFrame("TowerOfHanoi");

        stFrame.setBounds(x, y, wFrame, hBut * countRings + 250);
        setDefaultLookAndFeelDecorated(true);
        stFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        stFrame.setLayout(null);
        stFrame.setVisible(true);

        panel.setBounds(5, 5, 3 * wScene + 40, hBut * countRings + 200);
        panel.setBackground(Color.lightGray);
        panel.setLayout(null);
        panel.setVisible(true);
        stFrame.add(panel);

        infoLabel = new JLabel();
        infoLabel.setBounds(10, hBut * countRings + 150, 3 * wButt, hBut);
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        infoLabel.setVisible(true);
        panel.add(infoLabel);
        addOperaButton(10);
        drawRings(game.stacks);
    }

    public void addOperaButton(int x) {
        sceneButtons = new JButton[3];
        for (int i = 1; i <= 3; i++) {
            int k = i;
            sceneButtons[i - 1] = new JButton("Снять");
            sceneButtons[i - 1].setBounds(x + (i - 1) * wScene + (i - 1) * 5, 10, wScene, countRings * hBut + 100);
            sceneButtons[i - 1].setLayout(null);
            sceneButtons[i - 1].setHorizontalAlignment(JButton.CENTER);
            sceneButtons[i - 1].setVerticalAlignment(JButton.BOTTOM);
            sceneButtons[i - 1].setVisible(true);
            panel.add(sceneButtons[i - 1]);

            sceneButtons[i - 1].addActionListener(e -> {
                if (e.getActionCommand().equals("Снять")) {
                    infoLabel.setText("");
                    for (int i1 = 1; i1 <= 3; i1++) {
                        sceneButtons[i1 - 1].setText("Положить");
                    }
                } else if (e.getActionCommand().equals("Положить")) {
                    for (int i1 = 1; i1 <= 3; i1++) {
                        sceneButtons[i1 - 1].setText("Снять");
                    }
                }
                if (from1 == 0) {
                    from1 = k;
                } else if (from1 != 0) {
                    from = from1;
                    to = k;

                    Optional<String> moveResult = game.move(from, to);
                    moveResult.ifPresent(infoLabel::setText);
                    drawRings(game.stacks);
                    from1 = 0;
                    to = 0;
                    System.out.println(game.stacks);
                    if (game.ifWon()) {

                        infoLabel.setText(game.result());
                        for (int i1 = 0; i1 < 3; i1++) {
                            sceneButtons[i1].setVisible(false);
                        }
                    }
                }
            });
            game = new GameLogic(countRings);
        }
    }

    public void drawRings(List<Stack<Integer>> stacks) {
        for (int i = 0; i < 3; i++) {
            sceneButtons[i].removeAll();
            for (int j = stacks.get(i + 1).size(); j >= 1; j--) {
                int n = stacks.get(i + 1).elementAt(j - 1);
                JButton button = new JButton(" " + n);
                button.setBounds((wPan - 50 * n) / 2, hPan - j * hBut + 20, 50 * n, hBut);
                button.setBackground(Color.cyan);
                button.setVisible(true);
                sceneButtons[i].add(button);
            }
        }
    }
}
