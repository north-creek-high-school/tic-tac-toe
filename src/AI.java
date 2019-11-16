import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AI {
	public HashMap<int[], Cup> possibleMoves;

	public AI() {
		super();
		possibleMoves = new HashMap<>();
	}

	public int makeMove(BoardConfig board) {
		Cup moves = possibleMoves.get(board.getConfig());
		if(moves == null) {
			moves = new Cup(board.getConfig());
		}
		System.out.println(Arrays.toString(board.getConfig())+ " " + board.getConfig());
		possibleMoves.put(board.getConfig(), moves);
		return moves.pickRandom();
	}

	public void learn(boolean won) {
//		for (int i = 0; i < cups.size(); i++) {
//			cups.get(i).learn(won);
//			System.out.println(i + " = " + cups.get(i));
	}
}
