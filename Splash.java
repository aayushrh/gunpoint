public class Splash extends Enemy{
    Vector2 pP;
    int sc;
    public Splash(Vector2 start, double speed, int cooldown, int splashCooldown){
        super("images/splash.png", start, speed, cooldown, 4);
        image = scale(image, 0.5);
        sc = splashCooldown;
    }
    public void shoot(Player player){
        if(cd.cd()){//won't run cd if shooting true cuz priority lmao
            pP = player.getPos();
            SplashSummon splash = new SplashSummon(pP, sc);
            Board.entities.add(splash);
        }

    }
    public void update(){
        move(300,350);
    }
}
