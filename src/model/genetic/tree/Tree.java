package model.genetic.tree;

/**
 * Created by saruman on 7.07.2017.
 */
public class Tree {

    private Node rootNode = null;

    public Tree clone(){
        return new Tree(this.rootNode);
    }

    private Node cloneNode(Node realParent ){

        Node copyParent = realParent.dumbClone();
        if(realParent.getNodeType() == Node.NodeType.NODE){

            Node clonedLeft = this.cloneNode(realParent.getLeftChild());
            Node clonedRight = this.cloneNode(realParent.getRightChild());

            copyParent.setLeftChild(clonedLeft);
            copyParent.setRightChild(clonedRight);

            clonedLeft.setParent(copyParent);
            clonedRight.setParent(copyParent);

            return copyParent;

        }else {
            return realParent.dumbClone();
        }

    }

    public Tree(Node root) {
        this.rootNode = root;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    public int getNodeCount(){
        return travelNodes(this.rootNode);
    }

    private int travelNodes(Node node){
        if(node.getNodeType() == Node.NodeType.NODE){
            return 1 + this.travelNodes(node.getLeftChild()) + this.travelNodes(node.getRightChild());
        }else {
            return 1;
        }
    }

    public String getTreeExpression(){
        return this.getNodeExpression(this.rootNode);
    }

    private String getNodeExpression(Node node){
        if(node.getNodeType() == Node.NodeType.NODE){
            return "( " + this.getNodeExpression(node.getLeftChild()) + " " + node.getiFunctionID() + " " + this.getNodeExpression(node.getRightChild()) + " )" ;
        }else {
            return node.getiTerminalID();
        }
    }

}
