package UIComponents.Render;

public abstract class Polyhedron {
    public Polygon[] faces;
    Coordinate center;

    public void setToRotation(double xRotation, double yRotation, double zRotation){
        for(Polygon face: faces)
            face.setToRotation(xRotation,yRotation,zRotation);
    }




}
