public class Spiral extends Enemy{

    public boolean shooting = true;
    public double degree = 0;
    private Cooldown spiralCD;
    private double degreeChange;
    private int amount;
    public Spiral(Vector2 start, double speed, int cooldown, int spiralCooldown, int amount, double degree){
        super("images/spiral.png", start, speed, cooldown, 2);
        image = scale(image, 0.5);
        spiralCD = new Cooldown(spiralCooldown);
        this.amount = amount;
        degreeChange=degree;
    }
    public Spiral(Vector2 start, double speed, int cooldown, int spiralCooldown, int amount){
        this(start, speed, cooldown, spiralCooldown, amount, 5);
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
                    Bullet bullet = new Bullet(pos, new Vector2(Math.toRadians(degree+360.0*i/amount)).normalize(), new int[]{1}, 5);
                    Board.entities.add(bullet);
                    System.out.println(i);
                }
            }

        }
    }
}
