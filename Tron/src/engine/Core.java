package engine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public abstract class Core implements KeyListener, MouseListener,
        MouseMotionListener {

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
    private DisplayMode dm;
    private Animation animation;

    public void stop() {
        running = false;
    }

    public void run() {
        try {
            init();
            gameLoop();
        } finally {
            sm.restoreScreen();
        }
    }

    public void init() {
        animation = new Animation();
        sm = new ScreenManager();
        dm = sm.findFirstCompatibaleMode(modes);
        sm.setFullScreen(dm);
        Window w = sm.getFullScreenWindow();
        w.setFont(new Font("Arial", Font.PLAIN, 20));
        w.setBackground(Color.WHITE);
        w.setForeground(Color.RED);
        w.setCursor(w.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));
        
        w.addKeyListener(this);
        w.addMouseListener(this);
        w.addMouseMotionListener(this);
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
            draw(g);
            bImg.flush();
            g.drawImage(bImg, null, 0, 0);
            animation.addScene(bImg, cumTime);
            g.dispose();
            sm.update();

            try {
                Thread.sleep(20);
            } catch (Exception ex) {
            }
        }
    }

    public void update(long timePassed) {
        animation.update(timePassed);
    }

    public abstract void draw(Graphics2D g);

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

}
