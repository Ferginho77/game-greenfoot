import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Coin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Coin extends Actor
{
    public Coin() {
        GreenfootImage img = new GreenfootImage(25, 25);
        img.setColor(Color.YELLOW);
        img.fillOval(0, 0, 25, 25);
        setImage(img);
    }
    public void act()
    {
        // Add your action code here.
    }
}
