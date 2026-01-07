import greenfoot.*;  
import java.util.List;// (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    
    private int timer = 600;
    private int lavaEventCount = 0;
    private final int MAX_EVENT = 2;
    private boolean gameOver = false;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
    super(960, 540, 1); 
    GreenfootImage bg = new GreenfootImage("lava.jpg");
    bg.scale(getWidth(), getHeight());
    setBackground(bg);
    prepare();
    }
    
    public void act(){
        timer --;
    
        showText("Time : " + (timer / 60), 400, 50);

        if(timer <= 0){
            deactivateRandomBlocks();
             lavaEventCount++;

            if(lavaEventCount >= MAX_EVENT){
                playerWin();
            } else {
                timer = 600; // lanjut ke ronde berikutnya
            }
        }

        }
        
        private void playerWin(){
        gameOver = true;
        showText("YOU WIN!", getWidth()/2, getHeight()/2);
        Greenfoot.stop();
    }
    


private void deactivateRandomBlocks(){
    List<FalseBlock> blocks = getObjects(FalseBlock.class);

    for(FalseBlock b : blocks){
        if(Greenfoot.getRandomNumber(2) == 0){
            b.setActive(false);
        }
    }
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
    
    
    
    // Block bawah
    addObject(new Block(),48,493);
    addObject(new FalseBlock(), 194, 443);
    addObject(new FalseBlock(), 294, 393);
    addObject(new FalseBlock(), 394, 443);
    addObject(new FalseBlock(), 494, 393);
    addObject(new FalseBlock(), 594, 443);
    addObject(new FalseBlock(), 694, 393);
    addObject(new FalseBlock(), 794, 443);

    // Block atas
    addObject(new FalseBlock(), 198, 303);
    addObject(new FalseBlock(), 374, 303);
    addObject(new FalseBlock(), 594, 303);
    addObject(new FalseBlock(), 794, 303);
    
    //Ground Lava
    addObject(new Ground(), 548, 592);
    }
}
