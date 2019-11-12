import java.util.*;

public class Cup {

	private List<Integer> chips;

	private int currentToken = -1;

	public Cup() {
		chips = new LinkedList<>();
		chips.add(0);
		chips.add(1);
		chips.add(2);
		chips.add(3);
		chips.add(4);
		chips.add(5);
		chips.add(6);
		chips.add(7);
		chips.add(8);
	}

	public void learn(boolean won) {
		if (currentToken == -1)
			return;

		if (won) {
			chips.add(currentToken);
		} else {
			chips.remove(currentToken);
			if (!chips.contains(0)) {
				chips.add(0);
			}
			if (!chips.contains(1)) {
				chips.add(1);
			}
			if (!chips.contains(2)) {
				chips.add(2);
			}
			if (!chips.contains(3)) {
				chips.add(3);
			}
			if (!chips.contains(4)) {
				chips.add(4);
			}
			if (!chips.contains(5)) {
				chips.add(5);
			}
			if (!chips.contains(6)) {
				chips.add(6);
			}
			if (!chips.contains(7)) {
				chips.add(7);
			}
			if (!chips.contains(8)) {
				chips.add(8);
			}
		}
	}

	public int pickRandom() {
		int rand = (int) (Math.random() * chips.size());
		currentToken = chips.get(rand);
		return currentToken;
	}

	public String toString() {
		return chips.toString();
	}
}