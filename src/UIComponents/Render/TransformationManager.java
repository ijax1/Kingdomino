package UIComponents.Render;

public class TransformationManager {
    double[] rotations = new double[3];
    double[] translations = new double[3];

    public TransformationManager(double[] rotations, double[] translations){
        this.rotations = rotations;
        this.translations = translations;
    }

    public double getXRotation(){
        return rotations[0];
    }

    public double getYRotation(){
        return rotations[1];
    }

    public double getZRotation(){
        return rotations[2];
    }

    public double getXTranslation(){
        return translations[0];
    }

    public double getYTranslation(){
        return translations[1];
    }

    public double getZTranslation(){
        return translations[2];
    }

    public void setXRotation(double rotation){
        rotations[0] = rotation;
    }

    public void setYRotation(double rotation){
        rotations[1] = rotation;
    }

    public void setZRotation(double rotation){
        rotations[2] = rotation;
    }

    public void setXTranslation(double translation){
        translations[0] = translation;
    }

    public void setYTranslation(double translation){
        translations[1] = translation;
    }

    public void setZTranslation(double translation){
        translations[2] = translation;
    }

    public double[] getRotations(){
        return rotations;
    }

    public double[] getTranslations(){
        return translations;
    }

    public double[] setRotations(double[] rotations){
        return rotations;
    }

    public double[] setTranslations(double[] translations){
        return translations;
    }
}
