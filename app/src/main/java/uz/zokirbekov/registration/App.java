package uz.zokirbekov.registration;

import android.app.Application;

import uz.zokirbekov.registration.models.Person;

public class App extends Application {

    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
