package Backend;

import UIComponents.*;

import javax.swing.*;
import java.awt.*;


public class Kingdomino {
    private JPanel currentPanel;
    final private StartPanel startPanel;
    final private GamePanel gamePanel;
    final private PodiumPanel podiumPanel;
    final private GameManager manager;
    private JFrame frame;

    Kingdomino() {
        manager = new GameManager();
        GridBagLayout gb = new GridBagLayout();
        startPanel = new StartPanel(gb);
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
        //frame.setResizable(false);
        frame.setVisible(true);
    }
    /*
    public void changePanel(GameState state) {

        JPanel newPanel;
        if (state == GameState.INITIAL)
            newPanel = startPanel;
        else if (state == GameState.PLAYER_TURN)
            newPanel = gamePanel;
        else if (state == ENDSCREEN)
            newPanel = podiumPanel;
        else
            newPanel = currentPanel;
        frame.setContentPane(newPanel);
    }

     */

    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Kingdomino();
			}
    	});
    }
}