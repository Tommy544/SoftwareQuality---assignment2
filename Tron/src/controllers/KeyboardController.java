/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tron.Direction;

/**
 *
 * @author vcaniga
 */
public class KeyboardController extends AbstractController implements KeyListener {
    
    private int upKey;
    private int downKey;
    private int leftKey;
    private int rightkey;
    
    public KeyboardController(int upKey, int downKey, int leftKey, int rightKey) {
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightkey = rightKey;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
       int keyCode = e.getKeyCode();
       
       if (keyCode == upKey) {
           processNewDirection(Direction.UP);
       } else if (keyCode == downKey) {
           processNewDirection(Direction.DOWN);
       } else if (keyCode == leftKey) {
           processNewDirection(Direction.LEFT);
       } else if (keyCode == rightkey) {
           processNewDirection(Direction.RIGHT);
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public int getUpKey() {
        return upKey;
    }
    
    
    
}
