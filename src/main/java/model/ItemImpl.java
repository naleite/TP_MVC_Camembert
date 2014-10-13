package model;

/**
 * Created by naleite on 13/10/14.
 */
public class ItemImpl implements Item {

    private String intitule="Intitule";
    private String description="Nothing.";
    private int value=0;

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
