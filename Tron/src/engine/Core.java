package engine;

import controllers.AbstractController;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class Core {

    private static final DisplayMode modes[] = {
        //new DisplayMode(1920,1080,32,0),
        //new DisplayMode(1680,1050,32,0),
        //new DisplayMode(1280,1024,32,0),
        new DisplayMode(800, 600, 32, 0),
        new DisplayMode(800, 600, 24, 0),
        new DisplayMode(800, 600, 16, 0),
        new DisplayMode(640, 480, 32, 0),
        new DisplayMode(640, 480, 24, 0),
        new DisplayMode(640, 480, 16, 0),};
    private boolean running;
    protected ScreenManager sm;
    protected Window w;
    private DisplayMode dm;
    
    private Game game;
    
    public Core(Game game) {
        this.game = game;
    }

    public void stop() {
        running = false;
    }

    public void run() {
        try {
            if (!running) {
                init();
            }
            gameLoop();
        } finally {
            sm.restoreScreen();
        }
    }

    public void init() {
        sm = new ScreenManager();
        dm = sm.findFirstCompatibaleMode(modes);
        sm.setFullScreen(dm);
        w = sm.getFullScreenWindow();
        w.setFont(new Font("Arial", Font.PLAIN, 20));
        w.setBackground(Color.WHITE);
        w.setForeground(Color.RED);
        w.setCursor(w.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));
        addBasicKeyControlls();
        running = true;
    }

    public void gameLoop() {
        long startTime = System.currentTimeMillis();
        long cumTime = startTime;
        BufferedImage bImg = new BufferedImage(dm.getWidth(), dm.getHeight(), BufferedImage.TYPE_INT_ARGB);

        while (running) {
            long timePassed = System.currentTimeMillis() - cumTime;
            cumTime += timePassed;
            update(timePassed);
            Graphics2D g = sm.getGraphics();
            game.drawNextScene(g);
            bImg.flush();
            g.drawImage(bImg, null, 0, 0);
            g.dispose();
            sm.update();

            try {
                Thread.sleep(20);
            } catch (Exception ex) {
            }
        }
    }

    public void update(long timePassed) {
    }
    
    public void registerNewController(AbstractController controller) {
        if (controller instanceof KeyListener) {
            w.addKeyListener((KeyListener) controller);
        } else if (controller instanceof MouseListener) {
            w.addMouseListener((MouseListener) controller);
        } else {
            throw new IllegalArgumentException("This engine currently dow not support that type of input device.");
        }
    }

    public ScreenManager getSm() {
        return sm;
    }

    private void addBasicKeyControlls() {
        w.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    stop();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
