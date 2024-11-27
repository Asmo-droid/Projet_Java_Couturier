import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
// on définit ici l'ensemble des sprite qui peuvent interagir entre eux, ils ont une réalité physique, le héro par exemple ne peut pas traverser un arbre

public class SolidSprite extends Sprite{
    //on va avoir besoin d'un nouvel attribut pour appliquer différents comportements selon le solide.
    private String type;
    protected int lifePoints;
    private boolean isactivated = false;

    public SolidSprite(double x, double y, Image image, double width, double height, int lifePoints) {
        super(x, y, image, width, height);
        this.type="solid";
        this.lifePoints=lifePoints;
    }

//on définit classiquement un getter...

    public String getType(){
        return type;
    }
//...et un setter
    public void setType(String type){
        this.type = type;
    }
    public boolean getIsactivated(){
        return isactivated;
    }


    public void activate(){
        isactivated=true; //quand le trap est activé, on met son état à jour
    }

    public Rectangle2D getHitBox() {
        return new Rectangle2D.Double(x,y, (double) width,(double) height);
    }

    public boolean intersect(Rectangle2D.Double hitBox){
        return this.getHitBox().intersects(hitBox);
    }

    public void setImage(Image newImage) {
        this.image = newImage;
    }

}