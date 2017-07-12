package model.genetic.tree;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by saruman on 9.07.2017.
 */
public class SetPool {

    private HashMap<String, IFunction> functionHashMap = new HashMap<String, IFunction>();
    private HashMap<String, ITerminal> terminalHashMap = new HashMap<String, ITerminal>();

    public SetPool(HashMap<String, IFunction> functionHashMap, HashMap<String, ITerminal> terminalHashMap) {
        this.functionHashMap = functionHashMap;
        this.terminalHashMap = terminalHashMap;
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

        return new SetPool(newFunctionHashMap, newTerminalHashMap);

    }

    public IFunction getIFunction(String key){
        return this.functionHashMap.get(key);
    }

    public ITerminal getITerminal(String key){
        return this.terminalHashMap.get(key);
    }

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
