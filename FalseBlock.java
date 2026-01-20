import greenfoot.*;
import java.util.List;

public class FalseBlock extends Actor {

    private static final int FALL_DELAY = 60;
    private static final int FALL_SPEED = 6;
    private static final int CHAIN_RADIUS = 120; // jarak antar false block

    private int standTimer = 0;
    private boolean falling = false;

    public FalseBlock() {
        GreenfootImage img = new GreenfootImage("litblock.png");
        img.scale(70, 15);
        setImage(img);
    }

    public void act() {
        checkStanding();
        fallDown();
    }

    private void checkStanding() {
        if (falling) return;

        Player p = (Player) getOneObjectAtOffset(
            0,
            -getImage().getHeight() / 2 - 1,
            Player.class
        );

        if (p != null) {
            standTimer++;
            if (standTimer >= FALL_DELAY) {
                startFalling();
            }
        } else {
            standTimer = 0;
        }
    }

    // =================== CHAIN FALL ===================

    private void startFalling() {
        falling = true;

        // cari false block lain di sekitar
        List<FalseBlock> blocks = getObjectsInRange(CHAIN_RADIUS, FalseBlock.class);

        for (FalseBlock b : blocks) {
            b.forceFall();
        }
    }

    public void forceFall() {
        falling = true;
    }

    // =================== FALL ===================

    private void fallDown() {
        if (falling) {
            setLocation(getX(), getY() + FALL_SPEED);

            if (getY() > getWorld().getHeight()) {
                getWorld().removeObject(this);
            }
        }
    }
}
