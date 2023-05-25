public class Vector2 {
    public double x;
    public double y;

    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;

    }
    public Vector2(double angle){
        this.x = Math.cos(angle);
        this.y = Math.sin(angle);
    }
    public Vector2 add(Vector2 other){
        Vector2 vec = new Vector2();
        vec.x = this.x + other.x;
        vec.y = this.y + other.y;
        return vec;
    }

    public Vector2 sub(Vector2 other){
        Vector2 vec = new Vector2();
        vec.x = this.x - other.x;
        vec.y = this.y - other.y;
        return vec;
    }

    public double getMag(){
        double num = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
        return num;
    }

    public Vector2 normalize(){
        if(getMag() > 0) {
            Vector2 vec = new Vector2();
            double angle = getAngle();
            vec.x = Math.cos(angle);
            vec.y = Math.sin(angle);
            return vec;
        }else{
            return this;
        }
    }

    public double getAngle(){
        return Math.atan2(this.y, this.x);
    }

    public Vector2 multiply(double n){
        Vector2 vec = new Vector2();
        vec.x = this.x * n;
        vec.y = this.y * n;
        return vec;
    }

    public double distTo(Vector2 other){
        return Math.abs((this.sub(other).getMag()));
    }

    public boolean equals(Vector2 other){
        return(other.x == x && other.y == y);
    }
}
