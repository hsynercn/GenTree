package model.genetic.tree.session;


/**
 * Created by saruman on 9.07.2017.
 */
public class Input {
    private String iTerminalId = null;
    private double[] values = null;

    public Input(String iTerminalId, double[] values) {
        this.iTerminalId = iTerminalId;
        this.values = values;
    }

    public String getiTerminalId() {
        return iTerminalId;
    }

    public double[] getValues() {
        return values;
    }

}
