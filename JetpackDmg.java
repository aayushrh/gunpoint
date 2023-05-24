public class JetpackDmg extends Entity{

    public JetpackDmg(){
        super("", new Vector2(), new int[] {1},25 );
    }
    public void update(){
        pos = Board.player.pos;
    }
    public void collide(Entity other){

    }
}
