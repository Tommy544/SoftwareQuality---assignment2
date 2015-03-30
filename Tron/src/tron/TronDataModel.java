/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

import controllers.AbstractController;
import controllers.KeyboardController;
import java.awt.Color;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Class represents internal state of the game.
 * Class has methods that directly change internal state.
 * Class does not have any responsibilities for graphical data representation
 *
 * @author Vladimir Caniga
 * @author Jakub Smolar
 */
public class TronDataModel {
 
    private final List<Player> players = new ArrayList<>();
//    private final Player player1 = new Player(new Point(40, 40), Direction.RIGHT, Color.yellow, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
//    private final Player player2 = new Player(new Point(300, 300), Direction.RIGHT, Color.blue, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
//    private final Player player3 = new Player(new Point(500, 500), Direction.DOWN, Color.green, KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L);
//    private final Player player4 = new Player(new Point(150, 150), Direction.DOWN, Color.red, 0, 0, 0, 0);
    
    
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
     * Registers predefined player to the game.
     * Only registered players will be able to play and will be drawn on the screen.
     * 
     * @param player 
     */
    public void registerPlayer(int player, Window w) {
        KeyboardController player1Controller = new KeyboardController(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        w.addKeyListener(player1Controller);
        Player player1 = new Player(new Point(40, 40), Direction.RIGHT, Color.yellow, player1Controller);
        KeyboardController player2Controller = new KeyboardController(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
        w.addKeyListener(player2Controller);
        Player player2 = new Player(new Point(300, 300), Direction.RIGHT, Color.blue, player2Controller);
       //Player player3 = new Player(new Point(500, 500), Direction.DOWN, Color.green, new KeyboardController(KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L));
    
        
        switch (player) {
            case 1:
                players.add(player1);
                break;
            case 2:
                players.add(player2);
                break;
//            case 3:
//                players.add(player3);
//                break;
//            case 4:
//                player4.setMousecontroled(true);
//                players.add(player4);
//                break;
            default:
                break;
        }
    }
    
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
    
//    /**
//     * Tries to change players orientation based on user's key input.
//     * 
//     * @param e User's key input
//     */
//    public void changePlayerOrientation(KeyEvent e) {
//        for (Player player : players) {
//            if (e.getKeyCode() == player.getUpKey()) {
//                if (player.getCurrentDirection() != Direction.DOWN) {
//                    player.setCurrentDirection(Direction.UP);
//                }
//            } else if (e.getKeyCode() == player.getDownKey()) {
//                if (player.getCurrentDirection() != Direction.UP) {
//                    player.setCurrentDirection(Direction.DOWN);
//                }
//            } else if (e.getKeyCode() == player.getLeftKey()) {
//                if (player.getCurrentDirection() != Direction.RIGHT) {
//                    player.setCurrentDirection(Direction.LEFT);
//                }
//            } else if (e.getKeyCode() == player.getRightKey()) {
//                if (player.getCurrentDirection() != Direction.LEFT) {
//                    player.setCurrentDirection(Direction.RIGHT);
//                }
//            }
//        }
//    }
//    
//    /**
//     * Tries to change players orientation based on user's mouse input.
//     * 
//     * @param e User's mouse input
//     */
//    public void changePlayerOrientation(MouseEvent e) {
//        for (Player player : players) {
//            if (player.mousecontroled) {
//                if (e.getButton() == MouseEvent.BUTTON1) {
//                    player.setCurrentDirection(Direction.leftOf(player.getCurrentDirection()));
//                } else if (e.getButton() == MouseEvent.BUTTON3) {
//                    player.setCurrentDirection(Direction.rightOf(player.getCurrentDirection()));
//                }
//            }
//        }
//    }
    
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
