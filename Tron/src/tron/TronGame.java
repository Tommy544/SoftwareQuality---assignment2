/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

import controllers.KeyboardController;
import engine.Core;
import engine.Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author vcaniga
 */
public class TronGame implements Game {

    private static int moveAmount = 5;

    private final Core engine;
    private final TronDataModel dataModel;
    private final TronPresentation presentation;
    private final int windowWidth;
    private final int windowHeight;

    public TronGame() {
        engine = new Core(this);
        engine.init();
        this.windowWidth = engine.getSm().getWidth();
        this.windowHeight = engine.getSm().getHeight();
        dataModel = new TronDataModel(moveAmount, windowWidth, windowHeight);
        presentation = new TronPresentation(windowWidth, windowHeight);
    }

    public void startGame() {
        engine.run();
    }

    public void addNewPlayer(Player player) {
        dataModel.registerPlayer(player);
    }

    @Override
    public void drawNextScene(Graphics2D g) {
        dataModel.movePlayers();
        presentation.draw(g, dataModel.getPlayers());
        if (dataModel.checkCollisions()) {
            engine.stop();
        }
    }

    public static void main(String[] args) {
        TronGame tron = new TronGame();
        
        try {
            KeyboardController player1Controller = new KeyboardController(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
            tron.engine.registerNewController(player1Controller);
            Player player1 = new Player(new Point(40, 40), Direction.RIGHT, Color.yellow, player1Controller);
            tron.addNewPlayer(player1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error occured: The game engine does not yet support that type of input device");
        }

        try {
            KeyboardController player2Controller = new KeyboardController(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
            tron.engine.registerNewController(player2Controller);
            Player player2 = new Player(new Point(300, 300), Direction.RIGHT, Color.blue, player2Controller);
            tron.addNewPlayer(player2);
        } catch (IllegalArgumentException e) {
            System.out.println("Error occured: The game engine does not yet support that type of input device");
        }
        
        tron.startGame();
    }
}
