public class Shield extends Enemy {
    private int internalID;
    private static int ID = 0;
    public Vector2 v;
    public Shield(Vector2 start, double speed, int cooldown, int amt){
        super("images/sheild.png", start, speed, cooldown, 5);
        image = scale(image, 0.75);
        v = start;
        for(int i = 0; i<amt;i++){
            ShieldSummon q = new ShieldSummon(v, ID, Math.pow(-1,i)*(amt-i), 100+Math.floor(i/3)*25);
            Board.entities.add(q);
        }
        internalID = ID++;
    }
    public int getID(){
        return internalID;
    }
    public void shoot(Player player){
        if(cd.cd()){
            Vector2 direction = player.getPos().sub(pos).normalize();
            Bullet bullet = new Bullet(pos, direction, new int[]{1});
            Board.entities.add(bullet);
        }
    }
    public void update(){
        move(300,350);
        v = pos;
    }
    public void collide(Entity other){
        if(other.projectile){
            this.health -= ((Bullet)other).damage;
            if(health < 0){
                death = true;
            }
        }
    }
}
