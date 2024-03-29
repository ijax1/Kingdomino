package Backend;
import java.awt.Color;
import java.util.ArrayList;
import resources.Titles;
public class SkilledStrategy extends ComputerPlayer {

    private GridPosition bestPos;
    private int bestRot = 0;
    private Domino bestDomino;
    private String title = Titles.generateTitle();
    private String name = Titles.generateName();

    public SkilledStrategy(Color color, GameManager game) {
        super(color, game);
    }

    public String getStrategyName() {
        return "Skilled Strategy";
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Note: don't call if there are no choices
     */
    @Override
    public void calculateChoice(Domino[] d, ArrayList<Player> p) {
        int maxScore = 0;
        bestRot = 0;
        bestPos = null;
//        Domino bestDomino = dominoes[0];
        bestDomino = d[0];
//        System.out.println(getName());
        System.out.println(getGrid());
        //System.out.println(getGrid().availableSpaces());
        for (Domino dm : d) {
            Domino domino = dm.copy();
            for (int i = 0; i < 4; i++) {
                domino.setRotation(i*90);
                ArrayList<GridPosition> positions = getGrid().availableSpaces(domino);
                //System.out.println("Pos: " + positions);
                if (!positions.isEmpty()) {
                    if (bestPos == null)
                        bestPos = positions.get(0);
                    for (GridPosition pos : positions) {
                        Grid potentialGrid = getGrid().copy();
                        potentialGrid.placeDomino(pos.getX(), pos.getY(), domino);
                        int currScore = potentialGrid.calculateScore();
                        if (currScore > maxScore) {
                            bestPos = pos;
                            bestRot = domino.getRotation();
                            bestDomino = domino;
                            maxScore = currScore;
                        }
                    }
                }
            }
        }
        setSelected(true);
        setNextDomino(bestDomino);
    }

//    @Override
//    public void placeDomino(Domino[] d, ArrayList<Player> p) {
//        if (bestPos != null && getCurrentDomino() != null) {
//            Domino dom = getCurrentDomino();
//            dom.setRotation(bestRot);
////            getGrid().placeDomino(bestPos.getX(), bestPos.getY(), dom);
//            getGrid().placeDomino(bestPos.getY(), bestPos.getX(), dom);
//        }
//        setPlaced(true);
//    }

    public void placeDomino(Domino[] d, ArrayList<Player> p) {
        if (bestPos != null) {
            bestDomino.setRotation(bestRot);
//            System.out.println(getGrid().availableSpaces(bestDomino));
//            System.out.println(bestPos);
//            System.out.println("Included: "+ getGrid().availableSpaces(bestDomino).contains(bestPos));
            boolean placeable = getGrid().placeDomino(bestPos.getX(), bestPos.getY(), bestDomino);
//            System.out.println();
        }
        setPlaced(true);
    }
}