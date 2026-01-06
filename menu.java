import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class menu extends World
{
    public menu()
    {    
        super(960, 540, 1); 
        setBackground("home.png");
        
        // WAJIB: Panggil method prepare agar tombol muncul
        prepare(); 
    }
    
    private void prepare(){
        Start start = new Start();
        addObject(start, 485, 260);
        
        // Pastikan nama class Button About kamu sesuai (BtnAbout atau About?)
        BtnAbout about = new BtnAbout(); 
        addObject(about, 493, 387);
    }
}
