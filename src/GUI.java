import javax.swing.*;
import java.awt.event.*;
public class GUI implements ActionListener {
	private final static String NAME_TEXT = "Hello!";
	private JFrame frame;
	private JPanel contentPane;
	private JLabel label;
	private JButton button;
	public GUI(){
		//frame
		frame = new JFrame(this.getClass().getName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//content pane
		contentPane = new JPanel();

		//label
		label = new JLabel(NAME_TEXT);
		contentPane.add(label);

		//button
		button = new JButton("Hide");
		button.setActionCommand("Hide");
		button.addActionListener(this);
		contentPane.add(button);

		frame.setContentPane(contentPane);
		frame.pack();
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent event) {
		String eventName = event.getActionCommand();
		if (eventName.equals("Hide")) {
			label.setText(" ");
			button.setText("Show");
			button.setActionCommand("Show");
		} else {
			label.setText(NAME_TEXT);
			button.setText("Hide");
			button.setActionCommand("Hide");
		}
	}
	private static void runGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new GUI();
	}
	public static void main(String[] args) {
		//New thread for GUI
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				runGUI();
			}
		});
	}
}