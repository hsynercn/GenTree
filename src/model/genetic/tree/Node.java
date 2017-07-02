package model.genetic.tree;

/**
 * Created by saruman on 2.07.2017.
 */
public class Node {

    private Node parent = null;
    private Node leftChild = null;
    private Node rightChild = null;
    private NodeType nodeType = null;

    private IFunction function = null;
    private ITerminal terminal = null;



    public Node(Node parent, Node leftChild, Node rightChild, IFunction function) {
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.function = function;
        this.nodeType = NodeType.NODE;
    }

    public Node(ITerminal terminal) {
        this.terminal = terminal;
        this.nodeType = NodeType.LEAF;
    }

    public double calculateNode() throws Exception {

        if( this.nodeType == NodeType.LEAF )
        {
            return this.terminal.getValue();
        }
        else if( this.nodeType == NodeType.NODE || this.nodeType == NodeType.ROOT )
        {
            return this.function.execute( this.leftChild.calculateNode(), this.rightChild.calculateNode() );
        }
        else
        {
            throw new Exception("Invalid node state, can`t calculate");
        }
    }
}
