import java.util.ArrayList;
import java.util.Arrays;

public class NinjaSwing extends Entity{
    public NinjaSwing(){
        super("images/player_bullet.png", new Vector2(400, 250), new int[] {1,2},100 );
        projectile = true;
        loadImage("images/player_bullet.png");
    }

    public void update(){
        Vector2 input = new Vector2();
        if(Board.player.inputs.get("W")){
            input = input.add(new Vector2(0, -1));
        }
        if(Board.player.inputs.get("A")){
            input = input.add(new Vector2(-1, 0));
        }
        if(Board.player.inputs.get("S")){
            input = input.add(new Vector2(0, 1));
        }
        if(Board.player.inputs.get("D")){
            input = input.add(new Vector2(1, 0));
        }
        if(Board.player.inputs.get("SPACE")&&Board.player.cd[1].getCd()){
            if(Board.player.dash==null) Board.player.dash = input.normalize().multiply(50);
            Board.player.cd[1].cd();
        }else{
            Board.player.cd[1].resetCooldown();
        }
//        if(Board.player.inputs.get("Space")){
//
//        }
        input = input.normalize();
        if(Board.player.dash!=null){
            velo = Board.player.dash;
            Board.player.dash = null;
        }else {
            velo = velo.add(input.multiply(Board.player.sped * Board.slow));
        }
        velo = velo.multiply(0.9);
        pos = pos.add(velo);

        if(Board.outOfBoundsX(pos)){
            pos = pos.sub(new Vector2(velo.x,0));
        }
        if(Board.outOfBoundsY(pos)){
            pos = pos.sub(new Vector2(0,velo.y));
        }
        if(Board.player.swing>0) {
            Board.player.swing--;
            for(int i = 0; i < Board.entities.size(); i++) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(1);
                if (((Board.entities.get(i) instanceof Enemy) || (Board.entities.get(i).projectile && temp.equals(Board.entities.get(i).collLayer))) && Board.player.swing > 0) {
                    if(isCollide(Board.entities.get(i))) Board.entities.get(i).hp -= 1;
                }
            }
        }
    }
    public boolean isCollide(Entity other){
        Vector2 otherr = other.pos;
        int otherRad = other.collRad;
        Vector2 direction = Board.mousePos.normalize();
        Line one = new Line(Board.player.pos,new Vector2(direction.getAngle()+Math.toRadians(Board.player.angle)).multiply(collRad));
        Line two = new Line(Board.player.pos,new Vector2(direction.getAngle()-Math.toRadians(Board.player.angle)).multiply(collRad));
        if(pos.distTo(otherr)<otherRad+this.collRad){
            double a = Math.toDegrees(otherr.sub(pos).getAngle());
            double f = Math.toDegrees(direction.getAngle());
            if(a<f-Board.player.angle||a>f+Board.player.angle){
                return one.closestPoint(otherr).distTo(otherr)<otherRad||two.closestPoint(otherr).distTo(otherr)<otherRad;
            }else{
                return true;
            }
        }
        return false;
    }

    public void collide(Entity other){
    }
}
