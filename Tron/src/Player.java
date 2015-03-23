
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
    long id;
    int centrex;
    int centrey;
    int currentDirection;
    Color color;
    ArrayList<Integer> pathx = new ArrayList();
    ArrayList<Integer> pathy = new ArrayList();
    int up;
    int down;
    int left;
    int right;
    boolean mousecontroled = false;

    public Player(long id, int centrex, int centrey, int currentDirection, Color color, int up, int down, int left, int right) {
        this.id = id;
        this.centrex = centrex;
        this.centrey = centrey;
        this.currentDirection = currentDirection;
        this.color = color;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public int getCentrex() {
        return centrex;
    }


    public int getCentrey() {
        return centrey;
    }

    public int getCurrentDirection() {
        return currentDirection;
    }

    public Color getColor() {
        return color;
    }

    public ArrayList<Integer> getPathx() {
        return pathx;
    }

    public ArrayList<Integer> getPathy() {
        return pathy;
    }

    public int getUp() {
        return up;
    }

    public int getDown() {
        return down;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public void setCentrex(int centrex) {
        this.centrex = centrex;
    }

    public void setCentrey(int centrey) {
        this.centrey = centrey;
    }

    public void setCurrentDirection(int currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPathx(ArrayList<Integer> pathx) {
        this.pathx = pathx;
    }

    public void setPathy(ArrayList<Integer> pathy) {
        this.pathy = pathy;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void setRight(int right) {
        this.right = right;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
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
        return true;
    }




    

    


    
    
}
