package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by naleite on 13/10/14.
 */

public class ModelImpl implements Model {

    private String titre="Sans Titre";
    private List<Item> items;

    private int currentItemIndex;

    public ModelImpl(String titre) {

        items=new ArrayList<Item>();

        currentItemIndex=0;
        this.titre=titre;

    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Item getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }

    }

    public Item getCurrentItem(){
        return getItem(currentItemIndex);

    }

    @Override
    public int getNextItemIndex(){
        currentItemIndex=((this.currentItemIndex+1) % items.size());
        return currentItemIndex;
    }

    @Override
    public int getPrevItemIndex(){
        if (currentItemIndex==0){
            currentItemIndex=items.size()-1;
            return currentItemIndex;
        }
        else{
            currentItemIndex--;
            return currentItemIndex;
        }


    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public Iterator<Item> getItemsIterator(){
        return this.items.iterator();
    }

    public void remove(Item item){
        this.items.remove(item);
    }


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
}
