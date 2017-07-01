package model.genetic.tree.functions;

import model.genetic.tree.IFunction;

/**
 * Created by saruman on 2.07.2017.
 */
public class ZeroFunc implements IFunction{


    @Override
    public double execute(double varA, double varB) {
        return 0;
    }

    @Override
    public String getSymbol() {
        return this.getClass().getSimpleName();
    }
}
