package view;

import model.Model;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by naleite on 14-10-13.
 */
public interface View extends Observer{

    public MouseListener[] getMouseListener() ;

    public void setMouseListener(MouseListener mouseListener);

    void showItemInfo(int index);

    public ArrayList<Arc2D> getArcsList();

    public Arc2D getArcBlanc();

    public Arc2D getArcCentre();

    public Button getBtnNext();
    public Button getBtnPrev();

    public java.util.List<Rectangle2D> getRectShowList();

    public List<Rectangle2D> getRects();

    public void setCurrentAcrIndex(int currentAcr);

    void setModel(Model m);
}
