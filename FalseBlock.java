import greenfoot.*;

public class FalseBlock extends Actor {
    private boolean active = true;
    private boolean isTrap = false;
    private int vanishTimer = 45; 

    public FalseBlock() {
        setSafeImage();
    }

    public void act() {
        if (isTrap) {
            vanishTimer--;
            if (vanishTimer <= 0) {
                active = false; 
                setDangerImage();
                if (vanishTimer <= -15) { 
                    getWorld().removeObject(this);
                }
            }
        }
    }

    public void setAsTrap() {
        this.isTrap = true;
    }

    public boolean isActive() { return active; }

    private void setSafeImage() {
        GreenfootImage img = new GreenfootImage("mainblock.png"); 
        img.scale(70, 15); // Tinggi tipis
        setImage(img);
    }

    private void setDangerImage() {
        GreenfootImage img = new GreenfootImage("falseblock.png"); 
        img.scale(70, 15);
        setImage(img);
    }
}
