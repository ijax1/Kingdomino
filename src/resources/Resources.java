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
		map.put("castle0", Resources.loadImage("castle0"));
		map.put("forest0", Resources.loadImage("forest0"));
		map.put("forest1", Resources.loadImage("forest1"));
		map.put("lake0", Resources.loadImage("lake0"));
		map.put("lake1", Resources.loadImage("lake1"));
		map.put("mine0", Resources.loadImage("mine0"));
		map.put("mine1", Resources.loadImage("mine1"));
		map.put("mine2", Resources.loadImage("mine2"));
		map.put("mine3", Resources.loadImage("mine3"));
		map.put("pasture0", Resources.loadImage("pasture0"));
		map.put("pasture1", Resources.loadImage("pasture1"));
		map.put("pasture2", Resources.loadImage("pasture2"));
		map.put("swamp0", Resources.loadImage("swamp0"));
		map.put("swamp1", Resources.loadImage("swamp1"));
		map.put("swamp2", Resources.loadImage("swamp2"));
		map.put("wheat0", Resources.loadImage("wheat0"));
		map.put("wheat1", Resources.loadImage("wheat1"));
		System.out.println(map.size());
	}
	//Cannot be instantiated
	private Resources(){
	}
	public static BufferedImage loadTile(String name) {
		BufferedImage image = map.get(name);
		System.out.println("here");
		if(image != null) {
			System.out.println(name);	
		} else {
			image = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
			image.getGraphics().drawString(name+"not found", 0, 0);
		}
		return image;
	}
	public static BufferedImage loadImage(String name) {
		BufferedImage image = map.get(name);
		System.out.println("here");
		if(image != null) {
			System.out.println(name);
			return image;
		}
		URL imageURL = Resources.class.getResource(name);
		if(imageURL != null) {
			try {
				image = ImageIO.read(imageURL);
			} catch (IOException e) {
				image = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
				image.getGraphics().drawString(name+"not loaded by ImageIO", 0, 0);
			}
		} else {
			image = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
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
