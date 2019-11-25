
/**
 * @author Shantanu Singh and Shreshth Kharbanda
 * Advance Programming Topics
 * Period 3
 * TicTacToe
 * <p>
 * The GameUI class is responsible for displaying and setting up all the features
 * of the game interface, such as making the grid, drawing the shapes, and the
 * choice dialog.
 * </p>
 */

import javax.swing.*;
import java.awt.*;

public class GameUI {

	// set custom font
	public static final int TEXTSIZE = 175;

	// Constants for the dimensions of each square and the whole grid
	private final int xSize = 450;
	private final int ySize = 450;
	private int col1 = xSize / 3;
	private int col2 = col1 * 2;
	private int row1 = ySize / 3;
	private int row2 = row1 * 2;
	// initializes variable shape, used to draw on the grid
	public String shape = "X";
	// defines the winner variable
	String winner = "";

	// creates a DrawingPanel with the dimensions, and initializes it to variable
	// panel
	private DrawingPanel panel = new DrawingPanel(xSize, ySize);
	// initializes the graphics of the panel to g
	private Graphics g = getPanel().getGraphics();
	private static GameEngine engine = new GameEngine();
	private BoardConfig board;

	// Constructor for GameUI, which draws an empty 3x3 grid.
	GameUI() {
		drawGrid();
		board = new BoardConfig(engine.getMap());
	}

	/**
	 * empties the screen, then draws columns and rows
	 */
	public void drawGrid() {
		// Clears the panel
		panel.clear();
		// Draws the columns on the panel
		g.setColor(Color.BLACK);
		g.drawLine(col1, 0, col1, ySize);
		g.drawLine(col2, 0, col2, ySize);
		// Draws the rows on the panel
		g.drawLine(0, row1, xSize, row1);
		g.drawLine(0, row2, xSize, row2);
	}

	/**
	 * getPanel is a getter to get the value of panel
	 *
	 * @return panel - the variable that contains the DrawingPanel
	 */
	public DrawingPanel getPanel() {
		return panel;
	}

	/**
	 * drawShape draws an X or O, depending on who's turn it is. It checks if the
	 * game is over or not. If not, it checks where the user clicked, and assigns a
	 * square to it. Based on that, it draws the shape in the middle of that square.
	 *
	 * @param x the x coordinate of the mouse click
	 * @param y the y coordinate of the mouse click
	 */
	public void drawShape(int x, int y, AI ai) {

		boolean didMove;

		didMove = makePlayerMove(x, y);
		if (!didMove) {
			return;
		}

		makePlayerMove(x, y, ai);
	}

	private boolean makePlayerMove(int x, int y, AI ai) {

		board = new BoardConfig(engine.getMap());
		int move = ai.chooseMove(board);
		int moveX = move % 3;
		int moveY = move / 3;

		g.setFont(new Font("Arial", Font.BOLD, TEXTSIZE));
		g.drawString(shape, (moveX * (xSize / 3)) + 15, (moveY * (ySize / 3)) + 135);

		GameEngine.updateMap(move, shape.equals("X") ? 1 : 2);

		// change the shape variable
		shape = shape.equals("X") ? "O" : "X";
		return true;
	}

	private boolean makePlayerMove(int x, int y) {
		g.setFont(new Font("Arial", Font.BOLD, TEXTSIZE));
		int[] moveDimensions = GameEngine.players[0].makePlayerMove(x, y);
		if (moveDimensions[0] == -1)
			return false;
		g.drawString(shape, (moveDimensions[0] * (xSize / 3)) + 15, (moveDimensions[1] * (ySize / 3)) + 135);
		GameEngine.updateMap(moveDimensions[2], shape.equals("X") ? 1 : 2);

		// change the shape variable
		shape = shape.equals("X") ? "O" : "X";
		return true;
	}

	public static GameEngine getEngine() {
		return engine;
	}

}
