package view;

import model.Item;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Observer;

/**
 * Created by naleite on 14-10-13.
 */
public interface View extends Observer{

    public MouseListener[] getMouseListener() ;

    public void setMouseListener(MouseListener mouseListener);

    void showItemInfo(Item item);

    public ArrayList<Arc2D> getArcsList();

    public JFrame getFrame();
}
