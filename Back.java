import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Back here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Back extends Buttons
{
    public Back(){
        GreenfootImage btn = new GreenfootImage("back.png");
        
        // Agar tetap bulat, gunakan angka yang sama (misal 80x80)
        // Jika ingin agak besar, coba 100x100
        btn.scale(80, 80); 
        
        // WAJIB: Pasang gambar yang sudah di-scale ke Actor
        setImage(btn);
    }
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this)){
            ((About)getWorld()).stopped();
            World Start = getWorld();
            Start = new menu();
            Greenfoot.setWorld(Start);
        }
    }
}
