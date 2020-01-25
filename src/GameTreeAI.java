import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class GameTreeAI extends Player {

    private Map<String, Node> treeNodes = new HashMap<>();
    private Board board = new Board();
    private Node root;

    private PrintStream writer;

    private GameTreeAI(GameUserInterface gui, int num) {
        super(gui, num);
    }

    public static Player getPlayer(GameUserInterface gui, int num) {
        return new GameTreeAI(gui, num);
    }

    public String toString() {
        return "Game Tree AI";
    }

    public void learn() {
        root = new Node(null);
        treeNodes.put(board.toString(), root);
        String name = "test.txt";
        try {
            writer = new PrintStream(new File(name));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        createGameTree(root, board);
        writer.close();
    }

    public Move getMove(Board board) {
        Move move = treeNodes.get(board.toString()).pickMove();
        this.getGui().showAIMove(move);
        return move;
    }

    private void createGameTree(Node node, Board b) {
        Board newBoard = b;
        Move[] moves = newBoard.getAllMoves();
        for (Move move : moves) {
            newBoard.doMove(move);
            Node child = new Node(move);
            treeNodes.put(newBoard.toString(), child);
            node.children.add(child);

            if (newBoard.findWinner() == 1) {
                node.wins++;
            } else if (newBoard.findWinner() == 2) {
                node.losses++;
            } else if (newBoard.isBoardFull()) {
                node.draws++;
            } else {
                createGameTree(child, newBoard);
            }
            newBoard.undoMove(move);
        }
        for (Node child : node.children) {
            node.wins += child.wins;
            node.losses += child.losses;
            node.draws += child.draws;
        }
    }
}
