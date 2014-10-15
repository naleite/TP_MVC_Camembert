package model;

import java.util.Iterator;
import java.util.Observable;

/**
 * Created by naleite on 13/10/14.
 */
public interface Model {

    public String getTitre();

    public void setTitre(String titre);

    public Item getItem(int index);

    public Item getCurrentItem();

    public Item getNextItem();

    public Item getPrevItem();

    public void addItem(Item item);

    public Iterator<Item> getItemsIterator();

    public void remove(Item item);

    public double getTotalValue();

    public double getItemValue(int index);

    public int getNbItems();

    public int getCurrentIndex();

    public void setIndexAsCurrent(int index);
}
