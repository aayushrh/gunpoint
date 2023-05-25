public class Boomerang extends Entity{

    Enemy nearestEntity;
    double distance = -1;
    double sped;
    public Boomerang(Vector2 pos, double sped){
        super("images/player_bullet.png",pos,new int[]{2}, 10);
        projectile = true;
        hp = 3;
        this.sped = sped;
        loadImage("images/player_bullet.png");
    }
    public void findNearest(){
        for(int i = 0; i<Board.entities.size();i++){
            if(Board.entities.get(i) instanceof Enemy){
                if(distance==-1||Board.entities.get(i).pos.distTo(pos)<distance){
                    distance = Board.entities.get(i).pos.distTo(pos);
                    nearestEntity = (Enemy)Board.entities.get(i);
                }
            }
        }
    }
    public void update(){
        findNearest();
        if(nearestEntity !=null){
            Vector2 direction = nearestEntity.pos.sub(pos).normalize();
            velo = velo.add(direction.multiply(sped*Board.slow));
            velo = velo.multiply(0.9);
            pos = pos.add(velo);
        }else{
            hp = -1;
        }
        if(nearestEntity !=null && nearestEntity.hp<0){
            nearestEntity = null;
            distance = -1;
        }
    }
    public void collide(Entity other){
        if(other instanceof Enemy){
            double t = other.hp;
            other.hp-=hp;
            hp -= t;
            distance = -1;
            nearestEntity = null;
        }
    }
}
