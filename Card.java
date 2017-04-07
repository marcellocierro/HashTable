package sample;

/**
 * Created by Marcello395 on 2/12/17.
 */


//Card class, acts as helper for card properties
public class Card {

    String name;
    int cost, health, attack;
    double value;

    public Card(String n, int c, int h, int a, double v) {
        name = n;
        cost = c;
        health = h;
        attack = a;
        value = v;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return ((cost * .25)+(health *.45)+(attack * .30));
    }


    //tostring for debugging
    @Override
    public String toString() {
        return "Card: " +
                "Name = " + name + '\'' +
                ", Cost = " + cost +
                ", Health = " + health +
                ", Attack = " + attack +
                ", Value = " + value
                ;
    }
}
