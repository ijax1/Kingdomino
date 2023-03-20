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
    private Color tintColor = new Color(0, 0, 0, 125);
    private Kingdomino k;
    private GameManager manager;
    
    //final static String ERROR_TEXT = "THOU HAST YET TO INFORM US OF AN APPROPRIATE AMOUNT OF GAMES TO PLAY!";
    public StrategyAnalysisDialog(JFrame root, Kingdomino dom) {
        //jdialog with no name
        super(root, "", ModalityType.DOCUMENT_MODAL);
        this.manager = dom.getManager();

        this.k = dom;

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
        
        
        //JLabel inputError = new JLabel("");

        //input pannel for funer of games users want to run
        NumberFormat intFormat = NumberFormat.getIntegerInstance();
        final JFormattedTextField textfield = new JFormattedTextField(intFormat);
        
        //just a panel to hold titlePanel, bodyPanel, & bodyPanel2
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new GridLayout(3, 1));

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(OurColors.ERROR_HEADER);
        //the font color is set in foreground
        titlePanel.setForeground(OurColors.FONT_LIGHT);
        
        
        //FIRST PANEL SET UP:(buttons)
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.setBackground(OurColors.ERROR_BODY);
        
        //SECOND PANEL SET UP: (text input)
        JPanel bodyPanel2 = new JPanel();
        bodyPanel2.setLayout(new BorderLayout());
        bodyPanel2.setBackground(OurColors.ERROR_BODY);
        

                
        JLabel titleLabel = new JLabel("NO HUMAN PLAYERS SELECTED!");
        titleLabel.setFont(Resources.getMedievalFont(30));
        //the font color is set in foreground
        titleLabel.setForeground(OurColors.FONT_LIGHT);

        JLabel modeLabel = new JLabel("Chooseth thy analysis speed, your eminence.");
        modeLabel.setFont(Resources.getMedievalFont(18));
        modeLabel.setForeground(OurColors.FONT_DARK);  
        
        JLabel inputLabel = new JLabel("And how many games shall be played, my liege?");
        inputLabel.setFont(Resources.getMedievalFont(18));
        inputLabel.setForeground(OurColors.FONT_DARK);
        
        
        JButton button = new JButton("RETURNETH");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                //no mode, return
                tintPane.setVisible(false);
                dispose();
            }
        });

        JButton button2 = new JButton("SLOW AS A TORTOISE");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeSelected(textfield, false);
            }
        });

        JButton button3 = new JButton("QUICK AS A HARE");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeSelected(textfield, true);
            }
        });

        titlePanel.add(titleLabel);
        
        
        //PANEL 1:
        bodyPanel.add(modeLabel, BorderLayout.PAGE_START);
        bodyPanel.add(button, BorderLayout.LINE_START);
        bodyPanel.add(button2, BorderLayout.CENTER);
        bodyPanel.add(button3, BorderLayout.LINE_END);
        
        
        //PANEL 2:
        bodyPanel2.add(inputLabel, BorderLayout.PAGE_START); 
        bodyPanel2.add(textfield, BorderLayout.SOUTH);
		//bodyPanel.add(inputError, BorderLayout.SOUTH);

        /*
        if (!correctInput(textfield)) {
			inputError.setText(ERROR_TEXT);
        }else {
        	inputError.setText("");
        }
        */
        
        //Adding components together:
        //(NOTE: decided to switch where panels are added for the user's convenience)
        dialogPanel.add(titlePanel);
        dialogPanel.add(bodyPanel2);

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
    
    /*
    private boolean correctInput(JFormattedTextField textField) {
        boolean numbers = true;
    	double input = 1;
        try{
			input = (Double.parseDouble(textField.getText()));
		}catch (NumberFormatException ex) {
			numbers = false;
			System.out.println("Thou hast not entered an appropriate # of games." );
		}	
    	return numbers;
    }
    */
    
    private void modeSelected(JFormattedTextField textField, boolean isFast) {
        setVisible(false);
        tintPane.setVisible(false);
        
        //make sure user enter an int
        double input = 1;
        boolean games = true;
        try{
			input = (Double.parseDouble(textField.getText()));
		}catch (NumberFormatException ex) {
			games = false;
			System.out.println("Thou hast not entered an appropriate # of games." );
		}	
        
        //if num of games inputted in # format:
        if (games) {
        	//**call game manager with the number of games*
            manager.setNumGames((int)input);
            //slow mode
            manager.setMode(isFast);
            manager.setPlayers(k.getStartPanel().getAllPlayers());
            if (!isFast) {
                manager.setGameState(GameManager.GameState.PLAYER_TURN);
            }else
                manager.setGameState(GameManager.GameState.STRATEGY);
            
            dispose();
        }
  
       

    }
}
