package model;

import view.View;

import java.util.*;

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

        currentItemIndex=0;
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



}
