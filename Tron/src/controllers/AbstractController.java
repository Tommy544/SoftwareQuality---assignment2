/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import tron.Direction;
import tron.Player;

/**
 * Abstract controller that defines basic interaction with ingame Player object.
 * All user defined controllers must extend this class.
 * 
 * @author Vladimir Caniga
 * @author Jakub Smolar
 */
public abstract class AbstractController {
    
    /**
     * The instance of Player this controller is associated to
     */
    protected Player associatedPlayer;
    
    /**
     * Associates Player instance with this controller.
     * This method must be called immediately after construction of the controller.
     * 
     * @param player Player instance this controller should be associated with
     */
    public void associatePlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player cannot be null.");
        }
        
        this.associatedPlayer = player;
    }
    
    /**
     * Processes new direction that was determined by controller and passes the direction
     * to the Player, to try to change his orientation.
     * 
     * @param direction New Player direction determined by the controller
     */
    public void processNewDirection(Direction direction) {
        associatedPlayer.tryChangeOrientation(direction);
    }
    
}
