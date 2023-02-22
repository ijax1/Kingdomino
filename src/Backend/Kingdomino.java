package Backend;

import UIComponents.*;

import javax.swing.*;
import java.awt.*;

public class Kingdomino {
    private JPanel currentPanel;
    private StartPanel startPanel;
    private PodiumPanel podiumPanel;
    private GamePanel gamePanel;
    private GameManager manager;
    private JFrame frame;

    void KingDomino() {
        manager = new GameManager();
        startPanel = new StartPanel();
        gamePanel = new GamePanel();
        podiumPanel = new PodiumPanel();
        setUpFrame();
    }

    private void setUpFrame() {
        frame = new JFrame("Kingdomino");
        currentPanel = startPanel;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setContentPane(currentPanel);
        frame.setPreferredSize(screenSize);
        frame.pack();
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        Kingdomino game = new Kingdomino();
    }
}
