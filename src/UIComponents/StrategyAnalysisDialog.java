package UIComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Backend.GameManager;
import Backend.Kingdomino;
import resources.OurColors;
import resources.Resources;

@SuppressWarnings("serial")
public class StrategyAnalysisDialog extends JDialog {
	private JPanel tintPane;
	private Color tintColor = new Color(0,0,0,125);
    GameManager manager;
	public StrategyAnalysisDialog(JFrame root, Kingdomino dom) {
		//jdialog with no name
		super(root, "", ModalityType.DOCUMENT_MODAL);
		this.manager = dom.getManager();		
		
		//tintPane darkens the entire background when the dialog is open
        tintPane = new JPanel() {
            //for some reason you need to do this instead of just setting the background, idk why
            @Override
            public void paintComponent(Graphics g) {
                g.setColor(tintColor);
                g.fillRect(0, 0, getWidth(), getHeight());
                //this is after to avoid some flickering
                super.paintComponent(g);
            }
        };

        //input pannel for funer of games users want to run
        NumberFormat intFormat = NumberFormat.getIntegerInstance();
        final JFormattedTextField textfield = new JFormattedTextField(intFormat);
  
        //just a panel to hold titlePanel and bodyPanel
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new GridLayout(2,1));
        
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(OurColors.ERROR_HEADER);
        //the font color is set in foreground
        titlePanel.setForeground(OurColors.FONT_LIGHT);
        
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.setBackground(OurColors.ERROR_BODY);

        
        
		JLabel titleLabel = new JLabel("NO HUMAN PLAYERS SELECTED!");
		titleLabel.setFont(Resources.getMedievalFont(30));
        //the font color is set in foreground
		titleLabel.setForeground(OurColors.FONT_LIGHT);
		
		JLabel bodyLabel = new JLabel("Chooseth thy analysis speed, your eminence.");
		bodyLabel.setFont(Resources.getMedievalFont(18));
		bodyLabel.setForeground(OurColors.FONT_DARK);
		    
        JButton button = new JButton("RETURNETH");
        button.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                System.out.println("returnteth clicked");
        		setVisible(false);
                //no mode, return
        		tintPane.setVisible(false);
        		dispose();
        	}
        });

        JButton button2 = new JButton("SLOW AS A TORTOISE");
        button.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		tintPane.setVisible(false);
                //make sure user enter an int
                int input = Integer.parseInt(textfield.getText());
                //**call game manager with the number of games*
                manager.setNumGames(input);
                //slow mode
                manager.setMode(false);
        		dispose();
                manager.setGameState(GameManager.GameState.PLAYER_TURN);
        	}
        });

        JButton button3 = new JButton("QUICK AS A HARE");
        button.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		tintPane.setVisible(false);
                //make sure user enter an int
                //TODO: check if how this returns empty string
                int input = Integer.parseInt(textfield.getText());
                //**call game manager with the number of games*
                manager.setNumGames(input);

                //fast mode
                manager.setMode(true);
        		dispose();
                manager.setGameState(GameManager.GameState.PLAYER_TURN);
        	}
        });

        titlePanel.add(titleLabel);
        
        bodyPanel.add(bodyLabel, BorderLayout.PAGE_START);
        bodyPanel.add(textfield, BorderLayout.CENTER);
        bodyPanel.add(button, BorderLayout.LINE_START);
        bodyPanel.add(button2, BorderLayout.CENTER);
        bodyPanel.add(button3, BorderLayout.LINE_END);

        dialogPanel.add(titlePanel);
        dialogPanel.add(bodyPanel);
        
        getContentPane().add(dialogPanel);
        setUndecorated(true);
        //Pack before centering
        pack();
        //Center dialog
        setLocationRelativeTo(root);
        
        //Set glass pane visible
        tintPane.setOpaque(false);
        root.setGlassPane(tintPane);
        tintPane.setVisible(true);
        setVisible(true);
	}
}
