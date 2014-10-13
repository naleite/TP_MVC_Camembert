package view;

import model.Item;
import model.ItemImpl;
import model.Model;
import model.ModelImpl;

import javax.swing.*;
import java.util.Random;

/**
 * Created by naleite on 13/10/14.
 */
public class Testforview {

    public static void main(String arg[]){
        Model m=new ModelImpl("HELLO");
        Item it1=new ItemImpl("HHH","HHKKHKHKL",50);
        Item it2=new ItemImpl("UUU","HHKKHKHKL",80);
        Item it3=new ItemImpl("OOO","HHKKHKHKL",100);
        Item it4=new ItemImpl("III","HHKKHKHKL",120);
        m.addItem(it1);
        m.addItem(it2);
        m.addItem(it3);
        m.addItem(it4);

        for(int i=0;i<20;i++){
            Item it=new ItemImpl("HHH","HHKKHKHKL",new Random().nextDouble()*20);
            m.addItem(it);
        }
        JFrame frame=new JFrame();
        frame.setBounds(0,0,800,800);
        ViewCam viewCam=new ViewCam(m);
        frame.add(viewCam);
        frame.setVisible(true);







    }
}
