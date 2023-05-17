public class Enemy extends Entity{
    private double sped = 1.0;
    public Enemy(Vector2 start){
        super("images/player.png", start,2, 50);
    }
    public void update(){
        Player player = Board.player;
        Vector2 input = new Vector2();
        if(player.getPos().distTo(pos) >= 300 && player.getPos().distTo(pos) <= 350){
            Vector2 direction = player.getPos().sub(pos).normalize();
            Bullet bullet = new Bullet(pos, direction, 1);
            Board.entities.add(bullet);
        }
        else if (player.getPos().distTo(pos) <= 300){
            Vector2 direction = pos.sub(player.getPos()).normalize();
            input = input.add(direction);
            //pos = pos.add(direction);
        }
        else if (player.getPos().distTo(pos) >= 350){
            Vector2 direction = player.getPos().sub(pos).normalize();
            input = input.add(direction);
            //pos = pos.add(direction);
        }
        input = input.normalize();
        velo = velo.add(input.multiply(sped));
        velo = velo.multiply(0.9);
        pos = pos.add(velo);
    }

    public void collide(Entity other){

    }
}
