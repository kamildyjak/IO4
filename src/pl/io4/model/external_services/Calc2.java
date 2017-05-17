package pl.io4.model.external_services;

/**
 * Created by Marcin on 10.05.2017.
 */
public class Calc2 {
    public static double licz(char cat, double price){
        switch (cat) {
            case 'a': return price*0.23;
            case 'b': return price*0.08;
            default: return 0;
        }
    }
}
