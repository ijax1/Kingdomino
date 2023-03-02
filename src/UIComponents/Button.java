//package UIComponents;
//
//import java.awt.Graphics2D;
//
//import Backend.Kingdomino;
//import UIComponents.Render.Coordinate;
//
//public abstract class Button extends Component{
//
//    boolean clicked;
//
//    Button(Coordinate position, Kingdomino k) {
//        super(position, k);
//        this.clicked = false;
//    }
//
//    @Override
//    public void setPosition(Coordinate coordinate) {
//
//    }
//
//    @Override
//    public boolean onComponent(Coordinate c) {
//        return false;
//    }
//
//    @Override
//    public void draw(Graphics2D g) {
//
//    }
//
//    @Override
//    public void whenClicked() {
//        this.clicked = !this.clicked;
//        doAction();
//    }
//
//    abstract public void doAction();
//}
