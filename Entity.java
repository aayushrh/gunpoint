import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Entity {
    private BufferedImage image;
    protected Vector2 pos;
    protected Vector2 velo;
    public ArrayList<Integer> collLayer;
    public int collRad;
    public boolean death;
    public boolean projectile = false;

    public Entity(String path, Vector2 pos, int[] collLayer, int collRad) {
        loadImage(path);
        this.pos = pos;
        this.velo = new Vector2();
        this.collLayer = new ArrayList<Integer>();
        for(int i : collLayer){
            this.collLayer.add(i);
        }
        this.collRad = collRad;
    }

    public void loadImage(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                image,
                (int)pos.x,
                (int)pos.y,
                observer
        );
    }

    public Vector2 getPos() {
        return pos;
    }

    public void destroy(){
        for(int i = 0; i < Board.entities.size(); i++){
            if(Board.entities.get(i).equals(this)){
                Board.entities.remove(i);
                break;
            }
        }
    }
    public abstract void update();
    public abstract void collide(Entity other);
}
