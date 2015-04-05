/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

/**
 * Main class of th game.
 * Class takes care of graphical representation of the actual state of the game
 *
 * @author Vladimir Caniga
 * @author Jakub Smolar
 */
public class TronPresentation {

    private final int MOVE_AMOUNT = 5;
    private final int windowWidth;
    private final int windowHeight;
    
    public TronPresentation(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    /**
     * Method used to graphically represent internal data model to provided Graphics2D object.
     * 
     * @param g Provided object for drawing
     */
    public void draw(Graphics2D g, List<Player> players) {
        fillBackground(g);
        drawPlayerBikes(g, players);
    }

    /**
     * Helper method that fills entire screen with black color.
     * @param g Provided object for drawing
     */
    private void fillBackground(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, windowWidth, windowHeight);
    }

    private void drawPlayerBikes(Graphics2D g, List<Player> players) {
        for (Player player : players) {
            g.setColor(player.getColor());
            for (int i = 0; i < player.getPath().size(); i++) {
                g.fillRect(player.getPath().get(i).x, player.getPath().get(i).y, 10, 10);
            }
        }
    }

}
