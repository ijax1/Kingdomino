package UIComponents;

import java.awt.*;

import Backend.GameManager;
import Backend.Kingdomino;
import UIComponents.Render.Coordinate;
import resources.Quotes;
import resources.Resources;

public class MessageTextBox extends Component {
    //RandomAccessFile file = new RandomAccessFile("filename", "r");
    //int currentLine;
    private final Coordinate minimizedPosition = new Coordinate(130, 645, 0);
    private final Coordinate normalPosition = new Coordinate(130, 510, 0);
    private GameManager gm;
    private double width = 300;
    private double height = 220;
    private Graphics2D graphics;
    private String[] quote;

    MessageTextBox(Coordinate c, Kingdomino k) {
        super(c, k);
        this.gm = k.getManager();
        quote = Quotes.getQuote();

        //currentLine = (int) (Math.random()*file.length();
    }

    @Override
    public void setPosition(Coordinate coordinate) {
    }

    @Override
    public boolean onComponent(Coordinate c) {
        double x;
        double y;
        if (!isShown()) {
            x = minimizedPosition.getX();
            y = minimizedPosition.getY();
        } else {
            x = normalPosition.getX();
            y = normalPosition.getY();
        }
        return ((c.getX() > x && c.getX() < x+width) &&
                (c.getY() > y && c.getY() < y+height));
    }

    // private void newLine() {
    //     boolean newLine = false;
    //     while (!newLine) {}
    //         int line = (int) (Math.random()*file.length());
    //         if (currentLine != line) {
    //             currentLine = line;
    //             newLine = true;
    //         }
    // }

    @Override
    public void draw(Graphics2D g) {
        graphics = g;
        g.setColor(new Color(140, 67, 188));
        //filler dimensions rn
        if (!isShown()) {
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(3));
            g.drawRoundRect((int) minimizedPosition.getX(), (int) minimizedPosition.getY(), 300, 220, 50, 50);
            g.setColor(new Color(140, 67, 188));
            g.fillRoundRect((int) minimizedPosition.getX(), (int) minimizedPosition.getY(), 300, 220, 50, 50);
            g.setColor(Color.WHITE);
            g.drawString("MESSAGE", (int) minimizedPosition.getX() + 20, (int) minimizedPosition.getY() + 30);
            quote = Quotes.getQuote();
            //newLine();
        } else {
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(3));
            g.drawRoundRect((int) normalPosition.getX(), (int) normalPosition.getY(), 300, 220, 50, 50);
            g.setColor(new Color(140, 67, 188));
            g.fillRoundRect((int) normalPosition.getX(), (int) normalPosition.getY(), 300, 220, 50, 50);
            g.setColor(Color.WHITE);

            FontMetrics metrics = g.getFontMetrics(Resources.getMedievalFont(20));
            String[] quoteWords = quote[0].split(" ");
            String[] quoteAuthor = quote[1].split(" ");
            int x = (int) normalPosition.getX() + 20;
            int y = (int) normalPosition.getY() + 30;
            g.drawString("\"", x, y);
            x += metrics.stringWidth("\"");
            for (String quote : quoteWords) {
                if (x + metrics.stringWidth(quote) > normalPosition.getX() + 280) {
                    y += metrics.getHeight() + 5;
                    x = (int) normalPosition.getX() + 20;
                }
                g.drawString(quote, x, y);
                x += metrics.stringWidth(quote + " ");
            }
            g.drawString("\"", x, y);
            x = (int) normalPosition.getX() + 80;
            y += metrics.getHeight() + 5;
            g.drawString("-", x, y);
            x += metrics.stringWidth("- ");
            for (String author : quoteAuthor) {
                if (x + metrics.stringWidth(author) > normalPosition.getX() + 290) {
                    y += metrics.getHeight() + 5;
                    x = (int) normalPosition.getX() + 80 + metrics.stringWidth("- ");
                }
                g.drawString(author, x, y);
                x += metrics.stringWidth(author + " ");
            }
            /*
            if (!gm.isFirstRound() && !gm.getCurrentPlayer().hasLegalMoves(true)) {
                g.drawString("No Legal Moves!", (int) minimizedPosition.getX(), (int) minimizedPosition.getY());
            }
            */
            // draw string quotes based on seeking the current line from the text file with all the quotes
            // need to read the quote and find the bounds between when the quote starts / ends
            // if quote is too long, it should break into multiple lines, using a loop to draw each line
            // should break at the closest ' ' to the mid point of the length
            // quote length determined by pixel size (use topscroll as an example)
            // size of lines drawn also determine on quote length, maybe just easier to limit quotes
            // to a specific size 
            
            // file.seek(currentLine);
            // file.readLine();
            // String quote = file.readLine();
            
            //  ArrayList<Int> spaces = new ArrayList<Int>();
            //  ArrayList<String> quoteLines = new ArrayList<String>();
            // int lines = 1;

            // if (g.getFontMetrics().stringWidth(quote) > width of messageTextBox - 20) {
            //  for (int i=0; i<quote.length(); i++) {
            //      if (quote.charAt(i) == ' ') {
            //          spaces.add(i);
            //      }
            //  }
            //  lines = (g.getFontMetrics().stringWidth(quote) / pixel width of messageTextBox - 20) + 1;
            
            //  while (quoteLines.size() < lines) {
            //  }
            // } 
            
            // may just be easier to separate quotes and the person who said it 
            // personFile.seek(currentLine);
            // personFile.readLine();
            // String person = personFile.readLine();




        }
    }

    @Override
    public void whenClicked() {
        if (isShown()) {
            minimize();
        } else {
            show();
        }
        draw(graphics);
    }
}
