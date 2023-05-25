public class Shield extends Enemy {
    public Vector2 v;
    private int c;
    private int amt;
    private double as;
    private boolean spawn;
    public Shield(Vector2 start, double speed, int cooldown, int amt, double angleSpeed, int startLayer){
        super("images/sheild.png", start, speed, cooldown, 5);
        image = scale(image, 0.5);
        v = start;
        c = startLayer;
        this.amt = amt;
        as = angleSpeed;
    }
    public void shoot(Player player){
        if(cd.cd()){
            Vector2 direction = player.getPos().sub(pos).normalize();
            Bullet bullet = new Bullet(pos, direction, new int[]{1});
            Board.entities.add(bullet);
        }
    }
    public void update(){
        if(!spawn){
            double offset = Math.random();
            int t = 0;
            for(int i = 1; i<=amt;i++){
                ShieldSummon q = new ShieldSummon(v, this, i-t, offset*as*c, c);
                Board.entities.add(q);
                if(i-t==c){
                    t+=c;
                    c++;
                    offset = Math.random();
                }
            }
        }
        spawn = true;
        move(300,350);
        v = pos;
    }
}
