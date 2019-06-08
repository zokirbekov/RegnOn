package uz.zokirbekov.registration.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uz.zokirbekov.registration.R;
import uz.zokirbekov.registration.RegistrationActivity;
import uz.zokirbekov.registration.managers.RequestManager;
import uz.zokirbekov.registration.models.Person;
import uz.zokirbekov.registration.utils.MD5;
import uz.zokirbekov.registration.utils.Util;

public class RegistrationFragment extends Fragment implements RequestManager.IResponse<String> {

    @BindView(R.id.editText_surname)
    TextInputEditText editTextSurname;

    @BindView(R.id.editText_name)
    TextInputEditText editTextName;

    @BindView(R.id.editText_pass_sery)
    TextInputEditText editTextPassSery;

    @BindView(R.id.editText_pass_num)
    TextInputEditText editTextPassNumb;

    @BindView(R.id.editText_login)
    TextInputEditText editTextLogin;

    @BindView(R.id.editText_password)
    TextInputEditText editTextPassword;

    @OnClick(R.id.button)
    void onRegistrationClick()
    {
        String passport = editTextPassSery.getText().toString() + editTextPassNumb.getText().toString();
        String password = editTextPassword.getText().toString().trim();
        password = MD5.encrypt(password);

        Person person = new Person();
        person.setName(editTextName.getText().toString().trim());
        person.setSurname(editTextSurname.getText().toString().trim());
        person.setLogin(editTextLogin.getText().toString().toLowerCase().trim());
        person.setPassword(password);
        person.setPassport(passport);

        ((RegistrationActivity)getActivity()).isProgress(true);
        RequestManager.getInstance().insertPerson(person, this);
    }

    @OnClick(R.id.textView_login)
    void onLoginClick()
    {
        MainRegistrationFragment fragment = (MainRegistrationFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.content);
        fragment.switchFragment(0);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_registration, container, false);
        ButterKnife.bind(this,v);
        return v;
    }

    @Override
    public void success(String object) {
        ((RegistrationActivity)getActivity()).isProgress(false);
        //((RegistrationActivity)getActivity()).goToMain(object);
    }

    @Override
    public void error(String msg) {
        ((RegistrationActivity)getActivity()).isProgress(false);
        Util.showSnackBar(getView(),msg);
    }
}
