package pl.io4.views;

import javax.swing.*;

/**
 * Created by Zax37 on 14.03.2017.
 *
 * Widok opisuje interfejs graficzny.
 */

public class TestView extends JFrame {

    private JTextField testText = new JTextField(10);

    public TestView(){
        JPanel panel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        panel.add(testText);
        this.add(panel);
    }

    public void setTestText(String text){
        testText.setText(text);
    }

}
