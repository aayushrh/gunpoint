public class Spiral extends Enemy{
    private int internalID;
    private static int ID = 0;
    public Vector2 v;
    public boolean shooting = true;
    public int degree = 0;
    private int cooldown;
    public Spiral(Vector2 start, double s, int c, int cooldown){
        super(start, s, c);
        v = start;
        this.cooldown = cooldown;
        internalID = ID++;
    }
    public int getID(){
        return internalID;
    }
    public void shoot(Player player){
        if(getTCD()){
            shooting = true;
        }
    }
    public void update(){
        move(300,350);
        v = pos;
        if (shooting){
            degree += 5;
            cooldown--;

            if (degree >= 360){
                shooting = false;
                degree = 0;
            }
            Vector2 direction = new Vector2(Math.toRadians(degree));
            if (cooldown < 0){
                Bullet bullet = new Bullet(pos, direction, new int[]{1});
                Board.entities.add(bullet);
                cooldown = 5;
            }
        }
    }
    public void collide(Entity other){
        
    }
}
