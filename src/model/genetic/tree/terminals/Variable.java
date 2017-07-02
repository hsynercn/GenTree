package model.genetic.tree.terminals;

import model.genetic.tree.ITerminal;

/**
 * Created by saruman on 2.07.2017.
 */
public class Variable implements ITerminal{

    private double value = 0.0;
    private String symbol = null;

    public Variable(double value, String symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
