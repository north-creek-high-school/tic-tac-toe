import java.awt.*;
import javax.swing.JOptionPane;

/*
 Rename this file to GameDisplay
*/

public class DrawGrid {
	private static final int xSize = 450;
	private static final int ySize = 450;
	private static int col1 = xSize / 3;
	private static int col2 = col1 * 2;
	private static int row1 = ySize / 3;
	private static int row2 = row1 * 2;
	private static final int SIZE = 175;
	private String shape = "X";
	private boolean isGameOver = false;


	private DrawingPanel panel = new DrawingPanel(xSize, ySize);
	private Graphics g = getPanel().getGraphics();

	static GameEngine game = new GameEngine();

	DrawGrid() {
		drawColumns();
		drawRows();
	}

	private void drawColumns() {
		g.drawLine(col1, 0, col1, ySize);
		g.drawLine(col2, 0, col2, ySize);
	}

	private void drawRows() {
		g.drawLine(0, row1, xSize, row1);
		g.drawLine(0, row2, xSize, row2);
	}

	public DrawingPanel getPanel() {
		return panel;
	}


	public void drawShape(int x, int y) {
		int cellX = (x / 150);
		int cellY = (y / 150);
		int cell = cellX + (cellY * 3);

		if (!isGameOver) {
			g.setFont(new Font("Arial", Font.BOLD, SIZE));

			if (GameEngine.getMap()[cell] == 0) {
				g.drawString(shape, (cellX * (xSize / 3)) + 15, (cellY * (ySize / 3)) + 135);
				System.out.println("x = " + ((cellX * (xSize / 3)) + 15) + "\n y = " + ((cellY * (ySize / 3)) + 135));
				GameEngine.getMap()[cell] = shape.equals("X") ? 1 : 2;
				shape = shape.equals("X") ? "O" : "X";
			}
			isGameOver = GameEngine.checkGrid();


		}
	}

	public void playAgain(String winner) {
		int answer = JOptionPane.showConfirmDialog(null, "Would you like to play again?", winner, JOptionPane.YES_NO_OPTION);
		if (answer == 0) {
			GameEngine.closePrev();
			GameEngine.startGame();
		} else {
			System.exit(0);
		}
	}
}
