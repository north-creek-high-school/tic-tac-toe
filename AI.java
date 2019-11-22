import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class AI extends Player {
	public HashMap<int[], Cup> possibleMoves;
	private HashSet<int[]> recentPlays;

	public AI(String name) {
		super(name);
		possibleMoves = new HashMap<>();
		recentPlays = new HashSet<>();
	}

	public int chooseMove(BoardConfig board) {
		System.out.println("-----------Current Moves----------");
		for (Map.Entry<int[], Cup> entry : possibleMoves.entrySet()) {
			System.out.println(entry.getValue().toString());
		}
		System.out.println("---------------------------------");
		Cup moves = null;
		for (Map.Entry<int[], Cup> entry : possibleMoves.entrySet()) {
			if (Arrays.equals(entry.getKey(), board.getConfig())) {
				moves = entry.getValue();
			}
		}

		if (moves == null) {
			moves = new Cup(board.getConfig());
		}
		System.out.println(moves.toString());
		// System.out.println(Arrays.toString(board.getConfig())+ " " + map);
		possibleMoves.put(board.getConfig(), moves);
		recentPlays.add(board.getConfig());
		int move = moves.pickRandom();
		possibleMoves.get(board.getConfig()).setMoveTaken(move);
		return move;
	}

	public void learn(boolean won) {
		for (int[] play : recentPlays) {
			Cup newMoves = possibleMoves.get(play).adjustChips(won);
			possibleMoves.put(play, newMoves);
		}
		if (won) {
			System.out.println("---------------Winner--------------");
			for (int[] play : recentPlays) {
				System.out.println(possibleMoves.get(play).toString());
			}
			System.out.println("-----------------------------------");
		}
	}

	public AI train() {
		int player = 1;
		AI playerAI = new AI("AI");
		AI trainer = new AI("Training AI");
		int move = 0;

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j <= 9 && !GameEngine.checkGrid(); j++) {
				BoardConfig board = new BoardConfig(GameUI.getEngine().getMap());
				if (j % 2 == 0)
					move = playerAI.chooseMove(board);
				else
					move = trainer.chooseMove(board);
				GameEngine.updateMap(move, player);
				player = player == 1 ? 2 : 1;
			}
			playerAI.learn(player == 1 && !GameEngine.getBoardMessage().equalsIgnoreCase("It's a Draw"));
			trainer.learn(player == 2 && !GameEngine.getBoardMessage().equalsIgnoreCase("It's a Draw"));
		}
		
		return playerAI;
	}
	public void clearPlays() {
		this.recentPlays = new HashSet<>();
	}
}
