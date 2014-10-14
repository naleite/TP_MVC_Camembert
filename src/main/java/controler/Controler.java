package controler;

import model.Model;
import view.View;
import view.ViewCam;

/**
 * Created by naleite on 13/10/14.
 */
public interface Controler {


    public void setModel(Model m);
    public Model getModel();
    public void addMouseListener();
    public View getView();
    public void setView(ViewCam v);



    

}
