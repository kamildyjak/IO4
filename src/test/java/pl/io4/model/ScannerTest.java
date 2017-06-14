package pl.io4.model;

import org.junit.Test;
import pl.io4.model.exceptions.ProductNotFoundException;
import pl.io4.model.wrappers.CodeScanner;

/**
 * Created by kamil on 14.06.2017.
 */
public class ScannerTest {

    private CodeScanner scanner;

    public ScannerTest(){
        scanner = new CodeScanner();
    }

    @Test
    public void getCode() throws ProductNotFoundException {
       try{
           scanner.scan();
       }catch (ProductNotFoundException e){
           e.printStackTrace();
       }
    }
}
