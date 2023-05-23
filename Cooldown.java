public class Cooldown {
    public double maxCD;
    public double cd = 0;
    public Cooldown(int maxCD){
        this.maxCD = maxCD;
        this.cd = maxCD;
    }
    public void resetCooldown(){
        cd-=Board.slow;
    }
    public void setCd(int i){maxCD = i;}
    public boolean getCd(){
        return cd<=0;
    }
    public void multCD(double i){
        maxCD *=i;
    }
    public boolean cd(){
        if(maxCD==-1){
            return false;
        }
        if(cd<=0){
            //System.out.println(cd + " "+maxCD);
            cd=maxCD;
            return true;
        }else{
            cd-=Board.slow;
            //System.out.println(cd);
            return false;
        }
    }
}
