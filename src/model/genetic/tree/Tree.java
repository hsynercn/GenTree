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
}
