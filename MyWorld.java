import greenfoot.*;
import java.util.List;
import java.util.Collections;

public class MyWorld extends World {
    private int timer = 300; 
    private int ronde = 1;
    private boolean eventTriggered = false;

    public MyWorld() {    
        super(960, 540, 1);
        prepare();
    }

    public void act() {
        if (ronde <= 2) {
            runRound();
        } else {
            showText("SELAMAT! ANDA MENANG!", getWidth()/2, getHeight()/2);
            Greenfoot.stop();
        }
    }

    private void runRound() {
        if (timer > 0) {
            timer--;
            showText("Ronde: " + ronde, 100, 80);
            showText("Waktu: " + (timer/60), getWidth()/2, 50);
        } else if (!eventTriggered) {
            deactivateTwoBlocks();
            eventTriggered = true;
            timer = -100;
        }

        if (eventTriggered && timer < -90) {
            if (ronde < 2) {
                ronde++;
                timer = 300; // Reset waktu untuk ronde 2
                eventTriggered = false;
            } else {
                ronde = 3; 
            }
        }
    }

    private void deactivateTwoBlocks() {
        List<FalseBlock> blocks = getObjects(FalseBlock.class);
        // Mengacak urutan list agar blok yang diambil acak
        Collections.shuffle(blocks);
        
        int count = 0;
        for (FalseBlock b : blocks) {
            if (count < 4) { 
                b.setAsTrap();
                count++;
            }
        }
    }

    private void prepare() {
        Player player = new Player();
        addObject(player, 50, 350);
        addObject(new Block(), 50, 493);

        for (int i = 0; i < 11; i++) {
            addObject(new FalseBlock(), 150 + (i * 75), 400);
        }
        addObject(new Ground(), 548, 590);
    }
}
