/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

import engine.Position2D;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vcaniga
 */
public class TronDataModel {
 
    private final List<Player> players = new ArrayList<>();
    private final Player player1 = new Player(new Position2D(40, 40), DirectionEnum.RIGHT, Color.yellow, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
    private final Player player2 = new Player(new Position2D(300, 300), DirectionEnum.RIGHT, Color.blue, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
    private final Player player3 = new Player(new Position2D(500, 500), DirectionEnum.DOWN, Color.green, KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L);
    private final Player player4 = new Player(new Position2D(150, 150), DirectionEnum.DOWN, Color.red, 0, 0, 0, 0);
    
    private final int moveAmount;
    private final int windowWidth;
    private final int windowHeight;

    
    public TronDataModel(int moveAmount, int windowWidth, int windowHeight) {
        this.moveAmount = moveAmount;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }
    
    public void registerPlayer(int player) {
        switch (player) {
            case 1:
                players.add(player1);
                break;
            case 2:
                players.add(player2);
                break;
            case 3:
                players.add(player3);
                break;
            case 4:
                player4.setMousecontroled(true);
                players.add(player4);
                break;
            default:
                break;
        }
    }
    
    public void movePlayers() {
        int xPos;
        int yPos;
        
        for (Player player : players) {
            switch (player.getCurrentDirection()) {
                case UP:
                    yPos = player.getPosition().getY();
                    if (yPos > 0) {
                        player.getPosition().setY(yPos - moveAmount);
                    } else {
                        player.getPosition().setY(windowHeight);
                    }
                    break;
                case RIGHT:
                    xPos = player.getPosition().getX();
                    if (xPos < windowWidth) {
                        player.getPosition().setX(xPos + moveAmount);
                    } else {
                        player.getPosition().setX(0);
                    }
                    break;
                case DOWN:
                    yPos = player.getPosition().getY();
                    if (yPos < windowHeight) {
                        player.getPosition().setY(yPos + moveAmount);
                    } else {
                        player.getPosition().setY(0);
                    }
                    break;
                case LEFT:
                    xPos = player.getPosition().getX();
                    if (xPos > 0) {
                        player.getPosition().setX(xPos - moveAmount);
                    } else {
                        player.getPosition().setX(windowWidth);
                    }
                    break;
            }

            player.getPath().add(new Position2D(player.getPosition()));
        }
    }
    
    public void changePlayerOrientation(KeyEvent e) {
        for (Player player : players) {
            if (e.getKeyCode() == player.getUpKey()) {
                if (player.getCurrentDirection() != DirectionEnum.DOWN) {
                    player.setCurrentDirection(DirectionEnum.UP);
                }
            } else if (e.getKeyCode() == player.getDownKey()) {
                if (player.getCurrentDirection() != DirectionEnum.UP) {
                    player.setCurrentDirection(DirectionEnum.DOWN);
                }
            } else if (e.getKeyCode() == player.getLeftKey()) {
                if (player.getCurrentDirection() != DirectionEnum.RIGHT) {
                    player.setCurrentDirection(DirectionEnum.LEFT);
                }
            } else if (e.getKeyCode() == player.getRightKey()) {
                if (player.getCurrentDirection() != DirectionEnum.LEFT) {
                    player.setCurrentDirection(DirectionEnum.RIGHT);
                }
            }
        }
    }
    
    public void changePlayerOrientation(MouseEvent e) {
        for (Player player : players) {
            if (player.mousecontroled) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    player.setCurrentDirection(DirectionEnum.turnLeft(player.getCurrentDirection()));
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    player.setCurrentDirection(DirectionEnum.turnRight(player.getCurrentDirection()));
                }
            }
        }
    }
    
    public boolean checkCollisions() {
        for (Player player : players) {
            for (Player oponentPlayer : players) {
                for (int i = 0; i < oponentPlayer.getPath().size(); i++) {
                    if (player.equals(oponentPlayer) && i == (player.getPath().size() - 1)) {
                        continue;
                    }
                    if (player.getPosition().equals(oponentPlayer.getPath().get(i))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<Player> getPlayers() {
        return players;
    }
    
    
}
