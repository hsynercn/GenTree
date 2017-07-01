package model.genetic.tree.terminals;

import model.genetic.tree.ITerminal;

/**
 * Created by saruman on 2.07.2017.
 */
public class Constant implements ITerminal{

    private final double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getSymbol() {
        return String.format("%d",this.value);
    }
}
