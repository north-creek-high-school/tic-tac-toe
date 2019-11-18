import java.util.*;

public class Cup {

	private List<Integer> chips;
	private int moveTaken;

	private int currentToken = -1;

	public Cup(int[] map) {
		chips = new LinkedList<>();
		for(int i = 0; i < map.length; i++) {
			if(map[i] == 0) {
				chips.add(i);
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

	public void setMoveTaken(int moveTaken) {
		this.moveTaken = moveTaken;
		this.chips.remove(Integer.valueOf(moveTaken));
	}

	/**
	 * This method adjusts the number of chips of each type depending on
	 * whether the game was won or lost by the AI this cup belongs to.
	 *
	 * @param won tells whether the game was won by the AI this cup belongs to
	 */
	public Cup  adjustChips(boolean won) {
		/*
		 * If game was won, chip removed by setMoveTaken will be replaced,
		 * and another chip of that type will be added as a reward
		 */
		if(won) {
			chips.add(moveTaken);
			chips.add(moveTaken);
			//If game was lost, the chip removed by setMoveTaken will remain removed
		} else {
			//if there is no instance of that chip left after removing, chip will be replaced
			if(!chips.contains(moveTaken)) {
				chips.add(moveTaken);
			}
		}
		return this;
	}

}