import greenfoot.*;

public class Player extends Actor {
    private int speed = 4;
    private int vSpeed = 0;
    private int gravity = 1;
    private int jumpStrength = -15;
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
        if (spawnX == 0) { spawnX = getX(); spawnY = getY(); }
        
        handleMovement();
        applyGravity();
        checkLava();
        getWorld().showText("Nyawa: " + nyawa, 50, 30);
    }

    private void handleMovement() {
        if (Greenfoot.isKeyDown("d")) setLocation(getX() + speed, getY());
        if (Greenfoot.isKeyDown("a")) setLocation(getX() - speed, getY());
        if (Greenfoot.isKeyDown("space") && isOnSolidGround()) {
            vSpeed = jumpStrength;
            fall();
        }
    }

    private void applyGravity() {
        if (isOnSolidGround()) {
            vSpeed = 0;
        } else {
            fall();
        }
    }

    private void fall() {
        vSpeed += gravity;
        setLocation(getX(), getY() + vSpeed);
    }

    private boolean isOnSolidGround() {
    // Cari objek FalseBlock tepat di bawah kaki
    FalseBlock fb = (FalseBlock) getOneObjectAtOffset(0, getImage().getHeight()/2 + 2, FalseBlock.class);
    
    if (fb != null && fb.isActive()) {
        return true;
    }

    // Cek juga blok start yang permanen
    Actor b = getOneObjectAtOffset(0, getImage().getHeight()/2 + 2, Block.class);
    return b != null;
}
    private void checkLava() {
        if (isTouching(Ground.class) || getY() > getWorld().getHeight() - 10) {
            nyawa--;
            fallSound.play();
            setLocation(spawnX, spawnY);
            vSpeed = 0;
            if (nyawa <= 0) {
                getWorld().showText("GAME OVER", getWorld().getWidth()/2, getWorld().getHeight()/2);
                gameoverSound.play();
                Greenfoot.stop();
            }
        }
    }
}
