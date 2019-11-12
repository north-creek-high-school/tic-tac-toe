import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AI {
	public HashMap<int[], Cup> possibleMoves;

	public AI() {
		super();
		possibleMoves = new HashMap<>();
		
		for(int i = 0; i < Math.pow(3, 9); i++) {
			possibleMoves.put(arg0, arg1);
		}
	}

	public int makeMove(int[] movesAvailable) {
		Cup moves = possibleMoves.get(movesAvailable);
//		possibleMoves.put(movesAvailable, moves);
	
		return moves.pickRandom();
	}

	public void learn(boolean won) {
//		for (int i = 0; i < cups.size(); i++) {
//			cups.get(i).learn(won);
//			System.out.println(i + " = " + cups.get(i));
//		}
	}
}