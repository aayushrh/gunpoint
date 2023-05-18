public abstract class Enemy extends Entity{
    public double sped = 1.0;
    public int cd = 1;
    public int tcd;
    public Enemy(Vector2 start, double s, int c){
        super("images/player.png", start,new int[]{2}, 25);
        sped = s;
        cd = c;
        tcd = cd;
    }

    public abstract void shoot(Player player);

    public void move(int min, int max){
        Player player = Board.player;
        Vector2 input = new Vector2();
        if(player.getPos().distTo(pos) >= min && player.getPos().distTo(pos) <= max){
            shoot(player);
        }
        else if (player.getPos().distTo(pos) <= min){
            Vector2 direction = pos.sub(player.getPos()).normalize();
            input = input.add(direction);
            //pos = pos.add(direction);
        }
        else if (player.getPos().distTo(pos) >= max){
            Vector2 direction = player.getPos().sub(pos).normalize();
            input = input.add(direction);
            //pos = pos.add(direction);
        }
        input = input.normalize();
        velo = velo.add(input.multiply(sped));
        velo = velo.multiply(0.9);
        pos = pos.add(velo);
    }
    public boolean getTCD(){
        if(tcd == 0){
            tcd = cd;
            return true;
        }else{
            tcd--;
            return false;
        }
    }
}
