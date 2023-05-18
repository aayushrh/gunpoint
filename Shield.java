public class Shield extends Enemy{
    private int internalID;
    private static int ID = 0;
    public static Vector2 v;
    public Shield(Vector2 start, double s, int c, int amt){
        super(start, s, c);
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
        if(getTCD()){
            Vector2 direction = player.getPos().sub(pos).normalize();
            Bullet bullet = new Bullet(pos, direction, 1);
            Board.entities.add(bullet);
        }
    }
    public void update(){
        move(300,350);
        v = pos;
    }
    public void collide(Entity other){
        
    }
}
