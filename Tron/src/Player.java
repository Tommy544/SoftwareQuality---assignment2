
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jakub
 */
public class Player {
    private static long idGenerator = 0;
    
    private long id;
    private Position2D position;
    private DirectionEnum currentDirection;
    private Color color;
    private List<Position2D> path = new ArrayList<>();
    private int upKey;
    private int downKey;
    private int leftKey;
    private int rightKey;
    boolean mousecontroled = false;

    public Player(Position2D position, DirectionEnum currentDirection, Color color, int up, int down, int left, int right) {
        id = idGenerator++;
        this.position = position;
        this.currentDirection = currentDirection;
        this.color = color;
        this.upKey = up;
        this.downKey = down;
        this.leftKey = left;
        this.rightKey = right;
    }

    public Position2D getPosition() {
        return position;
    }

    public DirectionEnum getCurrentDirection() {
        return currentDirection;
    }

    public Color getColor() {
        return color;
    }

    public List<Position2D> getPath() {
        return path;
    }

    public int getUpKey() {
        return upKey;
    }

    public int getDownKey() {
        return downKey;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setUpKey(int upKey) {
        this.upKey = upKey;
    }

    public void setDownKey(int downKey) {
        this.downKey = downKey;
    }

    public static long getIdGenerator() {
        return idGenerator;
    }

    public static void setIdGenerator(long idGenerator) {
        Player.idGenerator = idGenerator;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPath(ArrayList<Position2D> path) {
        this.path = path;
    }

    public boolean isMousecontroled() {
        return mousecontroled;
    }

    public void setMousecontroled(boolean mousecontroled) {
        this.mousecontroled = mousecontroled;
    }

    public void setPosition(Position2D position) {
        this.position = position;
    }

    public void setCurrentDirection(DirectionEnum currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void setPath(List<Position2D> path) {
        this.path = path;
    }

    /**
     * @return the leftKey
     */
    public int getLeftKey() {
        return leftKey;
    }

    /**
     * @param leftKey the leftKey to set
     */
    public void setLeftKey(int leftKey) {
        this.leftKey = leftKey;
    }

    /**
     * @return the rightKey
     */
    public int getRightKey() {
        return rightKey;
    }

    /**
     * @param rightKey the rightKey to set
     */
    public void setRightKey(int rightKey) {
        this.rightKey = rightKey;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 67 * hash + Objects.hashCode(this.position);
        hash = 67 * hash + Objects.hashCode(this.currentDirection);
        hash = 67 * hash + Objects.hashCode(this.color);
        hash = 67 * hash + Objects.hashCode(this.path);
        hash = 67 * hash + this.upKey;
        hash = 67 * hash + this.getDownKey();
        hash = 67 * hash + this.getLeftKey();
        hash = 67 * hash + this.getRightKey();
        hash = 67 * hash + (this.mousecontroled ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.position, other.position)) {
            return false;
        }
        if (this.currentDirection != other.currentDirection) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (this.upKey != other.upKey) {
            return false;
        }
        if (this.getDownKey() != other.getDownKey()) {
            return false;
        }
        if (this.getLeftKey() != other.getLeftKey()) {
            return false;
        }
        if (this.getRightKey() != other.getRightKey()) {
            return false;
        }
        if (this.mousecontroled != other.mousecontroled) {
            return false;
        }
        return true;
    }    
    
}
