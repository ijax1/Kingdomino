package Backend;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Backend.GameManager.GameState;
import UIComponents.GamePanel;
import UIComponents.PodiumPanel;
import UIComponents.StartPanel;
import resources.Resources;


public class Kingdomino {
    private JPanel currentPanel;
    final private StartPanel startPanel;
    final private GamePanel gamePanel;
    final private PodiumPanel podiumPanel;
    final private GameManager manager;
    private JFrame frame;
    
    public Kingdomino() {
        manager = new GameManager(this);
        GridBagLayout gb = new GridBagLayout();
        startPanel = new StartPanel(gb, this);
        gamePanel = new GamePanel(new ArrayList<Player>(), this);
        podiumPanel = new PodiumPanel(this);
        setUpFrame();
    }

    private void setUpFrame() {
        frame = new JFrame("Kingdomino");
        currentPanel = startPanel;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setContentPane(currentPanel);
        frame.setPreferredSize(screenSize);
        frame.setIconImage(Resources.loadImage("crown.ico"));
        frame.pack();
        //frame.setResizable(false);
        frame.setVisible(true);
    }

    public GameManager getManager() {return manager;}

    public void changePanel(GameState state) {

        JPanel newPanel;
        if (state == GameState.INITIAL)
            newPanel = startPanel;
        else if (state == GameState.PLAYER_TURN)
            newPanel = gamePanel;
        else if (state == GameState.ENDSCREEN)
            newPanel = podiumPanel;
        else
            newPanel = currentPanel;
        frame.setContentPane(newPanel);
    }



    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Kingdomino();
			}
    	});
    }
}