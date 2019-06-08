package uz.zokirbekov.registration.models;

public class StatisticModel {
    private Person person;
    private int count;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
