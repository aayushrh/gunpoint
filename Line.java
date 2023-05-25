public class Line {
    public Vector2 start;
    public Vector2 end;
    public double m;
    public double b;
    public Line(Vector2 start, Vector2 end){
        if(start.x>end.x){
            this.end = start;
            this.start = end;
        }else {
            this.start = start;
            this.end = end;
        }
        m = start.sub(end).x/start.sub(end).y;
        b = start.y-m*start.x;
    }
    public Line(double slope, double yIntercept){
        m = slope;
        b = yIntercept;
    }
    public Line(Vector2 point, double slope){
        m = slope;
        b = point.y-m*point.x;
    }
    public Vector2 intersection(Line other){
        if(m==other.m){
            return null;
        }
        double x = (b-other.b)/(other.m-m);
        double y = m*x + b;
        return new Vector2(x,y);
    }
    public Line perpendicular(Vector2 point){
        return new Line(point,-1/m);
    }
    public Vector2 closestPoint(Vector2 point){
        Line temp = perpendicular(point);
        Vector2 i = intersection(temp);
        if(start!=null||end!=null) {
            if (i.x < start.x) {
                return start;
            } else if (i.x > end.x) {
                return end;
            }
        }
        return i;
    }
}
