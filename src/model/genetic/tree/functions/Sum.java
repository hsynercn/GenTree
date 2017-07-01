package model.genetic.tree.functions;

import model.genetic.tree.IFunction;

/**
 * Created by saruman on 2.07.2017.
 */
public class Sum extends ZeroFunc{

    @Override
    public double execute(double varA, double varB) {
        return varA + varB;
    }

}
