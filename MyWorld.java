import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        // Sesuaikan ukuran dunia dengan ukuran gambar kamu
    super(960, 540, 1); 
    
    // Membuat objek gambar GreenfootImage
    GreenfootImage bg = new GreenfootImage("lava.jpg");
    
    // Opsional: Memaksa gambar agar ukurannya pas dengan ukuran World
    bg.scale(getWidth(), getHeight());
    
    // Memasang gambar ke background
    setBackground(bg);
    prepare();
    }
    
    public void prepare(){
    Player player = new Player();
    addObject(player, 40, 415);
    
    //Block Bawah
    addObject(new Block(),48,493);
    addObject(new litblock(), 194, 443);
    addObject(new litblock(), 294, 393);
    addObject(new litblock(), 394, 443);
    addObject(new litblock(), 494, 393);
    addObject(new litblock(), 594, 443);
    addObject(new litblock(), 694, 393);
    addObject(new litblock(), 794, 443);
    //Block Atas
    addObject(new litblock(), 198, 303);
    addObject(new litblock(), 374, 303);
    addObject(new litblock(), 594, 303);
    addObject(new litblock(), 794, 303);
    
    //Ground Lava
    addObject(new Ground(), 548, 592);
    }
}
