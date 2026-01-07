import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor
{
    private int speed = 4;
    int nyawa = 5;
    private int vSpeed = 0;
    private int gravity = 1;
    private int jumpStrength = -15;
    private int spawnX, spawnY;
    private boolean spawnSaved = false;

    public Player(){
        GreenfootImage img = new GreenfootImage("char.png");
        img.scale(50, 70);
        setImage(img);
    }

    public void act(){
        // Simpan posisi spawn hanya sekali
        if(!spawnSaved && getWorld() != null){
            spawnX = getX();
            spawnY = getY();
            spawnSaved = true;
        }
        getWorld().showText("Nyawa :" + nyawa, 50, 50);
        handleMovement();
        applyGravity();
        checkGround();
    }

    public void handleMovement(){
        if(Greenfoot.isKeyDown("d")) setLocation(getX()+speed, getY());
        if(Greenfoot.isKeyDown("a")) setLocation(getX()-speed, getY());

        if(Greenfoot.isKeyDown("space") && (isOnGround() || isOnBlock() || isOnFalseBlock())){
            jump();
        }
    }

    public void jump(){
        vSpeed = jumpStrength;
        fall();
    }

    public void fall(){ vSpeed += gravity; setLocation(getX(), getY() + vSpeed); }

    public void applyGravity(){
        if(isOnGround() || isOnBlock() || isOnFalseBlock()){
            vSpeed = 0;
        } else {
            vSpeed += gravity;
            setLocation(getX(), getY() + vSpeed);
        }
    }

    public boolean isOnGround(){
        return getOneObjectAtOffset(0, getImage().getHeight()/2 + 5, Block.class) != null;
    }

    public boolean isOnBlock(){
        return getOneObjectAtOffset(0, getImage().getHeight()/2 + 5, litblock.class) != null;
    }

    public boolean isOnFalseBlock(){
    Actor a = getOneObjectAtOffset(
        0,
        getImage().getHeight()/2 + 5,
        FalseBlock.class
    );

    if(a != null){
        FalseBlock fb = (FalseBlock) a;
        return fb.isActive(); // hanya true jika block masih aman
    }
    return false;
}


    

    // RESET KETIKA MENYENTUH GROUND
    public void checkGround(){
        if(isTouching(Ground.class)){
            setLocation(spawnX, spawnY);
            nyawa = nyawa - 1;
            vSpeed = 0;
        }
    }
}
