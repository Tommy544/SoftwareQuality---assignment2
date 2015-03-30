/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import tron.Direction;
import tron.Player;

/**
 *
 * @author vcaniga
 */
public abstract class AbstractController {
    
    protected Player associatedPlayer;
    
    public void associatePlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("player cannot be null.");
        }
        
        this.associatedPlayer = player;
    }
    
    public void processNewDirection(Direction direction) {
        associatedPlayer.tryChangeOrientation(direction);
    }
    
}
