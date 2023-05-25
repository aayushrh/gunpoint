public class JetpackDmg extends Entity{

    public JetpackDmg(){
        super("", new Vector2(), new int[] {2},25 );
    }
    public void update(){
        pos = Board.player.pos;
    }
    public void collide(Entity other){
        if(other instanceof Enemy) {
            other.hp -= 1;
        }
    }
}
