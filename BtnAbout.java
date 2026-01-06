import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BtnAbout extends Buttons
{
    
    public BtnAbout(){
    setImage("button_abt.png");
    }
    
    /**
     * Act - do whatever the BtnAbout wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        // Add your action code here.
        if(Greenfoot.mouseClicked(this)){
            ((menu)getWorld()).stopped();
            World Start = getWorld();
            Start = new About();
            Greenfoot.setWorld(Start);
        }
    }
}
