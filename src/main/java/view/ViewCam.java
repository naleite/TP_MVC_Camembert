package view;

import model.Item;
import model.Model;
import model.ModelImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.*;

/**
 * Created by naleite on 13/10/14.
 */
public class ViewCam extends JComponent implements View{

    //Graphics2D graph;
    Model m;
    Observable o;
    java.util.List<Arc2D> arcs;
    java.util.List<Rectangle2D> rects;
    Line2D line;
    static final int X=400;
    static final int Y=400;
    static final double R=200;
    //double angle;
    Color[] colors={Color.YELLOW,Color.BLUE,Color.CYAN,Color.GREEN,Color.RED};

    public ViewCam(Model m) {

        this.m=m;
        this.o=(ModelImpl) m;
        o.addObserver(this);
        arcs=new ArrayList<Arc2D>();


    }

    @Override
    protected void paintComponent(Graphics g){
       Graphics2D graph = (Graphics2D) g;

        createArcs(graph);

        Arc2D.Double arcblanc=new Arc2D.Double();

        arcblanc.setArcByCenter(X,Y,R/2,0,360,Arc2D.PIE);

        graph.setPaint(Color.WHITE);
        graph.fill(arcblanc);


        System.out.println("Nb Arc:" +arcs.size());



    }



    private Color nextcolor(int currentIndex){

        int tot=colors.length;
        currentIndex=((currentIndex) % tot);
        return colors[currentIndex];



    }

    public void setRect(){

        Iterator<Item> iter=m.getItemsIterator();
        while (iter.hasNext()){
            String decl=iter.next().getDescription();
            Rectangle2D rect=new Rectangle2D.Double();

        }


    }

    private void createArcs(Graphics2D graph){

        arcs.clear();
        Iterator<Item> iter=m.getItemsIterator();
        System.out.println("nb items:"+m.getNbItems());
        double anglestart=0;
        int indexcolor=0;
        double anglestop=0;
        while (iter.hasNext()){
            Arc2D arc = new Arc2D.Double();

            double angle= (iter.next().getValue()  * 360 / m.getTotalValue());

            anglestop=anglestart+angle;

            System.out.println("Anglestart: "+anglestart);
            System.out.println("Anglestop: "+anglestop);

            arc.setArcByCenter(X,Y,R,anglestart,angle,Arc2D.PIE);
            arcs.add(arc);



            graph.setPaint(nextcolor(indexcolor));

            System.out.println("indexColor "+indexcolor);
            indexcolor++;

            // graph.fill(arc);

            anglestart=anglestop;


            graph.fill(arc);

        }

    }




    @Override
    public void update(Observable o, Object arg) {

        this.repaint();
    }
}
