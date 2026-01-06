import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ground extends Actor
{
    public Ground(){
        GreenfootImage tanah = new GreenfootImage("ground.png"); // gambar tanah misal ukuran 100x30
        tanah.scale(900, 30); // sesuaikan ukuran
        setImage(tanah);
    }
    
    public void act(){
        
    }
}
