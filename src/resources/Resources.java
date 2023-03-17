package resources;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Resources {
	private static Font medieval = loadFont("fonts/MedievalSharp-Regular.ttf",1);
	private static HashMap<String, BufferedImage>map = new HashMap<>();
	static {
		String path = "tile images/";
		String[] fileNames = {"castle0", "forest0", "forest1", "lake0", "lake1", "mine0", "mine1", "mine2", "mine3",
				"pasture0", "pasture1", "pasture2", "swamp0", "swamp1", "swamp2", "wheat0", "wheat1"
		};
		for(String name: fileNames) {
			map.put(name, Resources.loadImage(path+name+".png"));
		}
		System.out.println(map.size());
	}
	//Cannot be instantiated
	private Resources(){
	}
	public static BufferedImage loadTile(String name) {
		BufferedImage image = map.get(name);
		if(image != null) {	
		} else {
			image = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
			image.getGraphics().fillRect(0,0,500,500);
			image.getGraphics().drawString(name+"not found", 0, 0);
		}
		return image;
	}
	public static BufferedImage loadImage(String name) {
		BufferedImage image;
		URL imageURL = Resources.class.getResource(name);
		if(imageURL != null) {
			try {
				image = ImageIO.read(imageURL);
			} catch (IOException e) {
				image = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
				image.getGraphics().fillRect(0,0,500,500);
				image.getGraphics().drawString(name+"not loaded by ImageIO", 0, 0);
			}
		} else {
			image = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
			image.getGraphics().fillRect(0,0,500,500);
			image.getGraphics().drawString(name+"not found", 0, 0);
		}
		return image;
	}
	public static Font getMedievalFont(float fontSize) {
		return medieval.deriveFont(fontSize);
	}
	public static Font getLogoFont(float fontSize) {
		//return loadFont("fonts/MedievalSharp-Regular.ttf").deriveFont(size);
		return loadFont("fonts/DS Germania.ttf", fontSize);
	}
	public static Font getLogoFontContour(float fontSize) {
		return loadFont("fonts/DS Germania Contour.ttf", fontSize);
	}
	public static Font getLogoFontShadow(float fontSize) {
		return loadFont("fonts/DS Germania Shadow.ttf", fontSize);
	}
	
	public static Font loadFont(String name, float fontSize) {
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, Resources.class.getResourceAsStream(name));
		} catch (IOException | FontFormatException e) {
			//A default serif font
			System.out.println("Warning-could not find font " + name +". Loading serif font...");
			font = Font.getFont(Font.SERIF);
			if(font==null) {
			//The default Swing font
				System.out.println("Warning-could not find serif font. Loading default font...");
				font = new JLabel().getFont();
			}
		}
		return font.deriveFont(fontSize);
	}
	public static void tint(BufferedImage img, Color color) {
		Graphics2D g = img.createGraphics();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		g.setColor(color);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
	}
}
