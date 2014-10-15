package controler;

import model.Model;
import view.View;
import view.ViewCam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

/**
 * Created by naleite on 13/10/14.
 */
public class ControlerImpl implements Controler{

    Model m;
    View view;
    MouseListener mouse;
    AffineTransform rot=new AffineTransform();

    public ControlerImpl(Model m, View v){
        this.m=m;
        this.view=v;
        addMouseListener();
        addButtonListener();

    }
    @Override
    public void setModel(Model m) {

        this.m=m;
    }

    @Override
    public Model getModel() {
        return this.m;
    }

    private void addMouseListener(){
        MouseListener mouse=new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.println("Mouse Clicked");
                int x=e.getX();
                int y=e.getY();
                //int index;


                ArrayList<Arc2D> arcs=view.getArcsList();

                for(int i=0;i<arcs.size();i++){
                    if(arcs.get(i).contains(x,y) && !view.getArcBlanc().contains(x,y)
                            && !view.getArcCentre().contains(x,y)){
                        m.setIndexAsCurrent(i);


                        double anst=arcs.get(i).getAngleStart();
                        double anex=arcs.get(i).getAngleExtent();
                        //System.out.println(arcs.get(i).getAngleStart()+"   "+arcs.get(i).getAngleExtent());
                        //double xr=arcs.get(i).getCenterX()+200*Math.cos((anex+anst)/360);
                        //double yr=arcs.get(i).getCenterY()+200*Math.sin((anex+anst)/360);
                        //System.out.println(xr+" zuobiao "+yr+"   "+Math.cos(90/360));


                        view.showItemInfo(m.getCurrentItem());
                        break;

                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {



            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };

        view.setMouseListener(mouse);


    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    public void setView(ViewCam v) {

        this.view=v;
    }

    public void addButtonListener(){

        ActionListener actionNext=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                view.showItemInfo(m.getNextItem());


            }
        };

        ActionListener actionPrev=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showItemInfo(m.getPrevItem());



            }
        };

        view.getBtnNext().addActionListener(actionNext);
        view.getBtnPrev().addActionListener(actionPrev);


    }





}
