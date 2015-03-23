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
public class Tron extends Core {

    private final int MOVE_AMOUNT = 5;
    private TronDataModel tronDataModel;

    /**
     * Initialization method that initializes internal data model class - TronDataModel
     * and registers players to game.
     * Overrides calls init() method of superclass Core.
     */
    @Override
    public void init() {
        super.init();

        tronDataModel = new TronDataModel(MOVE_AMOUNT, sm.getWidth(), sm.getHeight());

        // ========= To choose which players are to be included in-game, comment/uncomment appropriate lines below ===========
        tronDataModel.registerPlayer(1); // <-- Yellow player, up: UP; down: DOWN; left: LEFT; right: RIGHT;
        tronDataModel.registerPlayer(2); // <-- Blue player, up: W; down: S; left: A; right: D;
        tronDataModel.registerPlayer(3); // <-- Green player, up: I; down: K; left: J; right: L
        tronDataModel.registerPlayer(4); // <-- Red player, mouse controlls - turn left: Left Mouse Button; turn right Right Mouse Button
        // ===================================================================================================================
    }

    /**
     * Method used to graphically represent internal data model to provided Graphics2D object.
     * 
     * @param g Provided object for drawing
     */
    @Override
    public void draw(Graphics2D g) {
        fillBackground(g);
        tronDataModel.movePlayers();
        drawPlayerBikes(g);
        if (tronDataModel.checkCollisions()) {
            stop();
        }
    }

    /**
     * Helper method that fills entire screen with black color.
     * @param g Provided object for drawing
     */
    private void fillBackground(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, sm.getWidth(), sm.getHeight());
    }

    private void drawPlayerBikes(Graphics2D g) {
        List<Player> players = tronDataModel.getPlayers();
        for (Player player : players) {
            g.setColor(player.getColor());
            for (int i = 0; i < player.getPath().size(); i++) {
                g.fillRect(player.getPath().get(i).getX(), player.getPath().get(i).getY(), 10, 10);
            }
        }
    }

    /**
     * Main method of the game.
     * Creates instance of Tron and calls its run method.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        new Tron().run();
    }

    /**
     * Event handler for user's key input
     * 
     * @param e User's key input
     */
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        tronDataModel.changePlayerOrientation(e);
    }

    /**
     * Event handler for user's mouse input
     * 
     * @param e User's mouse input
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        tronDataModel.changePlayerOrientation(e);
    }

}
