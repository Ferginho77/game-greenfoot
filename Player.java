import greenfoot.*;
import java.util.ArrayList;

public class Player extends Actor {

    /* ================= BASIC MOVEMENT ================= */
    private int speed = 4;
    private int vSpeed = 0;
    private final int gravity = 1;
    private final int jumpStrength = -15;

    /* ================= PLAYER STATUS ================= */
    private int nyawa = 2;
    private int spawnX, spawnY;

    /* ================= SOUND ================= */
    private GreenfootSound fallSound = new GreenfootSound("dry-fart.mp3"); 
    private GreenfootSound gameoverSound = new GreenfootSound("fail.mp3");

    /* ================= ANIMATION ================= */
    private ArrayList<GreenfootImage> idleAnim = new ArrayList<>();
    private ArrayList<GreenfootImage> runAnim = new ArrayList<>();
    private ArrayList<GreenfootImage> jumpAnim = new ArrayList<>();

    private int animIndex = 0;
    private int animDelay = 0;
    private int animSpeed = 5;

    private boolean facingRight = true;

    /* ================= CONSTRUCTOR ================= */
    public Player() {
        loadAnimation(idleAnim, "Idle", 15);
        loadAnimation(runAnim, "Run", 15);
        loadAnimation(jumpAnim, "Jump", 15);

        setImage(idleAnim.get(0));
    }

    /* ================= ACT ================= */
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

        updateAnimation();

        getWorld().showText("Nyawa: " + nyawa, 60, 30);
    }

    /* ================= MOVEMENT ================= */
    private void moveHorizontal() {

        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - speed, getY());
            if (facingRight) flip();
        }

        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + speed, getY());
            if (!facingRight) flip();
        }

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

    /* ================= ANIMATION SYSTEM ================= */
    private void updateAnimation() {
        animDelay++;
        if (animDelay < animSpeed) return;
        animDelay = 0;

        ArrayList<GreenfootImage> currentAnim;

        if (!onGround()) {
            currentAnim = jumpAnim;
        } 
        else if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("d")) {
            currentAnim = runAnim;
        } 
        else {
            currentAnim = idleAnim;
        }

        setImage(currentAnim.get(animIndex));
        animIndex++;

        if (animIndex >= currentAnim.size()) {
            animIndex = 0;
        }
    }

    private void loadAnimation(ArrayList<GreenfootImage> list, String name, int count) {
        for (int i = 1; i <= count; i++) {
            GreenfootImage img = new GreenfootImage(name + " (" + i + ").png");
            img.scale(40, 55);
            list.add(img);
        }
    }

    private void flip() {
        facingRight = !facingRight;
        getImage().mirrorHorizontally();
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
        if (isTouching(Ground.class) || getY() > getWorld().getHeight() - 10) {
            nyawa--;
            fallSound.play();
            setLocation(spawnX, spawnY);
            vSpeed = 0;

            if (nyawa <= 0) {
                getWorld().showText("GAME OVER", 
                    getWorld().getWidth()/2, 
                    getWorld().getHeight()/2);
                gameoverSound.play();
                Greenfoot.stop();
            }
        }
    }

    private void checkFallOut() {
        if (getY() > getWorld().getHeight()) {
            nyawa--;
            setLocation(spawnX, spawnY);
            vSpeed = 0;

            if (nyawa <= 0) {
                getWorld().showText("GAME OVER", 
                    getWorld().getWidth()/2, 
                    getWorld().getHeight()/2);
                Greenfoot.stop();
            }
        }
    }
}
