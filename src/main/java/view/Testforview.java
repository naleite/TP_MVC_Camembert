package view;

import javax.swing.*;

/**
 * Created by naleite on 13/10/14.
 */
public class Testforview {

    public static void main(String arg[]){
        JFrame frame=new JFrame();
        frame.setBounds(100,100,500,500);
        ViewCam viewCam=new ViewCam();
        frame.add(viewCam);
        frame.setVisible(true);
    }
}
