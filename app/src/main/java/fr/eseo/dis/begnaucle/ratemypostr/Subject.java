package fr.eseo.dis.begnaucle.ratemypostr;

/**
 * Created by cleme on 04/01/2018.
 */

public interface Subject {
    public void addObserver(Observer obs);
    public void removeObserver(Observer obs);
}
