/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.MouseInputListener;
import tron.Direction;

/**
 * Controller
 * 
 * @author vcaniga
 */
public class MouseController extends AbstractController implements MouseListener {
    
    private int turnLeftKey;
    private int turnRightKey;
    
    public MouseController(int turnLeftKey, int turnRightKey) {
        this.turnLeftKey = turnLeftKey;
        this.turnRightKey = turnRightKey;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

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
