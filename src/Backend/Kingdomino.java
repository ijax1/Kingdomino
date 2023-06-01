package Backend;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

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


public class Kingdomino implements GameEventListener {

    final private JPanel basePanel;
    final private StartPanel startPanel;
    private GamePanel gamePanel;
    final private PodiumPanel podiumPanel;
    private AnalysisPanel analysisPanel;
    final private GameManager manager;
    final private CardLayout panels = new CardLayout();
    private JFrame frame;

    public static final int FRAME_WIDTH = 1280;
    public static final int FRAME_HEIGHT = 720;

    public Kingdomino() {
        manager = new GameManager();
        basePanel = new JPanel(panels);

        manager.addListener(this);

        startPanel = new StartPanel(new GridBagLayout(), this);
        podiumPanel = new PodiumPanel(new GridBagLayout(), this);
        basePanel.add(startPanel, "Start Panel");
//        basePanel.add(gamePanel, "Game Panel");
        basePanel.add(podiumPanel, "Podium Panel");
        setUpFrame();
    }

    // GamePanel needs to be initialized after startPanel initializes players
    private void initializeGamePanel() {
        gamePanel = new GamePanel(this);
        if (!manager.isFastMode()) {
            manager.addListener(gamePanel);
        }
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
        frame.setResizable(true);
        frame.setVisible(true);

        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); //$NON-NLS-1$
        frame.getRootPane().getActionMap().put("Cancel", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                //framename.setVisible(false);
            }
        });
        frame.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowGainedFocus(WindowEvent e) {
                if (gamePanel != null)
                    gamePanel.repaint();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                if (gamePanel != null)
                    gamePanel.lostFocus();
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }

    public GameManager getManager() {
        return manager;
    }

    @Override
    public void onStateChangedTo(GameState state) {
        if (state == GameState.INITIAL) {
            panels.show(basePanel, "Start Panel");
        } else if (state == GameState.PLAYER_TURN ||
                state == GameState.TALLY_SCORE) {
            if (gamePanel == null) {
                initializeGamePanel();
            }
//            if (manager.isFirstRound()) {
//                getGamePanel().initDominoes();
//            }
//            getGamePanel().setComponents();

            panels.show(basePanel, "Game Panel");
        } else if (state == GameState.ENDSCREEN) {
            panels.show(basePanel, "Podium Panel");
        } else if (state == GameState.ANALYSIS_PANEL) {
            analysisPanel = new AnalysisPanel(new GridBagLayout(), this);
            basePanel.removeAll();
            basePanel.add(analysisPanel, "Analysis Panel");
            panels.show(basePanel, "Analysis Panel");
//            analysisPanel.beginAnalysis(manager.getNumGames());
        }
    }

    @Override
    public void initDominoes() {
//        if (manager.isFirstRound()) {
//            getGamePanel().initDominoes();
//        }
    }

    public void finishTurn() {
        getGamePanel().finishTurn();
        if (manager.getCurrentPlayer() instanceof HumanPlayer && manager.getCurrentPlayer().getCurrentDomino() != null) {
            UIGrid uiGrid = getGamePanel().getUIGrid();
            if (manager.getCurrentPlayer().hasLegalMoves(true))
                manager.getCurrentPlayer().getGrid().placeDomino(uiGrid.getDominoLocation()[1], uiGrid.getDominoLocation()[0], manager.getCurrentPlayer().getCurrentDomino());
        }
    }

    public void nextPlayer() {
//        getGamePanel().finishTurn();
//        if (manager.getCurrentPlayer() instanceof HumanPlayer && manager.getCurrentPlayer().getCurrentDomino() != null) {
//            UIGrid uiGrid = getGamePanel().getUIGrid();
//            manager.getCurrentPlayer().getGrid().placeDomino(uiGrid.getDominoLocation()[1], uiGrid.getDominoLocation()[0], manager.getCurrentPlayer().getCurrentDomino());
//        }
//        manager.nextPlayer();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public StartPanel getStartPanel() {
        return startPanel;
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

    @Override
    public void onDominoSelected(Domino d, boolean recallNextPlayer) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onNextPlayer() {
        // TODO Auto-generated method stub
        nextPlayer();

    }

    @Override
    public void onFinishTurn() {
        if (!manager.isFastMode())
            finishTurn();
    }
}