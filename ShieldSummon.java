public class ShieldSummon extends Entity {
    private Shield followEntity;
    private int ID;
    private double cA;
    private double angleSpeed;
    private int layer;
    private int layerID;
    private double distance;
    public ShieldSummon(Vector2 start, int ID,int layerNum, double angleSpeed, int totalLayerNum){
        super("images/green-circle.png", start, new int[]{2}, 25);
        hp = 1;
        this.ID = ID;
        if(totalLayerNum%2!=0){
            this.angleSpeed = -angleSpeed;
        }else{
            this.angleSpeed = angleSpeed;
        }
        distance = totalLayerNum*25;
        cA = layerNum*360.0/totalLayerNum;
    }
    public void update(){
        
        if(followEntity==null){
            for(int i = 0; i< Board.entities.size(); i++){
                if (Board.entities.get(i) instanceof Shield){
                    if(((Shield) Board.entities.get(i)).getID()==ID){
                        followEntity = (Shield) Board.entities.get(i);
                    }
                }
            }
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