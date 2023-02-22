package resources;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Resources {
	//Cannot be instantiated
	private Resources(){
	}
	public static BufferedImage loadImage(String name) {
		BufferedImage image;
		try {
			image = ImageIO.read(Resources.class.getResource(name));
		} catch (IOException e) {
			image = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
		}
		return image;
	}
	public static Font getMedievalFont(float size) {
		return loadFont("fonts/MedievalSharp-Regular.ttf").deriveFont(size);
	}
	public static Font loadFont(String name) {
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, Resources.class.getResourceAsStream(name));
		} catch (IOException | FontFormatException e) {
			//A default serif font
			System.out.println("serif");
			font = Font.getFont(Font.SERIF);
			if(font==null) {
			//The default Swing font
				System.out.println("default");
				font = new JLabel().getFont();
			}
		}
		return font;
	}
}
