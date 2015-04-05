/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import tron.Direction;
import tron.Player;

/**
 * Controller that handles Mouse inputs
 * 
 * @author Vladimir Caniga
 * @author Jakub Smolar
 */
public class MouseController extends AbstractController implements MouseListener {
    
    private int turnLeftKey;
    private int turnRightKey;
    
    /**
     * Simple constructor.
     * 
     * @param turnLeftKey Mouse Button code associated to turnLeft action
     * @param turnRightKey Mouse Button code associated to turnRight action
     */
    public MouseController(Player player, int turnLeftKey, int turnRightKey) {
        super(player);
        this.turnLeftKey = turnLeftKey;
        this.turnRightKey = turnRightKey;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Key press event that determines the next direction and calls AbstractController's
     * method to process new direction.
     * 
     * @param e 
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == turnLeftKey) {
            processNewDirection(Direction.leftOf(associatedPlayer.getCurrentDirection()));
        } else if (e.getButton() == turnRightKey) {
            processNewDirection(Direction.rightOf(associatedPlayer.getCurrentDirection()));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
}
