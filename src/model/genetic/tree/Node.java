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



    public Node(Node parent, IFunction function) {
        this.parent = parent;
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
        else if( this.nodeType == NodeType.NODE )
        {
            return this.function.execute( this.leftChild.calculateNode(), this.rightChild.calculateNode() );
        }
        else
        {
            throw new Exception("Invalid node state, can`t calculate");
        }
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public String getSymbol(){
        if(this.nodeType == NodeType.NODE ){
            return " { " + this.leftChild.getSymbol() + " " + this.function.getSymbol() + " " + this.rightChild.getSymbol() + " } ";
        }else {
            return this.terminal.getSymbol(); //NodeType.LEAF
        }
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public void setFunction(IFunction function) {
        this.function = function;
    }

    public void setTerminal(ITerminal terminal) {
        this.terminal = terminal;
    }

    public Node getParent() {


        return parent;
    }

    public boolean replaceChild(Node oldChild, Node newChild){
        if( oldChild == this.leftChild ) {
            this.setLeftChild(newChild);
            return true;
        }else if (oldChild == this.rightChild ){
            this.setRightChild(newChild);
            return true;
        }
        return false;
    }

}
