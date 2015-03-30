package tron;

import controllers.AbstractController;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
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

    private Point position; // actual position on game map of the Player
    private Direction currentDirection; // actual direction of the Player
    private Color color; // color of the Player
    private List<Point> path = new ArrayList<>(); // path of the player's bike represented by all past Player positions
    private AbstractController controller; // controller associated to the Player
    
    /**
     * Simple constructor.
     * 
     * @param position The position on game map where Player should be spawned
     * @param currentDirection The direction that the player should be facing when spawned
     * @param color The color of the Player
     * @param controller The controller instance that is used to control this player
     */
    public Player(Point position, Direction currentDirection, Color color, AbstractController controller) {
        this.position = position;
        this.currentDirection = currentDirection;
        this.color = color;
        this.controller = controller;
        controller.associatePlayer(this);
    }

    /**
     * Method moves player on the game map in the direction he is heading and by
     * amount of pixels passed by "amount" parameter
     * 
     * @param amount The amount of pixels the the Player should move in one step
     * @param windowHeight Height of the game Window
     * @param windowWidth Width of the game Window
     */
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

    /**
     * Used for creation of the Player's path.
     * Appends current position of the Player to the end of his path.
     */
    public void appendCurrentPositionToPath() {
        path.add(new Point(position));
    }

    /**
     * Checks if Player at this moment collides with other players of himself.
     * 
     * @param otherPlayers Other players that are checked against this Player
     * @return true if there is a collision, false if not
     */
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

    /**
     * Checks if Player collides with a single other Player
     * 
     * @param otherPlayer Other player that is checked against this Player
     * @return true if there is a collision, false if not
     */
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

    /**
     * Checks if Player collides with himself
     * 
     * @return true if there is a collision, false if not
     */
    public boolean collidesWithSelf() {
        for (int i = 0; i < path.size() - 1; i++) {
            if (position.equals(path.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tries to change player's current direction to the direction passed as a parameter.
     * Current direction remains unchanged if the new direction is 180° from the current one.
     * 
     * @param direction Requested new direction
     */
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

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    
}
