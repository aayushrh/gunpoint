public class Spiral extends Enemy{

    public boolean shooting = true;
    public int degree = 0;
    private Cooldown spiralCD;
    private int amount;
    public Spiral(Vector2 start, double s, int c, int cooldown, int amount){
        super(start, s, c);
        spiralCD = new Cooldown(cooldown);
        this.amount = amount;
    }
    public void shoot(Player player){
        if(cd.cd()){
            shooting = true;
        }
    }
    public void update(){
        if(!shooting) {
            move(300, 350);
        }
        if (shooting){
            degree += 5;

            if (degree >= 360){
                shooting = false;
                degree = 0;
            }
            if(spiralCD.cd()) {
                for(int i = 0; i<amount;i++) {
                    Bullet bullet = new Bullet(pos, new Vector2(Math.toRadians(degree+360*i/amount)), new int[]{1}, 5);
                    Board.entities.add(bullet);
                }
            }

        }
    }
    public void collide(Entity other){

    }
}
