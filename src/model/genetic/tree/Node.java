package model.genetic.tree;

/**
 * Created by saruman on 2.07.2017.
 */
public class Node {

    public enum NodeType {
        NODE,
        LEAF
    }

    private Node parent = null;
    private Node leftChild = null;
    private Node rightChild = null;
    private NodeType nodeType = null;

    private String iFunctionID = null;
    private String iTerminalID = null;


    public Node dumbClone(){
        Node dumbClone = null;
        if(this.nodeType == NodeType.NODE){
            dumbClone = new Node(this.parent, this.iFunctionID, this.nodeType);
        }else if(nodeType == NodeType.LEAF)
        {
            dumbClone = new Node(this.parent, this.iTerminalID, this.nodeType);
        }
        dumbClone.setRightChild(this.leftChild);
        dumbClone.setLeftChild(this.rightChild);
        return dumbClone;
    }

    public Node(Node parent, String componenetId, NodeType nodeType) {
        this.parent = parent;
        this.nodeType = nodeType;
        if(nodeType == NodeType.NODE){
            this.iFunctionID = componenetId;
        }else if(nodeType == NodeType.LEAF)
        {
            this.iTerminalID = componenetId;
        }
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
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

    public String getiFunctionID() {
        return iFunctionID;
    }

    public String getiTerminalID() {
        return iTerminalID;
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
