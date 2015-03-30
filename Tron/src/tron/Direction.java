package tron;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Enum representing all possible bike directions.
 *
 * @author Vladimir Caniga
 * @author Jakub Smolar
 */
public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;
    
    /**
     * Static method used for determining next direction after right turn.
     * 
     * @param direction Original direction
     * @return Direction after the turn
     */
    public static Direction rightOf(Direction direction) {
        switch (direction) {
            case UP:
                return RIGHT;
            case RIGHT:
                return DOWN;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            default:
                return null;
        }
    }
    
    /**
     * Static method used for determining next direction after left turn.
     * 
     * @param direction Original direction
     * @return Direction after the turn
     */
    public static Direction leftOf(Direction direction) {
        switch (direction) {
            case UP:
                return LEFT;
            case RIGHT:
                return UP;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            default:
                return null;
        }
    }
}
