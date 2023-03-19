package UIComponents;

import java.awt.Color;
import java.awt.Graphics2D;

import Backend.GameManager;
import Backend.Kingdomino;
import UIComponents.Render.Coordinate;

public class MessageTextBox extends Component {
    //RandomAccessFile file = new RandomAccessFile("filename", "r");
    //int currentLine;
    private final Coordinate minimizedPosition = new Coordinate(100, 670, 0);
    private GameManager gm;
    MessageTextBox(Coordinate c, Kingdomino k) {
        super(c, k);
        this.gm = k.getManager();

        //currentLine = (int) (Math.random()*file.length();
    }

    @Override
    public void setPosition(Coordinate coordinate) {
    }

    @Override
    public boolean onComponent(Coordinate c) {
        return false;}

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
        g.setColor(new Color(140, 67, 188));
        //filler dimensions rn
        if (!isShown()) {
            g.setColor(Color.white);
            g.drawString("MESSAGE", (int) minimizedPosition.getX(), (int) minimizedPosition.getY());
            //newLine();
        } else {
            g.fillRoundRect((int) getPosition().getX(), (int) getPosition().getY(), 300, 220, 50, 50);
            g.setColor(Color.white);
            if (!gm.getCurrentPlayer().hasLegalMoves(true)) {
                g.drawString("No Legal Moves!", (int) minimizedPosition.getX(), (int) minimizedPosition.getY());
            }
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

    }
}
