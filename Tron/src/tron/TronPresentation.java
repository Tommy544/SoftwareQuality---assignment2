/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

import engine.Core;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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

    /**
     * Initialization method that initializes internal data model class - TronDataModel
     * and registers players to game.
     * Overrides calls init() method of superclass Core.
     */
//    @Override
//    public void init() {
//        super.init();
//
//        tronDataModel = new TronDataModel(MOVE_AMOUNT, sm.getWidth(), sm.getHeight());
//
//        // ========= To choose which players are to be included in-game, comment/uncomment appropriate lines below ===========
//        tronDataModel.registerPlayer(1, w); // <-- Yellow player, up: UP; down: DOWN; left: LEFT; right: RIGHT;
//        tronDataModel.registerPlayer(2, w); // <-- Blue player, up: W; down: S; left: A; right: D;
//        tronDataModel.registerPlayer(3, w); // <-- Green player, up: I; down: K; left: J; right: L
//        //tronDataModel.registerPlayer(4, w); // <-- Red player, mouse controlls - turn left: Left Mouse Button; turn right Right Mouse Button
//        // ===================================================================================================================
//    }
    
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

    /**
     * Main method of the game.
     * Creates instance of TronPresentation and calls its run method.
     * 
     * @param args 
     */
//    public static void main(String[] args) {
//        new TronPresentation().run();
//    }

    /**
     * Event handler for user's key input
     * 
     * @param e User's key input
     */
//    @Override
//    public void keyPressed(KeyEvent e) {
//        super.keyPressed(e);
//        tronDataModel.changePlayerOrientation(e);
//    }
//
//    /**
//     * Event handler for user's mouse input
//     * 
//     * @param e User's mouse input
//     */
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        tronDataModel.changePlayerOrientation(e);
//    }

}
