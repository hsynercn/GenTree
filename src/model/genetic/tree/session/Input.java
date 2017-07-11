package model.genetic.tree.session;


import java.util.ArrayList;

/**
 * Created by saruman on 9.07.2017.
 */
public class Input {
    private String iTerminalId = null;
    private ArrayList<Double> values = null;

    public Input(String iTerminalId, ArrayList<Double> values) {
        this.iTerminalId = iTerminalId;
        this.values = values;
    }

    public String getiTerminalId() {
        return iTerminalId;
    }

    public ArrayList<Double> getValues() {
        return values;
    }

    public void setiTerminalId(String iTerminalId) {
        this.iTerminalId = iTerminalId;
    }

    public void setValues(ArrayList<Double> values) {
        this.values = values;
    }
}
