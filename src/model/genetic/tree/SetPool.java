package model.genetic.tree;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by saruman on 9.07.2017.
 */
public class SetPool {

    private HashMap<String, IFunction> functionHashMap = new HashMap<String, IFunction>();
    private HashMap<String, ITerminal> terminalHashMap = new HashMap<String, ITerminal>();
    private ArrayList<String> variables = new ArrayList<String>();

    public SetPool(HashMap<String, IFunction> functionHashMap, HashMap<String, ITerminal> terminalHashMap, ArrayList<String> variables) {
        this.functionHashMap = functionHashMap;
        this.terminalHashMap = terminalHashMap;
        this.variables = variables;
    }

    public SetPool clone(){

        HashMap<String, IFunction> newFunctionHashMap = new HashMap<String, IFunction>();
        HashMap<String, ITerminal> newTerminalHashMap = new HashMap<String, ITerminal>();

        for(String tempKey: this.functionHashMap.keySet()){
            newFunctionHashMap.put(tempKey, this.functionHashMap.get(tempKey).clone());
        }
        for(String tempKey: this.terminalHashMap.keySet()){
            newTerminalHashMap.put(tempKey, this.terminalHashMap.get(tempKey).clone());
        }

        return new SetPool(newFunctionHashMap, newTerminalHashMap, this.variables);

    }

    //TODO add test for this method
    public boolean addIFunction(IFunction iFunction, String key){
        if(this.functionHashMap.containsKey(key)){
            return false;
        }else {
            this.functionHashMap.put(key, iFunction);
            return true;
        }
    }

    //TODO add test for this method
    public boolean addITerminal(ITerminal iTerminal, String key){
        if(this.terminalHashMap.containsKey(key)){
            return false;
        }else{
            this.terminalHashMap.put(key, iTerminal);
            return true;
        }
    }

    public IFunction getIFunction(String key){
        return this.functionHashMap.get(key);
    }

    public ITerminal getITerminal(String key){
        return this.terminalHashMap.get(key);
    }

    //TODO add test for this method
    public boolean updateITerminalValue(String key, double newValue){
        if(this.terminalHashMap.containsKey(key)){
            return this.terminalHashMap.get(key).setValue(newValue);
        }else{
            return false;
        }
    }

    public ArrayList<String> getITerminalIDs(){
        return new ArrayList<String>( this.terminalHashMap.keySet() );
    }

    public ArrayList<String> getIFuntionIDs(){
        return new ArrayList<String>( this.functionHashMap.keySet() );
    }


}
