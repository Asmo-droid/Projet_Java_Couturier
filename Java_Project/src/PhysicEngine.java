import java.lang.reflect.Array;
import java.util.ArrayList;
// ici on implémente les éléments qui apparaitront sur l'écran, qu'ils bougent ou pas
public class PhysicEngine implements Engine{
    private ArrayList<DynamicSprite> movingSpriteList;
    private ArrayList <Sprite> environment;
    private GameEngine gameEngine;

    public PhysicEngine(GameEngine gameEngine) {
        this.gameEngine=gameEngine;
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
    }

    public void addToEnvironmentList(Sprite sprite){
        if (!environment.contains(sprite)){
            environment.add(sprite);
        }
    }

    public void setEnvironment(ArrayList<Sprite> environment){
        this.environment=environment;
    }

    public void addToMovingSpriteList(DynamicSprite sprite){
        if (!movingSpriteList.contains(sprite)){
            movingSpriteList.add(sprite);
        }
    }

    @Override
    public void update() {

        if (gameEngine.isgameover()){
            return;
        }

        for(DynamicSprite dynamicSprite : movingSpriteList){
            dynamicSprite.moveIfPossible(environment);
        }
    }
}
