package model.genetic.tree.functions;

/**
 * Created by saruman on 2.07.2017.
 */
public class Div extends ZeroFunc{

    @Override
    public double execute(double varA, double varB) {
       if( varB == 0 )
       {
           return Double.MAX_VALUE;
       }
       return varA / varB;
    }

}
