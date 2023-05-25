import java.awt.event.KeyEvent;
import java.util.HashMap;

public class TitleScreen extends Entity{
    private HashMap<String, Boolean> inputs = new HashMap<String, Boolean>();
    public Entity next = null;
    public TitleScreen() {
        super("images/screens/titleScreen.png", new Vector2(0, 0), new int[]{1}, 25);
        inputs.put("Enter", false);
        inputs.put("Click", false);
        pos = new Vector2(image.getWidth()/4, image.getHeight()/4);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            inputs.replace("Enter", true);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            inputs.replace("Enter", false);
        }
    }

    public void click(){
        inputs.replace("Click", true);
    }

    public void release(){
        inputs.replace("Click", false);
    }

    public void update() {
        if(inputs.get("Enter")){
            hp = -1;
            next = new ClassSelection();
        }

        if(inputs.get("Click")){
            if((Board.mousePos.x  > 385 && Board.mousePos.x  < 510) && (Board.mousePos.y  > 287 && Board.mousePos.y  < 347)){
                hp = -1;
                next = new ClassSelection();
            }
            if((Board.mousePos.x  > 348 && Board.mousePos.x  < 550) && (Board.mousePos.y  > 405 && Board.mousePos.y  < 461)){
                hp = -1;
                next = new Credits();
            }
        }
    }

    public void collide(Entity other){

    }

}
