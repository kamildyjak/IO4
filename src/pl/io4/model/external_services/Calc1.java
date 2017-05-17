package pl.io4.model.external_services;

/**
 * Created by Marcin on 10.05.2017.
 */
public class Calc1 {
    public static double calc(double price, char cat){
        switch (cat) {
            case 'a': return price*0.23;
            case 'b': return price*0.08;
            default: return 0;
        }
    }
}
