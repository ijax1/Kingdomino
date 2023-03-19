package Backend;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import Backend.GameManager.GameState;
import UIComponents.AnalysisPanel;
import UIComponents.GamePanel;
import UIComponents.PodiumPanel;
import UIComponents.StartPanel;
import UIComponents.UIGrid;
import resources.Resources;


public class Kingdomino {

    final private JPanel basePanel;
    final private StartPanel startPanel;
    private GamePanel gamePanel;
    final private PodiumPanel podiumPanel;
    final private AnalysisPanel analysisPanel;
    final private GameManager manager;
    final private CardLayout panels = new CardLayout();
    private JFrame frame;

    public static final int FRAME_WIDTH = 1280;
    public static final int FRAME_HEIGHT = 720;

    public Kingdomino() {
        manager = new GameManager();
        basePanel = new JPanel(panels);

        startPanel = new StartPanel(new GridBagLayout(), this);
        podiumPanel = new PodiumPanel(new GridBagLayout(), this);
        analysisPanel = new AnalysisPanel(new GridBagLayout(), this);

        basePanel.add(startPanel, "Start Panel");
//        basePanel.add(gamePanel, "Game Panel");
        basePanel.add(podiumPanel, "Podium Panel");
        basePanel.add(analysisPanel, "Analysis Panel");
        setUpFrame();
    }

    // GamePanel needs to be initialized after startPanel initializes players
    private void initializeGamePanel() {
        gamePanel = new GamePanel(this);
        basePanel.add(gamePanel, "Game Panel");
    }

    private void setUpFrame() {
        frame = new JFrame("Kingdomino");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setContentPane(basePanel);
//        frame.setPreferredSize(screenSize);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setIconImage(Resources.loadImage("crown.ico"));
        frame.pack();
        //frame.setResizable(true);
        frame.setVisible(true);

        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); //$NON-NLS-1$
        frame.getRootPane().getActionMap().put("Cancel", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                //framename.setVisible(false);
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }

    public GameManager getManager() {
        return manager;
    }

    public void setGameAndPanelState(GameState state) {
    	manager.setGameState(state);
        if (state == GameState.INITIAL) {
            panels.show(basePanel, "Start Panel");
        } else if (state == GameState.PLAYER_TURN ||
                state == GameState.TALLY_SCORE) {
            if (gamePanel == null) {
                initializeGamePanel();
            }
            if (manager.isFirstRound()) {
                getGamePanel().initDominoes();
            }
            panels.show(basePanel, "Game Panel");
        } else if (state == GameState.ENDSCREEN) {
            panels.show(basePanel, "Podium Panel");
        } else if (state == GameState.STRATEGY) {
            panels.show(basePanel, "Analysis Panel");
//            analysisPanel.beginAnalysis(manager.getNumGames());
        }

    }
    public void nextPlayer() {
        getGamePanel().finishTurn();
        if (manager.getCurrentPlayer() instanceof HumanPlayer && manager.getCurrentPlayer().getCurrentDomino() != null) {
            UIGrid uiGrid = getGamePanel().getUIGrid();
            manager.getCurrentPlayer().getGrid().placeDomino(uiGrid.getDominoLocation()[1], uiGrid.getDominoLocation()[0], manager.getCurrentPlayer().getCurrentDomino());
        }
        manager.nextPlayer();
        getGamePanel().changePlayer(manager.getCurrentPlayer());
        
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Kingdomino();
            }
        });
    }

    public AnalysisPanel getAnalysisPanel() {
        return analysisPanel;
    }
}