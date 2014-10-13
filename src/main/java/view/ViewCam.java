package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by naleite on 13/10/14.
 */
public class ViewCam extends JComponent{

    //Graphics2D graph;
    Arc2D arc;
    Rectangle2D rect;
    Line2D line;

   @Override
    protected void paintComponent(Graphics g){
       Graphics2D graph = (Graphics2D) g;

       arc = new Arc2D.Double();

       graph.setPaint(Color.BLUE);
       graph.fill(arc);


    }














}
