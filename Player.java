import greenfoot.*;

public class Player extends Actor {

    private int speed = 4;
    private int vSpeed = 0;
    private final int gravity = 1;
    private final int jumpStrength = -15;

    private int nyawa = 2;
    private int spawnX, spawnY;
    private GreenfootSound fallSound = new GreenfootSound("dry-fart.mp3"); 
    private GreenfootSound gameoverSound = new GreenfootSound("fail.mp3");

    public Player() {
        GreenfootImage img = new GreenfootImage("char.png");
        img.scale(40, 55);
        setImage(img);
    }

    public void act() {
        if (spawnX == 0) {
            spawnX = getX();
            spawnY = getY();
        }

        moveHorizontal();
        applyGravity();
        checkCoin();
        checkFinish();
        checkFallOut();
        checkLava();
        getWorld().showText("Nyawa: " + nyawa, 60, 30);
    }

    /* ================= MOVEMENT ================= */

    private void moveHorizontal() {
        if (Greenfoot.isKeyDown("a")) setLocation(getX() - speed, getY());
        if (Greenfoot.isKeyDown("d")) setLocation(getX() + speed, getY());

        if (Greenfoot.isKeyDown("space") && onGround()) {
            vSpeed = jumpStrength;
        }
    }

    private void applyGravity() {
        vSpeed += gravity;

        int newY = getY() + vSpeed;

        if (vSpeed > 0 && onGround()) {
            vSpeed = 0;
            return;
        }

        setLocation(getX(), newY);
    }

    private boolean onGround() {
        int offset = getImage().getHeight() / 2 + 1;

        return getOneObjectAtOffset(0, offset, Block.class) != null
            || getOneObjectAtOffset(0, offset, FalseBlock.class) != null;
    }

    /* ================= GAME LOGIC ================= */

    private void checkCoin() {
    Coin coin = (Coin) getOneIntersectingObject(Coin.class);
    if (coin != null) {
        World w = getWorld();
        w.removeObject(coin);

        if (w instanceof MyWorld) {
            ((MyWorld) w).updateCoinUI();
        }
        if (w instanceof WorldLevel2) {
            ((WorldLevel2) w).updateCoinUI();
        }
    }
}





   private void checkFinish() {
    if (isTouching(FinishBlock.class)) {
        World w = getWorld();

        if (w instanceof MyWorld) {
            ((MyWorld) w).finishReached();
        }

        if (w instanceof WorldLevel2) {
            ((WorldLevel2) w).finishReached();
        }
    }
}


        private void checkLava() { 
            if (isTouching(Ground.class) || getY() > getWorld().getHeight() - 10) 
            { nyawa--; 
                fallSound.play(); 
                setLocation(spawnX, spawnY);
                vSpeed = 0; if (nyawa <= 0) { 
                getWorld().showText("GAME OVER", getWorld().getWidth()/2, getWorld().getHeight()/2);
                gameoverSound.play(); Greenfoot.stop(); 
            } 
        } 
    }
        
    private void checkFallOut() {
        if (getY() > getWorld().getHeight()) {
            nyawa--;
            setLocation(spawnX, spawnY);
            vSpeed = 0;

            if (nyawa <= 0) {
                getWorld().showText("GAME OVER", getWorld().getWidth()/2, getWorld().getHeight()/2);
                Greenfoot.stop();
            }
        }
}
}
