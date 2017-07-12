package model.genetic.tree;

/**
 * Created by saruman on 7.07.2017.
 */
public class Tree {

    private Node rootNode = null;

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

}
