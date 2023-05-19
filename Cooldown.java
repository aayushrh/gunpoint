public class Cooldown {
    public int maxCD;
    public int cd = 0;
    public Cooldown(int maxCD){
        this.maxCD = maxCD;
    }
    public void resetCooldown(){
        cd--;
    }
    public void setCd(int i){
        maxCD = i;
    }
    public boolean cd(){
        if(cd<=0){
            //System.out.println(cd + " "+maxCD);
            cd=maxCD;
            return true;
        }else{
            cd--;
            //System.out.println(cd);
            return false;
        }
    }
}
