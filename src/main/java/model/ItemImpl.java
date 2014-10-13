package model;

/**
 * Created by naleite on 13/10/14.
 */
public class ItemImpl implements Item {

    private String intitule="Intitule";
    private String description="Nothing.";
    private double value=0.;

    public ItemImpl(String intitule, String description, double value) {
        this.intitule = intitule;
        this.description = description;
        this.value = value;
    }

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

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
