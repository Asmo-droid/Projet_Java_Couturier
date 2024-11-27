import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class RenderEngine extends JPanel implements Engine{

    private ArrayList<Displayable> renderList;
    private GameEngine gameEngine;

    public RenderEngine(JFrame jFrame, GameEngine gameEngine) {

        renderList = new ArrayList<>();
        this.gameEngine = gameEngine;
    }


    public void addToRenderList(Displayable displayable){
        if (!renderList.contains(displayable)){
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable){
        if (!renderList.contains(displayable)){
            renderList.addAll(displayable);
        }
    }

    public void removeFromRenderList(Displayable displayable) {
        renderList.remove(displayable);
    }
    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject:renderList) {
            renderObject.draw(g);
        }
    }

    @Override
    public void update(){
        if (gameEngine.isgameover()) {
            return; // Arrêter le rendu si le jeu est terminé
        }
        this.repaint();
    }
}
