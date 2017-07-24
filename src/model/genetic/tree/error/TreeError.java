package model.genetic.tree.error;

import model.genetic.tree.Tree;

/**
 * Created by saruman on 24.07.2017.
 */
public class TreeError {

    private Tree tree;
    private double error;

    public TreeError(Tree tree, double error) {
        this.tree = tree;
        this.error = error;
    }

    public Tree getTree() {
        return tree;
    }

    public double getError() {
        return error;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public void setError(double error) {
        this.error = error;
    }
}
