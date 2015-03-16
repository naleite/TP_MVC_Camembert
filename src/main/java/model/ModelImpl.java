package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

/**
 * Created by naleite on 13/10/14.
 */

public class ModelImpl extends Observable implements Model {

    private String titre="Sans Titre";
    private List<Item> items;
    //private View view;

    private int currentItemIndex;

    public ModelImpl(String titre) {

        items=new ArrayList<Item>();

        currentItemIndex=-1;
        this.titre=titre;

    }

    @Override
    public String getTitre() {
        return titre;
    }

    @Override
    public void setTitre(String titre) {
        this.titre = titre;
        super.setChanged();
        super.notifyObservers();
    }

    @Override
    public Item getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }

    }

    @Override
    public Item getCurrentItem(){
        return getItem(currentItemIndex);

    }

    @Override
    public Item getNextItem(){
        currentItemIndex=((this.currentItemIndex+1) % items.size());
        return getItem(currentItemIndex);
    }

    @Override
    public Item getPrevItem(){
        if (currentItemIndex==0){
            currentItemIndex=items.size()-1;
            return getItem(currentItemIndex);
        }
        else{
            currentItemIndex--;
            return getItem(currentItemIndex);
        }


    }

    @Override
    public void addItem(Item item){
        this.items.add(item);
        super.setChanged();
        super.notifyObservers();
    }

    @Override
    public Iterator<Item> getItemsIterator(){
        return this.items.iterator();
    }

    @Override
    public void remove(Item item){
        this.items.remove(item);
        super.setChanged();
        super.notifyObservers();
    }


    @Override
    public double getTotalValue(){
        double sum=0.;
        Iterator<Item> iter=items.iterator();
        while (iter.hasNext()){
            sum+=iter.next().getValue();
        }
        return sum;
    }

    @Override
    public double getItemValue(int index) {
        return items.get(index).getValue();
    }

    @Override
    public int getNbItems() {
        return items.size();
    }


    @Override
    public int getCurrentIndex() {
        return this.currentItemIndex;
    }


    @Override
    public void setIndexAsCurrent(int index) {
        if(index>=0 && index<items.size()){
            currentItemIndex=index;
        }
        else{
            throw new ArrayIndexOutOfBoundsException("Can not set this index.");
        }
    }
}
