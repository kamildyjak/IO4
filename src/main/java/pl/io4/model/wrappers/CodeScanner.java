package pl.io4.model.wrappers;

import pl.io4.model.Model;
import pl.io4.model.entities.Product;
import pl.io4.model.exceptions.ProductNotFoundException;
import pl.io4.model.machines.ProductsMachine;

import java.util.Random;

/**
 * Created by kamil on 14.06.2017.
 */
public class CodeScanner implements  Scanner {

    private ProductsMachine productsMachine;

    public CodeScanner(){
        productsMachine = Model.getProductsMachine();
    }

    public Product scan() throws ProductNotFoundException {
        int id = getId();
        return productsMachine.getProduct(id);
    }

    @Override
    public int getId() {
        Random randomGenerator = new Random();
        int result = randomGenerator.nextInt(500);
        return result; //
    }
}
