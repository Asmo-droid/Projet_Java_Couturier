import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

//on définit l'ensemble des éléments du décor ici

public class Playground {
    private ArrayList<Sprite> environment = new ArrayList<>();



    public Playground (String pathName){
        try{
            final Image imageTree = ImageIO.read(new File("./img/tree.png"));
            final Image imageGrass = ImageIO.read(new File("./img/grass.png"));
            final Image imageRock = ImageIO.read(new File("./img/rock.png"));
            final Image imageTrap = ImageIO.read(new File("./img/trap.png"));
            final Image imagePortal = ImageIO.read(new File("./img/portal.png"));

            final int imageTreeWidth = imageTree.getWidth(null);
            final int imageTreeHeight = imageTree.getHeight(null);

            final int imageGrassWidth = imageGrass.getWidth(null);
            final int imageGrassHeight = imageGrass.getHeight(null);

            final int imageRockWidth = imageRock.getWidth(null);
            final int imageRockHeight = imageRock.getHeight(null);

            final int imageTrapWidth = imageTrap.getWidth(null);
            final int imageTrapHeight = imageTrap.getHeight(null);

            final int imagePortalWidth = imagePortal.getWidth(null);
            final int imagePortalHeight = imagePortal.getHeight(null);

            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line=bufferedReader.readLine();
            int lineNumber = 0;
            int columnNumber = 0;
            while (line!= null){
                for (byte element : line.getBytes(StandardCharsets.UTF_8)){
                    switch (element){
                        case 'T' : environment.add(new SolidSprite(columnNumber*imageTreeWidth,
                                lineNumber*imageTreeHeight,imageTree, imageTreeWidth, imageTreeHeight, 100));
                            break;
                        case ' ' : environment.add(new Sprite(columnNumber*imageGrassWidth,
                                lineNumber*imageGrassHeight, imageGrass, imageGrassWidth, imageGrassHeight));
                            break;
                        case 'R' : environment.add(new SolidSprite(columnNumber*imageRockWidth,
                                lineNumber*imageRockHeight, imageRock, imageRockWidth, imageRockHeight, 100));
                            break;
                        case 'P' : SolidSprite trap = new SolidSprite(columnNumber*imageTrapWidth,
                                lineNumber*imageTrapHeight, imageTrap, imageTrapWidth, imageTrapHeight, 100);
                            trap.setType("trap");
                            environment.add(trap);
                            break;
                        case 'O' : SolidSprite portal = new SolidSprite(columnNumber*imagePortalWidth,
                                lineNumber*imagePortalHeight, imagePortal, imagePortalWidth, imagePortalHeight, 100);
                            portal.setType("portal"); //le portail a un type différent des autres solidsprite
                            environment.add(portal);
                            break;

                    }
                    columnNumber++;
                }
                columnNumber =0;
                lineNumber++;
                line=bufferedReader.readLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Sprite> getSolidSpriteList(){
        ArrayList <Sprite> solidSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            if (sprite instanceof SolidSprite) solidSpriteArrayList.add(sprite);
        }
        return solidSpriteArrayList;
    }

    public ArrayList<Displayable> getSpriteList(){
        ArrayList <Displayable> displayableArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            displayableArrayList.add((Displayable) sprite);
        }
        return displayableArrayList;
    }
}
