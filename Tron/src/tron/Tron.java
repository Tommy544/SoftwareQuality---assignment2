/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

import engine.Core;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 *
 * @author vcaniga
 */
public class Tron extends Core {

    private final int MOVE_AMOUNT = 5;
    private TronDataModel tronDataModel;

    @Override
    public void init() {
        super.init();

        tronDataModel = new TronDataModel(MOVE_AMOUNT, sm.getWidth(), sm.getHeight());

        // ========= To choose which players are to be included in-game, comment/uncomment appropriate lines below ===========
        tronDataModel.registerPlayer(1); // <-- Yellow player, up: UP; down: DOWN; left: LEFT; right: RIGHT;
        tronDataModel.registerPlayer(2); // <-- Blue player, up: W; down: S; left: A; right: D;
        tronDataModel.registerPlayer(3); // <-- Green player, up: I; down: K; left: J; right: L
        tronDataModel.registerPlayer(4); // <-- Red player, mouse controlls - turn left: Left Mouse Button; turn right Right Mouse Button
        // ===================================================================================================================
    }

    @Override
    public void draw(Graphics2D g) {
        fillBackground(g);
        tronDataModel.movePlayers();
        drawPlayerBikes(g);
        if (tronDataModel.checkCollisions()) {
            stop();
        }
    }

    private void fillBackground(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, sm.getWidth(), sm.getHeight());
    }

    private void drawPlayerBikes(Graphics2D g) {
        List<Player> players = tronDataModel.getPlayers();
        for (Player player : players) {
            g.setColor(player.getColor());
            for (int i = 0; i < player.getPath().size(); i++) {
                g.fillRect(player.getPath().get(i).getX(), player.getPath().get(i).getY(), 10, 10);
            }
        }
    }

    public static void main(String[] args) {
        new Tron().run();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        tronDataModel.changePlayerOrientation(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        tronDataModel.changePlayerOrientation(e);
    }

}
