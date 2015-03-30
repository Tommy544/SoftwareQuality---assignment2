package tron;

import controllers.AbstractController;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.naming.directory.DirContext;
import static tron.Direction.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Class represents a single player in the game - his bike. Defines attributes
 * for all properties of the player.
 *
 * @author Vladimir Caniga
 * @author Jakub Smolar
 */
public class Player {

    private Point position;
    private Direction currentDirection;
    private Color color;
    private List<Point> path = new ArrayList<>();
    private int upKey;
    private int downKey;
    private int leftKey;
    private int rightKey;
    private AbstractController controller;
    boolean mousecontroled = false;

    /**
     * Simple constructor.
     *
     * @param position Position of the player on the game map
     * @param currentDirection Current direction the player is facing
     * @param color The color of the player bike
     * @param up Key code for changing direction to UP
     * @param down Key code for changing direction to DOWN
     * @param left Key code for changing direction to LEFT
     * @param right Key code for changing direction to RIGHT
     */
//    public Player(Point position, Direction currentDirection, Color color, int up, int down, int left, int right) {
//        this.position = position;
//        this.currentDirection = currentDirection;
//        this.color = color;
//        this.upKey = up;
//        this.downKey = down;
//        this.leftKey = left;
//        this.rightKey = right;
//    }
    
    public Player(Point position, Direction currentDirection, Color color, AbstractController controller) {
        this.position = position;
        this.currentDirection = currentDirection;
        this.color = color;
        this.controller = controller;
        controller.associatePlayer(this);
    }

    public void moveBy(int amount, int windowHeight, int windowWidth) {
        switch (currentDirection) {
            case UP:
                if (position.y > 0) {
                    position.y -= amount;
                } else {
                    position.y = windowHeight;
                }
                break;
            case RIGHT:
                if (position.x < windowWidth) {
                    position.x += amount;
                } else {
                    position.x = 0;
                }
                break;
            case DOWN:
                if (position.y < windowHeight) {
                    position.y += amount;
                } else {
                    position.y = 0;
                }
                break;
            case LEFT:
                if (position.x > 0) {
                    position.x -= amount;
                } else {
                    position.x = windowWidth;
                }
                break;
        }

        appendCurrentPositionToPath();
    }

    public void appendCurrentPositionToPath() {
        path.add(new Point(position));
    }

    public boolean collidesWithOtherPlayers(List<Player> otherPlayers) {
        if (otherPlayers == null) {
            throw new IllegalArgumentException("otherPlayers cannot be null.");
        }

        for (Player otherPlayer : otherPlayers) {
            if (this.equals(otherPlayer)) {
                if (collidesWithSelf()) {
                    return true;
                }
            } else if (collidesWithOtherPlayer(otherPlayer)) {
                return true;
            }
        }
        return false;
    }

    public boolean collidesWithOtherPlayer(Player otherPlayer) {
        if (otherPlayer == null) {
            throw new NullPointerException("otherPlayer cannot be null.");
        }
        if (this.equals(otherPlayer)) {
            throw new IllegalArgumentException("This method cannot take this instance as a parameter.");
        }

        for (int i = 0; i < otherPlayer.getPath().size(); i++) {
            if (position.equals(otherPlayer.getPath().get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean collidesWithSelf() {
        for (int i = 0; i < path.size() - 1; i++) {
            if (position.equals(path.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void tryChangeOrientation(Direction direction) {

        if (direction == UP && currentDirection != DOWN) {
            currentDirection = direction;
        } else if (direction == DOWN && currentDirection != Direction.UP) {
            currentDirection = DOWN;
        } else if (direction == LEFT && currentDirection != RIGHT) {
            currentDirection = LEFT;
        } else if (direction == RIGHT && currentDirection != LEFT) {
            currentDirection = RIGHT;
        }

    }

    public List<Point> getPath() {
        return path;
    }

    public Color getColor() {
        return color;
    }

    
}
