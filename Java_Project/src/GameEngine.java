import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    DynamicSprite hero;
    private boolean isgameover=false;
    private JFrame gameOverFrame; // Fenêtre Game Over
    private RenderEngine renderEngine;

    public GameEngine(DynamicSprite hero, RenderEngine renderEngine) {
        this.hero = hero;
        this.renderEngine = renderEngine;
    }

    public boolean isgameover() {
        return isgameover;
    }



    private void triggergameover() {
        System.out.println("Game Over!");
        showGameOverScreen();
    }

    @Override
    public void update() {

        if (isgameover) {return;} //si on est déjà en game over, on ne fait rien

        if (hero.isdead()) {
            isgameover = true;
            renderEngine.removeFromRenderList(hero); // Retirer le héros de la liste de rendu
            renderEngine.repaint();

            Timer gameOverTimer = new Timer(1000, (e) -> triggergameover());
            gameOverTimer.setRepeats(false);
            gameOverTimer.start();
        }



    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

private void showGameOverScreen() {
    // Vérification si la fenêtre de Game Over n'existe pas déjà
    if (gameOverFrame == null) {
        gameOverFrame = new JFrame("Game Over");
        gameOverFrame.setSize(400, 200);
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.setLocationRelativeTo(null);

        JLabel gameOverLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 36));
        gameOverLabel.setForeground(Color.RED);

        gameOverFrame.add(gameOverLabel);
        gameOverFrame.setVisible(true);
    }
}



}
