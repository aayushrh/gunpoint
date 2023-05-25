public abstract class Enemy extends Entity{
    public double sped = 1.0;
    public Cooldown cd;
    public Enemy(String path, Vector2 start, double speed, int cooldown, int health){
        super(path, start,new int[]{2}, 25);
        sped = speed;
        cd = new Cooldown(cooldown);
        hp = health;
    }

    public abstract void shoot(Player player);

    public void move(int min, int max){
        if(Board.player != null) {
            Player player = Board.player;
            Vector2 input = new Vector2();
            if (player.getPos().distTo(pos) >= min && player.getPos().distTo(pos) <= max) {
                shoot(player);
            } else if (player.getPos().distTo(pos) <= min) {
                Vector2 direction = pos.sub(player.getPos()).normalize();
                input = input.add(direction);
                //pos = pos.add(direction);
            } else if (player.getPos().distTo(pos) >= max) {
                Vector2 direction = player.getPos().sub(pos).normalize();
                input = input.add(direction);
                //pos = pos.add(direction);
            }
            input = input.normalize();
            velo = velo.add(input.multiply(sped * Board.slow));
            velo = velo.multiply(0.9);
            pos = pos.add(velo);
        }
    }
    public void collide(Entity other){
//        if(other.projectile){
//            this.hp -= other.hp;
//        }
    }
}
