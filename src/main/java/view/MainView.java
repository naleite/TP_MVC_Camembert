package view;

import controler.Controler;
import controler.ControlerImpl;
import model.Item;
import model.ItemImpl;
import model.Model;
import model.ModelImpl;

import javax.swing.*;
import java.util.Random;

/**
 * Created by naleite on 13/10/14.
 */
public class MainView {


    public static void main(String arg[]){


        Model m=new ModelImpl("HELLO");
        View viewCam=new ViewCam(m);
        Controler controler=new ControlerImpl();
        controler.setModel(m);
        controler.setView((ViewCam)viewCam);
        controler.addMouseListener();

//        JFrame frame=new JFrame();
//        frame.setBounds(0,0,800,800);
//
//        frame.add((ViewCam)viewCam);
//        frame.setVisible(true);


//        for(int i=0;i<5;i++){
//            Item it=new ItemImpl("HHH","HHKKHKHKL",new Random().nextDouble()*20);
//            m.addItem(it);
////            try {
////                Thread.sleep(1000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//
//
//        }


//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        m.setTitre("Bonjour");
        Item it1=new ItemImpl("1","Item1",50);
        Item it2=new ItemImpl("2","Item2",80);
        Item it3=new ItemImpl("3","Item3",100);
        Item it4=new ItemImpl("4","Item4",120);
        m.addItem(it1);
        m.addItem(it2);
        m.addItem(it3);
        m.addItem(it4);










    }
}
