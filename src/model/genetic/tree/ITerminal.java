package model.genetic.tree;

/**
 * Created by saruman on 2.07.2017.
 */
public interface ITerminal {
    public double getValue();
    public boolean setValue(double value);
    public String getSymbol();
    public ITerminal clone();
}
