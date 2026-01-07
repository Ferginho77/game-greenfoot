import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FalseBlock extends Assets
{
     private boolean active = true;

    public FalseBlock(){
        setSafeImage();
    }

    public void setActive(boolean value){
        active = value;

        if(active){
            setSafeImage();
        } else {
            setDangerImage();
        }
    }

    public boolean isActive(){
        return active;
    }

    private void setSafeImage(){
        GreenfootImage img = new GreenfootImage("mainblock.png"); // block normal
        img.scale(60, 20);
        setImage(img);
    }

    private void setDangerImage(){
        GreenfootImage img = new GreenfootImage("falseblock.png"); // block lava
        img.scale(60, 20);
        setImage(img);
    }}
