import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
//cette classe sert aux éléments au bougent comme le héro
public class DynamicSprite extends SolidSprite{
    private Direction direction = Direction.EAST;
    private double speed = 5;
    private double timeBetweenFrame = 150;
    private final int spriteSheetNumberOfColumn = 10;

    public DynamicSprite(double x, double y, Image image, double width, double height, int lifePoints) {
        super(x, y, image, width, height, lifePoints);
    }

    //on définit la méthode isdead qui renvoie true si le personnage est mort

    public boolean isdead() {
        return lifePoints<=0;
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment) throws IOException {
        Rectangle2D.Double moved = new Rectangle2D.Double();
        switch (direction) {
            case EAST:
                moved.setRect(super.getHitBox().getX() + speed, super.getHitBox().getY(),
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case WEST:
                moved.setRect(super.getHitBox().getX() - speed, super.getHitBox().getY(),
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case NORTH:
                moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() - speed,
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case SOUTH:
                moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() + speed,
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
        }

        for (Sprite s : environment) {
            if (s instanceof SolidSprite && s != this) {
                if (((SolidSprite) s).intersect(moved)) {
                    //si le solidsprite est un portal, on déclenche la téléportation
                    if (((SolidSprite) s).getType().equals("portal")) {
                        teleportTo(Math.random()*150+100,Math.random()*50+450);
                        return true;
                    }
                    if (((SolidSprite) s).getType().equals("trap")&& !((SolidSprite) s).getIsactivated()) {
                            ((SolidSprite) s).activate(); //on active le piège
                            try {
                                //on charge l'image du piège cassé
                                Image brokenTrapImage = ImageIO.read(new File("./img/trap_broken.png"));
                                ((SolidSprite) s).setImage(brokenTrapImage); //on met à jour l'image
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            this.lifePoints -= 50;
                            if (this.lifePoints>0) {
                                teleportTo(Math.random()*150+100,Math.random()*50+450);
                            }
                        if (this.lifePoints<0){this.lifePoints=0;}
                        return true;
                    }
                    return false; //pour tous les autres solidsprite, pas de mouvement
                }
            }
        }
        return true;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private void move(){
        switch (direction){
            case NORTH -> {
                this.y-=speed;
            }
            case SOUTH -> {
                this.y+=speed;
            }
            case EAST -> {
                this.x+=speed;
            }
            case WEST -> {
                this.x-=speed;
            }
        }
    }

    public void moveIfPossible(ArrayList<Sprite> environment){
        if (this.isdead()) {
            return; //si le héros est mort, il ne bouge plus
        }
        try {

            if (isMovingPossible(environment)) {
                move();
            }
        } catch (IOException e) {
            e.printStackTrace(); //on affiche l'erreur
        }

    }
//je crée la méthode qui va permettre de téléporter mon personnage
    public void teleportTo(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }

    @Override
    public void draw(Graphics g) {
        int index= (int) (System.currentTimeMillis()/timeBetweenFrame%spriteSheetNumberOfColumn);

        g.drawImage(image,(int) x, (int) y, (int) (x+width),(int) (y+height),
                (int) (index*this.width), (int) (direction.getFrameLineNumber()*height),
                (int) ((index+1)*this.width), (int)((direction.getFrameLineNumber()+1)*this.height),null);
    }
}
