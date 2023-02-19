package UIComponents;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class UIGrid extends JComponent {
	private int size = 501;
	public UIGrid() {
		setPreferredSize(new Dimension(size,size));
	}
	
	@Override
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D)g1;
		for(int i=0; i<=size; i+=100) {
			g.drawLine(i, 0, i, size);
		}
		for(int i=0; i<=size; i+=100) {
			g.drawLine(0, i, size, i);
		}
	}
}
