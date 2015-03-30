/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents internal state of the game.
 * It has methods that directly change internal state.
 * It does not have any responsibilities for graphical data representation
 *
 * @author Vladimir Caniga
 * @author Jakub Smolar
 */
public class TronDataModel {
 
    /**
     * The list of all players that are in the game
     */
    private final List<Player> players = new ArrayList<>();  
    
    private final int moveAmount;
    private final int windowWidth;
    private final int windowHeight;

    
    /**
     * Simple constructor
     * 
     * @param moveAmount Speed of bike movement
     * @param windowWidth Width of the game window
     * @param windowHeight Height of the game window
     */
    public TronDataModel(int moveAmount, int windowWidth, int windowHeight) {
        this.moveAmount = moveAmount;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }
    
    /**
     * Adds the player passed as a parameter to the list of ingame players
     * 
     * @param player 
     */
    public void registerPlayer(Player player) {
        players.add(player);
    }
    
    /**
     * Internally moves all players by one step to their appropriate direction.
     */
    public void movePlayers() {
        for (Player player : players) {         
            player.moveBy(moveAmount, windowHeight, windowWidth);
        }
    }
    
    /**
     * Checks all players for collision with himself or with other player.
     * 
     * @return true if there was a collision, false if not
     */
    public boolean checkCollisions() {
        for (Player player : players) {
            if (player.collidesWithOtherPlayers(players)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Simple getter.
     * 
     * @return List of all players that are registered in game
     */
    public List<Player> getPlayers() {
        return players;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }
    
    
    
}
