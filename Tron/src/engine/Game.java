/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.awt.Graphics2D;

/**
 * Interface that represents single required method that needs to be implemented
 * by a class that is then recognizable by engine as a Game
 * 
 * @author Vladimir Caniga
 * @author Jakub Smolar
 */
public interface Game {
    
    /**
     * A method that is called by the engine and expects client game to render 
     * next frame.
     * 
     * @param g 
     */
    public void drawNextScene(Graphics2D g);
}
