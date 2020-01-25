import java.util.*;

public class Node {

    public int wins;
    public int losses;
    public int draws;
    private Move nodeMove;

    public ArrayList<Node> children;

    public Node(Move move) {
        children = new ArrayList<>();
        this.nodeMove = move;
    }

    public Move pickMove() {
        if (children.isEmpty())
            return null;
        else if(children.size() == 1)
            return children.get(0).nodeMove;
        Node bestNode = children.get(0);
        for (int i = 0; i < children.size()-1; i++) {
            Node newNode = children.get(i);
            if (newNode.losses == 0 && newNode.draws == 0) {
                bestNode = newNode;
                break;
            } else if(newNode.draws + newNode.wins - newNode.losses > bestNode.draws + bestNode.wins - bestNode.losses) {
                bestNode = newNode;
            }
        }
        return bestNode.nodeMove;
    }
}
