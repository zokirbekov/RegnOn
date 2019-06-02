package uz.zokirbekov.registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uz.zokirbekov.registration.models.Person;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        goToMain(null);
    }

    public void goToMain(Person p)
    {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("person",p);
        startActivity(intent);
    }
}
