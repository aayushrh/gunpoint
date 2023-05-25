public class ShieldSummon extends Entity {
    public Shield followEntity;
    private int ID;
    private double cA;
    private double angleSpeed;
    private int layer;
    private int layerID;
    private double distance;
    public ShieldSummon(Vector2 start, Shield follow,int layerNum, double angleSpeed, int totalLayerNum){
        super("images/green-circle.png", start, new int[]{2}, 25);
        followEntity = follow;
        hp = 1;
        if(totalLayerNum%2!=0){
            this.angleSpeed = -angleSpeed;
        }else{
            this.angleSpeed = angleSpeed;
        }
        distance = totalLayerNum*25;
        cA = layerNum*360.0/totalLayerNum;
    }
    public void update(){
        if(followEntity.pos.equals(new Vector2(0.0, 30.0))){
            hp = -1;
            Board.entities.remove(Board.entities.indexOf(this));
        }
        cA = (cA+angleSpeed*Board.slow)%360;
        pos = followEntity.pos.add(new Vector2(Math.toRadians(cA)).multiply(distance));
        if(followEntity.hp<=0){
            hp=-1;
        }
    }
    public void collide(Entity other){
//        if(other.projectile){
//            this.hp -= other.hp;
//        }
    }
}