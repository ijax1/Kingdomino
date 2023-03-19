package Backend;

import java.awt.Color;
import java.util.ArrayList;

public class RandomStrategy extends ComputerPlayer {

    private GameManager gameManager;
    private Deck deck;
    private Grid grid = getGrid();


    public RandomStrategy(Color color, String name, String title, GameManager game) {
        super(color, name, title, game);
        this.gameManager = game;
        deck = game.getDeck();
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    private static int randomNum(int x) {
        int num = (int) (Math.random() * x);
        return num;
    }

    public void calculateChoice(Domino[] d, ArrayList<Player>p) {
        if (!gameManager.isFirstPlayer()) {
        	Domino[] dominoes = d;
            int idx = (int) (Math.random() * dominoes.length);
            Domino chosenDomino = dominoes[idx];
            setNextDomino(chosenDomino);
//            gameManager.getGame().getGamePanel().computerPlayerChose(chosenDomino);
        } else {
            Domino[] dominoes = d;
            int idx = (int) (Math.random() * dominoes.length);
            Domino chosenDomino = dominoes[idx];
            setNextDomino(chosenDomino);
//            gameManager.getGame().getGamePanel().computerPlayerChose(chosenDomino);
        }
//        Domino[] choices = deck.getDominoesToSelect();
//        int ranChoice = randomNum(4);
//        Domino chosenDomino = choices[ranChoice];
//        setNextDomino(chosenDomino);
    }


    public void placeDomino(Domino[] d, ArrayList<Player>p) {
        Domino domino = getNextDomino();

        //randomly set rotation of domino:
        int ranRot = randomNum(3) * 90;
        domino.setRotation(ranRot);

        //randomly select available placement:
        ArrayList<GridPosition> placements = grid.availableSpaces(domino);
        int ranPlace = randomNum(placements.size());

        //calls placeDomino:
        boolean placeable = grid.placeDomino(placements.get(ranPlace).getY(), placements.get(ranPlace).getX(), domino);
        if(!placeable)
            System.out.println("can't place there");
    }


}





