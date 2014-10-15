package view;

import model.Item;
import model.Model;
import model.ModelImpl;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

    JFrame frame;
    Line2D line;
    Graphics2D graph;
     int X=400;
     int Y=400;
    static final double R=200;
    //double angle;
    Color[] colors={Color.YELLOW,Color.BLUE,Color.CYAN,Color.GREEN,Color.RED};

    Arc2D arcblanc,arccentre;



    public ViewCam(Model m) {

        this.m=m;
        this.o=(ModelImpl) m;
        o.addObserver(this);
        arcs=new ArrayList<Arc2D>();
        frame=new JFrame();
        frame.setBounds(0,0,800,800);

        frame.add(this);
        frame.setVisible(true);

        X=getWidth()/2;
        Y=getHeight()/2;



    }

    @Override
    protected void paintComponent(Graphics g){
        graph = (Graphics2D) g;

        createArcs(graph);

         arcblanc=new Arc2D.Double();

        arcblanc.setArcByCenter(X,Y,R*2/3,0,360,Arc2D.PIE);

        graph.setPaint(Color.WHITE);
        graph.fill(arcblanc);

         arccentre=new Arc2D.Double();

        arccentre.setArcByCenter(X,Y,R/2,0,360,Arc2D.PIE);

        graph.setPaint(Color.MAGENTA);
        graph.fill(arccentre);


        //graph.fillOval(X,Y,50,20);
        graph.setPaint(Color.BLACK);
        graph.setFont(new Font("Arial",Font.BOLD,20));
        String title=m.getTitre();
        FontMetrics fm = g.getFontMetrics();
        int stringWidth = fm.stringWidth(title);
        int stringAscent = fm.getAscent();
        int xCoordinate = getWidth()/2 - stringWidth/2;
        int yCoordinate = getHeight()/2 +stringAscent/2;

        graph.drawString(title,xCoordinate,yCoordinate);


        //System.out.println(m.getTitre());





    }



    private Color nextcolor(int currentIndex){

        int tot=colors.length;
        currentIndex=((currentIndex) % tot);
        return colors[currentIndex];



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


            anglestart=anglestop;


            graph.fill(arc);

//            graph.setPaint(Color.BLACK);
//            graph.setFont(Font.getFont("Arial"));
//            graph.drawString(m.getTitre(),X,Y);


        }

    }




    @Override
    public void update(Observable o, Object arg) {

        this.repaint();
    }

    public MouseListener[] getMouseListener() {
        return frame.getMouseListeners();
    }

    public void setMouseListener(MouseListener mouseListener) {

        frame.addMouseListener(mouseListener);

    }

    public ArrayList<Arc2D> getArcsList(){
        return (ArrayList)arcs;
    }
    @Override
    public void showItemInfo(Item item) {
        System.out.println(item.getIntitule());
        System.out.println(item.getDescription());
        System.out.println(item.getValue());

        //Draw REct pas reussir
        Point p=this.getMousePosition();

        Rectangle2D popup=new Rectangle2D.Double();
        popup.setRect(X,Y,100,50);


        graph.setPaintMode();
        graph.setPaint(Color.GREEN);
        graph.drawRect(X+200,Y+200,100,50);
        //System.out.println(popup.toString());

    }

    public void setRect(Point p){



        }


        @Override
    public JFrame getFrame() {
        return this.frame;
    }

    @Override
    public Arc2D getArcBlanc() {
        return this.arcblanc;
    }

    @Override
    public Arc2D getArcCentre() {
        return this.arccentre;
    }


}
