/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

import controllers.KeyboardController;
import controllers.MouseController;
import engine.Core;
import engine.Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Main class of the Tron game that also contains the main method.
 * Implements interface Game so that the engine can recognize Tron as a game.
 * 
 * @author Vladimir Caniga
 * @author Jakub Smolar
 */
public class TronGame implements Game {

    /**
     * The amount of pixels the player should move in one step
     */
    private static int moveAmount = 5;

    private final Core engine; // Instance of the engine this game is based on
    private final TronDataModel dataModel;
    private final TronPresentation presentation;
    private final int windowWidth;
    private final int windowHeight;

    /**
     * Constructor of the Tron game.
     * Initializes the engine of the game, classes that represent data model and
     * presentation of the Tron game.
     */
    public TronGame() {
        engine = new Core(this);
        engine.init();
        this.windowWidth = engine.getSm().getWidth();
        this.windowHeight = engine.getSm().getHeight();
        dataModel = new TronDataModel(moveAmount, windowWidth, windowHeight);
        presentation = new TronPresentation(windowWidth, windowHeight);
    }

    /**
     * Method used for starting the Tron game
     */
    public void startGame() {
        engine.run();
    }

    /**
     * Adds a new player that is passed as a parameter to the game
     * 
     * @param player New Player that is to be added to the game
     */
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

    /**
     * main method of the game.
     * Creates instance of the TronGame class, adds players to the game and then
     * it starts the game.
     * 
     * @param args 
     */
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
        
        try {
            MouseController player3Controller = new MouseController(MouseEvent.BUTTON1, MouseEvent.BUTTON3);
            tron.engine.registerNewController(player3Controller);
            Player player3 = new Player(new Point(150, 150), Direction.DOWN, Color.red, player3Controller);
            tron.addNewPlayer(player3);
        } catch (IllegalArgumentException e) {
            System.out.println("Error occured: The game engine does not yet support that type of input device");
        }
        
        tron.startGame();
    }
}
