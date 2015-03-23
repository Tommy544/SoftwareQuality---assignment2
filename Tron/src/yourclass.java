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
	
        List<Player> players = new ArrayList<Player>();
        Player vladoChuj = new Player(1,40, 40, 1, Color.yellow, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);         
        Player vekyPanKubo = new Player(2,300, 300, 1, Color.blue, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D); 
        Player smradlavPeto = new Player(3, 150, 150, 3, Color.red, 0, 0, 0, 0);
        
	int moveAmount = 5;

	public void init() {
		super.init();
                players.add(vladoChuj);
                players.add(vekyPanKubo);
                smradlavPeto.mousecontroled=true;
                players.add(smradlavPeto);
		Window w = sm.getFullScreenWindow();
		w.addKeyListener(this);
		w.addMouseListener(this);
		w.addMouseMotionListener(this);
	}

	public static void main(String[] args) {
		new yourclass().run();
	}

	public void draw(Graphics2D g) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, sm.getWidth(), sm.getHeight());
            for(Player tmp : players){
		switch(tmp.currentDirection){
		case 0:
			if (tmp.getCentrey()>0){
                            tmp.centrey-=moveAmount;
                            } else {
                                tmp.centrey = sm.getHeight();
                            }
			break;
		case 1:
			if (tmp.centrex < sm.getWidth()){
                            tmp.centrex += moveAmount;
                            } else {
                                tmp.centrex = 0;
                            }
			break;
		case 2:
			if (tmp.centrey < sm.getHeight()){
                            tmp.centrey += moveAmount;
                            } else {
                                tmp.centrey = 0;
                            }
			break;
		case 3:
			if (tmp.getCentrex()>0){
                            tmp.centrex -= moveAmount;
                            } else {
                                tmp.centrex = sm.getWidth();
                            }
			break;
		}		                

                tmp.pathx.add(tmp.getCentrex());
                tmp.pathy.add(tmp.centrey);
         
                for (int i = 0; i < tmp.pathx.size(); i++){
                    g.setColor(tmp.color);
                    g.fillRect(tmp.pathx.get(i), tmp.pathy.get(i), 10, 10);
                }
            }
            checkCollisions();
        }

	public void keyPressed(KeyEvent e) {
            
            for(Player tmp : players){
                if(e.getKeyCode() == tmp.up){
                    if (tmp.currentDirection != 2){
			tmp.currentDirection = 0;
                    }
                }else if(e.getKeyCode() == tmp.down){
                    if (tmp.currentDirection != 0){
			tmp.currentDirection = 2;
                    }    
                }else if(e.getKeyCode() == tmp.left){
                    if (tmp.currentDirection != 1){
			tmp.currentDirection = 3;
                    }                        
                }else if(e.getKeyCode() == tmp.right){
                    if (tmp.currentDirection != 3){
                        tmp.currentDirection = 1;
                    }                      
                }
            }
        }
        
        public void checkCollisions(){
            for(Player player : players){
                for(Player oponentPlayer : players){
                    for(int i=0; i < oponentPlayer.pathx.size(); i++){
                        if(player.equals(oponentPlayer) && i == (player.pathx.size()-1)){
                            continue;
                        }
                        if(player.centrex == oponentPlayer.pathx.get(i) && player.centrey == oponentPlayer.pathy.get(i)){
                            stop();
                        }
                    }
                }
            }
        }
    

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent arg0) {

	}

	public void mouseClicked(MouseEvent e) {
            
            for(Player tmp : players){
                if(tmp.mousecontroled){
                    if(e.getButton() == MouseEvent.BUTTON1){  
                        int direction = (tmp.currentDirection - 1) % 4;
                        tmp.currentDirection = direction < 0 ? direction + 4 : direction;
                        System.out.println(tmp.currentDirection);
                    }else if(e.getButton() == MouseEvent.BUTTON3){
                        int direction = (tmp.currentDirection + 1) % 4;
                        tmp.currentDirection = direction < 0 ? direction + 4 : direction;
                        System.out.println(tmp.currentDirection);
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
