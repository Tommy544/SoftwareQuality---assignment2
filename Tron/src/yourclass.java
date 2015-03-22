
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class yourclass extends Core implements KeyListener, MouseListener,
        MouseMotionListener {

    private final List<Player> players = new ArrayList<>();
    private final Player player1 = new Player(new Position2D(40, 40), DirectionEnum.RIGHT, Color.yellow, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
    private final Player player2 = new Player(new Position2D(300, 300), DirectionEnum.RIGHT, Color.blue, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
    private final Player player3 = new Player(new Position2D(150, 150), DirectionEnum.DOWN, Color.red, 0, 0, 0, 0);

    private final int moveAmount = 5;

    @Override
    public void init() {
        super.init();
        players.add(player1);
        players.add(player2);
        player3.mousecontroled = true;
        players.add(player3);
        Window w = sm.getFullScreenWindow();
        w.addKeyListener(this);
        w.addMouseListener(this);
        w.addMouseMotionListener(this);
    }

    public static void main(String[] args) {
        new yourclass().run();
    }

    @Override
    public void draw(Graphics2D g) {
        int xPos;
        int yPos;
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, sm.getWidth(), sm.getHeight());
        for (Player player : players) {
            switch (player.getCurrentDirection()) {
                case UP:
                    yPos = player.getPosition().getY();
                    if (yPos > 0) {
                        player.getPosition().setY(yPos - moveAmount);
                    } else {
                        player.getPosition().setY(sm.getHeight());
                    }
                    break;
                case RIGHT:
                    xPos = player.getPosition().getX();
                    if (xPos < sm.getWidth()) {
                        player.getPosition().setX(xPos + moveAmount);
                    } else {
                        player.getPosition().setX(0);
                    }
                    break;
                case DOWN:
                    yPos = player.getPosition().getY();
                    if (yPos < sm.getHeight()) {
                        player.getPosition().setY(yPos + moveAmount);
                    } else {
                        player.getPosition().setY(0);
                    }
                    break;
                case LEFT:
                    xPos = player.getPosition().getX();
                    if (xPos > 0) {
                        player.getPosition().setX(xPos - moveAmount);
                    } else {
                        player.getPosition().setX(sm.getWidth());
                    }
                    break;
            }

            player.getPath().add(new Position2D(player.getPosition()));

            g.setColor(player.getColor());
            for (int i = 0; i < player.getPath().size(); i++) {
                g.fillRect(player.getPath().get(i).getX(), player.getPath().get(i).getY(), 10, 10);
            }
        }
        checkCollisions();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            stop();
        }

        for (Player player : players) {
            if (e.getKeyCode() == player.getUpKey()) {
                if (player.getCurrentDirection() != DirectionEnum.DOWN) {
                    player.setCurrentDirection(DirectionEnum.UP);
                }
            } else if (e.getKeyCode() == player.getDownKey()) {
                if (player.getCurrentDirection() != DirectionEnum.UP) {
                    player.setCurrentDirection(DirectionEnum.DOWN);
                }
            } else if (e.getKeyCode() == player.getLeftKey()) {
                if (player.getCurrentDirection() != DirectionEnum.RIGHT) {
                    player.setCurrentDirection(DirectionEnum.LEFT);
                }
            } else if (e.getKeyCode() == player.getRightKey()) {
                if (player.getCurrentDirection() != DirectionEnum.LEFT) {
                    player.setCurrentDirection(DirectionEnum.RIGHT);
                }
            }
        }
    }

    public void checkCollisions() {
        for (Player player : players) {
            for (Player oponentPlayer : players) {
                for (int i = 0; i < oponentPlayer.getPath().size(); i++) {
                    if (player.equals(oponentPlayer) && i == (player.getPath().size() - 1)) {
                        continue;
                    }
                    if (player.getPosition().equals(oponentPlayer.getPath().get(i))) {
                        stop();
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }

    
    @Override
    public void mouseClicked(MouseEvent e) {

        for (Player player : players) {
            if (player.mousecontroled) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    player.setCurrentDirection(DirectionEnum.turnLeft(player.getCurrentDirection()));
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    player.setCurrentDirection(DirectionEnum.turnRight(player.getCurrentDirection()));
                }
            }
        }
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }

}
