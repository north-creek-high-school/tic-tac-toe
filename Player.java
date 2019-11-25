public class Player {
	String name;

	public Player(String name) {
		this.name = name;
	}

	public int[] makePlayerMove(int x, int y) {
		int move = 0;
		int moveX = 0;
		int moveY = 0;

		moveX = (x / 150);
		moveY = (y / 150);
		move = moveX + (moveY * 3);
		if (GameEngine.getMap()[move] != 0) {
			return new int[]{-1};
		}
		return new int[]{moveX, moveY, move};
	}
}
