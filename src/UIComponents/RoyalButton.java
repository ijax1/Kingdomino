package UIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalButtonUI;

import resources.OurColors;
import resources.Resources;

public class RoyalButton extends JButton {
	public RoyalButton(String text) {
		super(text);
		SelectButtonUI ui = new SelectButtonUI();

		setBackground(OurColors.BUTTON_COLOR);
		setForeground(OurColors.FONT_LIGHT);
        ui.setSelectColor(getBackground().darker());
        setUI(ui);
		setBorder(new LineBorder(OurColors.ACCENT_COLOR, 2));
		setFont(Resources.getMedievalFont(20));
		//get rid of that ugly border around the text
		setFocusPainted(false);
	}
	
//	@Override
//	public Dimension getPreferredSize() {
//		return new Dimension(400,100);
//		
//	}
//	@Override
//	public Dimension getMinimumSize() {
//		return new Dimension(150,100);
//		
//	}
//	@Override
//	public Dimension getMaximumSize() {
//		return new Dimension(1600,400);
//		
//	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}			
	public static class SelectButtonUI extends MetalButtonUI {
        public void setSelectColor(Color selectColor) {
            this.selectColor = selectColor;
        }
        @Override
        public Color getSelectColor() {
            return selectColor;
        }
    }
}
