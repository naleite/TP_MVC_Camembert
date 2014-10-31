package view;

import model.Item;
import model.Model;
import model.ModelImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
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

    List<Arc2D> arcs;
    List<Rectangle2D> rects;
    List<String> stringstoDraws;
    List<Rectangle2D> rectShowList;

    JFrame frame;
    Graphics2D graph;

     double X=400;
     double Y=400;
    static final double R=200;

    Color[] colors={Color.YELLOW,Color.BLUE,Color.CYAN,Color.GREEN,Color.RED,Color.ORANGE,Color.PINK,Color.MAGENTA};
    Arc2D arcblanc,arccentre;

    Button btnNext;
    Button btnPrev;

    double widthRect=150;
    double heightRect=100;

    int currentAcr=-1;

    public ViewCam() {




        arcs=new ArrayList<>();
        rects=new ArrayList<>();
        rectShowList=new ArrayList<>();
        stringstoDraws=new ArrayList<>();

        frame=new JFrame();
        frame.setBounds(0,0,800,800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnNext=new Button("Next");
        btnPrev=new Button("Previous");
        btnNext.setBounds(120,20,100,30);
        btnPrev.setBounds(10,20,100,30);
        btnNext.setEnabled(true);
        btnNext.setVisible(true);
        btnPrev.setVisible(true);
        btnPrev.setEnabled(true);


        frame.add(btnPrev);
        frame.add(btnNext);
        frame.add(this);

        X=frame.getWidth()/2;
        Y=frame.getHeight()/2;



    }

    @Override
    protected void paintComponent(Graphics g){

        graph = (Graphics2D) g;

        createArcs(graph);
        drawRectList(graph);

        //desiner un arc blanc
        arcblanc=new Arc2D.Double();
        arcblanc.setArcByCenter(X,Y,R*2/3,0,360,Arc2D.PIE);
        graph.setPaint(Color.WHITE);
        graph.fill(arcblanc);

        //desiner un arc au centre
        arccentre=new Arc2D.Double();
        arccentre.setArcByCenter(X,Y,R/2,0,360,Arc2D.PIE);
        graph.setPaint(Color.MAGENTA);
        graph.fill(arccentre);

        //desiner le titre, la desc et la valeur du modele.
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



    }

    private void createArcs(Graphics2D graph){

        arcs.clear();
        Iterator<Item> iter=m.getItemsIterator();
        //System.out.println("nb items:"+m.getNbItems());
        double anglestart=0;
        int indexcolor=0;
        double anglestop=0;
        int num=0;
        while (iter.hasNext()){
            Arc2D arc = new Arc2D.Double();
            double angle= (iter.next().getValue()  * 360 / m.getTotalValue());//calcul l'angle du chaque arc
            anglestop=anglestart+angle;

            //If this arc is clicked or picked by buttons, larger..
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
            //System.out.println("indexColor "+indexcolor);
            indexcolor++;
            anglestart=anglestop;
            graph.fill(arc);//draw it...

            //create rect et ajouter dans la liste
            Rectangle2D rect=createRect(arc);
            rects.add(rect);

        }

    }

    private Rectangle2D createRect(Arc2D arc){
        double r=arc.getWidth()/2;
        Rectangle2D rect=new Rectangle2D.Double();
        double anglestart=arc.getAngleStart();
        double angle=arc.getAngleExtent();
        double angleRect=anglestart+angle/2;
        //Calculer le milieu de l'arc
        double xr=arc.getCenterX()+r*Math.cos(angleRect/180*3.14);
        double yr=arc.getCenterY()-r*Math.sin(angleRect/180*3.14);

        //transformer le rect pour la bonne position.
        switch (transformPosition(angleRect)){
            case 1: yr=yr-heightRect;break;
            case 2: yr=yr-heightRect;xr=xr-widthRect;break;
            case 3: xr=xr-widthRect;break;
            case 4: break;
            default:break;
        }
        rect.setRect(xr,yr,widthRect,heightRect);

        return rect;

    }

    private void drawRectList(Graphics2D graph){
        Iterator<Rectangle2D> iter=rectShowList.iterator();
        while(iter.hasNext()){
            Rectangle2D rect=iter.next();
            int index=rectShowList.indexOf(rect);

            drawStringsInRect(rect,index);
            //graph.setPaint(Color.BLACK);
            graph.draw(rect);

        }

    }

    private void drawStringsInRect(Rectangle2D rect, int index) {
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
    public Arc2D getArcBlanc() {
        return this.arcblanc;
    }

    @Override
    public Arc2D getArcCentre() {
        return this.arccentre;
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


    @Override
    public void setCurrentAcrIndex(int currentAcr) {
        this.currentAcr = currentAcr;
    }

    @Override
    public void setModel(Model m) {
        this.m=m;
        this.o=(ModelImpl) m;
        o.addObserver(this);
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

    public List<Rectangle2D> getRectShowList() {
        return rectShowList;
    }

    public List<Rectangle2D> getRects() {
        return rects;
    }




}
