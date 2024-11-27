import java.awt.*;
import java.awt.geom.Rectangle2D;
//c'est la classe parente de SolidSprite et DynamicSprite qui définit tous les objets du jeu


public class Sprite implements Displayable{
    protected double x;
    protected double y;
    protected Image image;
    protected final double width;
    protected final double height;

    public Sprite(double x, double y, Image image, double width, double height) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    public Rectangle2D getHitBox() {
        return new Rectangle2D.Double(x,y, (double) width,(double) height);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,(int)x,(int)y,null);
    }
}
