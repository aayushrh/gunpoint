public class ShieldSummon extends Entity {
    private Shield followEntity;
    private int ID;
    private double cA;
    private double angleSpeed;
    private double distance;
    public ShieldSummon(Vector2 start, int ID, double angleSpeed, double dist){
        super("images/green-circle.png", start, new int[]{2}, 25);
        this.ID = ID;
        this.angleSpeed = angleSpeed;
        distance = dist;
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
        cA = (cA+angleSpeed)%360;
        pos = followEntity.pos.add(new Vector2(cA*Math.PI/180).multiply(distance));
    }
    public void collide(Entity other){

    }
}