package sample;

/**
 * Created by Marcello395 on 2/12/17.
 */


/*
Entry class for the HashTable
 */
public class Entry<k, v> {

    //instantiates the parameters of the table
    protected String key;
    protected Double value;
    Entry<k, v> next = null;

    //constructors
    public Entry(String key, Double value) {
        super();
        this.key = key;
        this.value = value;
    }

    /*
    Getters and setters
     */
    public void setValue(Double value) {this.value = value;}

    public Double getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    public Entry<k, v> getNext() {
        return next;
    }
}
