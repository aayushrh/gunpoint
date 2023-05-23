import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Entity {
    public BufferedImage image;
    protected Vector2 pos;
    protected Vector2 velo;
    public ArrayList<Integer> collLayer;
    public int collRad;
    public double hp = 1;
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

    public static BufferedImage rotateImage(BufferedImage buffImage, double angle) {
        double radian = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(radian));
        double cos = Math.abs(Math.cos(radian));

        int width = buffImage.getWidth();
        int height = buffImage.getHeight();

        //int nWidth = (int) Math.floor((double) width * cos + (double) height * sin);
        //int nHeight = (int) Math.floor((double) height * cos + (double) width * sin);

        BufferedImage rotatedImage = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = rotatedImage.createGraphics();

        graphics.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        //graphics.translate((nWidth - width) / 2, (nHeight - height) / 2);
        // rotation around the center point
        graphics.rotate(radian, (double) (width / 2), (double) (height / 2));
        graphics.drawImage(buffImage, 0, 0, null);
        graphics.dispose();

        return rotatedImage;
    }

    public void loadImage(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void loadImage(String path, double degrees) {
        try {
            image = ImageIO.read(new File(path));
            image = rotateImage(image, degrees);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public BufferedImage scale(BufferedImage image, double scale){
        BufferedImage before = image;
        int w = before.getWidth();
        int h = before.getHeight();
        BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(0.5, 0.5);
        AffineTransformOp scaleOp =
                new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        after = scaleOp.filter(before, after);
        return after;
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                image,
                (int)(pos.x - image.getWidth()/4),
                (int)(pos.y - image.getHeight()/4),
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
