import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

public class AI {
	private ArrayList<Cup> cups;
	public HashMap<Integer, Integer> aiMoves;

	public AI() {
		super();
		cups = new ArrayList<>();
		aiMoves = new HashMap<>();
		for (int i = 0; i <= 9; i++) {
			cups.add(new Cup());
		}
	}

	public int makeMove(int cupNum) {
		int place = cups.get(cupNum).pickRandom();
		aiMoves.put(cupNum, place);
	
		return place;
	}

	public void learn(boolean won) {
		for (int i = 0; i < cups.size(); i++) {
			cups.get(i).learn(won);
			System.out.println(i + " = " + cups.get(i));
		}
	}
}