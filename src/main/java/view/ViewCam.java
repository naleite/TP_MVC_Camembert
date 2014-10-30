package view;

import model.Item;
import model.Model;
import model.ModelImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

/**
 * Created by naleite on 13/10/14.
 */
public class ViewCam extends JComponent implements View{

    //Graphics2D graph;
    Model m;
    Observable o;
    java.util.List<Arc2D> arcs;
    java.util.List<Rectangle2D> rects;
    java.util.List<String> stringstoDraws;

    JFrame frame;
    Line2D line;
    Graphics2D graph;
     double X=400;
     double Y=400;
    static final double R=200;
    //double angle;
    Color[] colors={Color.YELLOW,Color.BLUE,Color.CYAN,Color.GREEN,Color.RED};

    Arc2D arcblanc,arccentre,arcCurrent;
    java.util.List<Rectangle2D> rectShowList;

    Button btnNext;
    Button btnPrev;

    double widthRect=150;
    double heightRect=100;

    int currentAcr=-1;

    @Override
    public void setCurrentAcrIndex(int currentAcr) {
        this.currentAcr = currentAcr;
    }

    public ViewCam(Model m) {

        this.m=m;
        this.o=(ModelImpl) m;
        o.addObserver(this);
        arcCurrent=null;
        arcs=new ArrayList<Arc2D>();
        rects=new ArrayList<Rectangle2D>();
        rectShowList=new ArrayList<>();
        stringstoDraws=new ArrayList<>();

        frame=new JFrame();
        btnNext=new Button("Next");
        btnPrev=new Button("Previous");
        frame.setBounds(0,0,800,800);

        btnNext.setBounds(120,20,100,30);
        btnPrev.setBounds(10,20,100,30);
        btnNext.setEnabled(true);
        btnNext.setVisible(true);
        btnPrev.setVisible(true);
        btnPrev.setEnabled(true);
        frame.add(btnPrev);
        frame.add(btnNext);
        frame.add(this);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        X=getWidth()/2;
        Y=getHeight()/2;



    }

    @Override
    protected void paintComponent(Graphics g){

        graph = (Graphics2D) g;

        createArcs(graph);
        drawRectList(graph);

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
        graph.drawString(( m.getTotalValue())+" Euros",xCoordinate,yCoordinate+30);

        graph.setPaint(Color.BLACK);
        //drawStrings(graph);


    }

    private void drawStrings(Rectangle2D rect, int index) {
        double x=rect.getX();
        double y=rect.getY();
        graph.setPaint(Color.BLACK);
        String s=stringstoDraws.get(index);
        String[] strings=s.split("\n");
        for(int i=0;i<strings.length;i++) {
            graph.drawString(strings[i],(int) (x + widthRect / 3), (int) (y + heightRect / 3+i*20));
        }
        graph.setPaint(Color.MAGENTA);

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
        int num=0;
        while (iter.hasNext()){
            Arc2D arc = new Arc2D.Double();

            double angle= (iter.next().getValue()  * 360 / m.getTotalValue());

            anglestop=anglestart+angle;

            if(num==this.currentAcr){
                arc.setArcByCenter(X,Y,R+20,anglestart,angle,Arc2D.PIE);
                arcs.add(arc);
            }
            else {
                arc.setArcByCenter(X,Y,R,anglestart,angle,Arc2D.PIE);
                arcs.add(arc);
            }
            num++;
            graph.setPaint(nextcolor(indexcolor));

            System.out.println("indexColor "+indexcolor);
            indexcolor++;


            anglestart=anglestop;


            graph.fill(arc);

            //create rects
            Rectangle2D rect=createRect(arc);
            rects.add(rect);

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
    public void showItemInfo(int index) {
        Item item=m.getItem(index);
        System.out.println(item.getIntitule());
        System.out.println(item.getDescription());
        System.out.println(item.getValue());

        String intitule=item.getIntitule();
        String decl=item.getDescription();
        double value=item.getValue();



        String toDraw=intitule+"\n"+decl+"\n"+value;

        stringstoDraws.add(toDraw);

        //graph.drawString(toDraw,(int) x,(int)y+10);
        //rectShowList.add(rects.get(index));
        repaint();


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


    public void setBtnNext(Button btnNext) {
        this.btnNext = btnNext;
    }

    public void setBtnPrev(Button btnPrev) {
        this.btnPrev = btnPrev;
    }

    @Override
    public Button getBtnNext() {
        return btnNext;
    }

    @Override
    public Button getBtnPrev() {
        return btnPrev;
    }

    @Override
    public Graphics2D getGraphics(){
        return this.graph;
    }




    public int transformPosition(double angle){

        if(0<=angle && angle<=90) {
            return 1;
        }
        else if(angle>90 && angle<=180){
            return 2;
        }
        else if(angle>180 && angle<=270){
            return 3;
        }
        else if(angle >270 && angle <=361){
            return 4;
        }
        else {
            return -1;
        }
    }

    public Rectangle2D createRect(Arc2D arc){
        double r=arc.getWidth()/2;
        Rectangle2D rect=new Rectangle2D.Double();
        double anglestart=arc.getAngleStart();
        double angle=arc.getAngleExtent();
        double angleRect=anglestart+angle/2;
        double xr=arc.getCenterX()+r*Math.cos(angleRect/180*3.14);
        double yr=arc.getCenterY()-r*Math.sin(angleRect/180*3.14);

        switch (transformPosition(angleRect)){
            case 1: yr=yr-heightRect;break;
            case 2: yr=yr-heightRect;xr=xr-widthRect;break;
            case 3: xr=xr-widthRect;break;
            case 4: break;
            default:break;
        }
        rect.setRect(xr,yr,widthRect,heightRect);
        //graph.drawRect((int) xr, (int) yr, (int) widthRect, (int) heightRect);


        //System.out.println(xr+" zuobiao "+yr+"   ");

        return rect;

    }

    private void drawRectList(Graphics2D graph){
        Iterator<Rectangle2D> iter=rectShowList.iterator();
        while(iter.hasNext()){
            Rectangle2D rect=iter.next();
            int index=rectShowList.indexOf(rect);

            drawStrings(rect,index);
            //graph.setPaint(Color.BLACK);
            graph.draw(rect);

        }

    }


    public List<Rectangle2D> getRectShowList() {
        return rectShowList;
    }

    public List<Rectangle2D> getRects() {
        return rects;
    }




}
