public class Bar extends Entity{

    private double maxValue;
    private boolean horizon;
    private String path;

    public Bar(String path, Vector2 pos, double maxValue, boolean x_major){
        super(path, pos, new int[]{}, 0);
        this.path = path;
        this.maxValue = maxValue;
        this.horizon = x_major;
    }

    public void setValue(double value){
        loadImage(path);
        if(horizon){
            image = scale(image, new Vector2(value/maxValue, 1));
        }else{
            image = scale(image, new Vector2(1, value/maxValue));
        }
    }

    public void update(){

    }

    public void collide(Entity other){

    }

}
