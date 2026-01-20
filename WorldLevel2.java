import greenfoot.*;

public class WorldLevel2 extends World {

    private final int REQUIRED_COIN = 3;

    public WorldLevel2() {
        super(960, 540, 1);
        setBackground(new GreenfootImage("level2.jpg"));
        prepare();
        updateCoinUI();
    }

    /* ================= UI ================= */

    public void updateCoinUI() {
        int sisa = getObjects(Coin.class).size();
        showText("Koin: " + (REQUIRED_COIN - sisa) + " / " + REQUIRED_COIN, 100, 50);
    }

    /* ================= FINISH ================= */

    public void finishReached() {
        if (getObjects(Coin.class).isEmpty()) {
            showText("SELAMAT! ANDA MENANG!", getWidth()/2, getHeight()/2);
            Greenfoot.stop();
        } else {
            showText("AMBIL SEMUA KOIN!", getWidth()/2, getHeight()/2);
            Greenfoot.stop();
        }
    }

    /* ================= PREPARE ================= */

    private void prepare() {

        // ===== PLAYER =====
        addObject(new Player(), 80, 430);

        // ===== PLATFORM BAWAH =====
        addObject(new Block(), 80, 500);
        addObject(new Block(), 260, 470);
        addObject(new Block(), 440, 500);

        // ===== PLATFORM MENENGAH =====
        addObject(new Block(), 150, 360);
        addObject(new Block(), 350, 320);
        addObject(new Block(), 560, 350);

        // ===== FALSE BLOCK =====
        addObject(new FalseBlock(), 240, 260);
        addObject(new FalseBlock(), 420, 230);
        addObject(new FalseBlock(), 600, 260);

        // ===== PLATFORM ATAS =====
        addObject(new Block(), 700, 200);
        addObject(new Block(), 840, 150);

        // ===== FINISH =====
        Block finishBase = new Block();
        addObject(finishBase, 790, 500);

        FinishBlock finish = new FinishBlock();
        addObject(
            finish,
            finishBase.getX(),
            finishBase.getY()
            - (finishBase.getImage().getHeight() / 2)
            - (finish.getImage().getHeight() / 2)
        );

        // ===== COIN =====
        addObject(new Coin(), 250, 200);
        addObject(new Coin(), 420, 410);
        addObject(new Coin(), 790, 80);

        // ===== LAVA =====
        addObject(new Ground(), 550, 600);
    }
}
