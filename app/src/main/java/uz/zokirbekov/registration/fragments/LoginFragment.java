package uz.zokirbekov.registration.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import uz.zokirbekov.registration.models.RegistrationRequest;
import uz.zokirbekov.registration.utils.MD5;
import uz.zokirbekov.registration.utils.Util;

public class LoginFragment extends Fragment implements RequestManager.IResponse<Person> {

    @BindView(R.id.editText_login)
    TextInputEditText editTextLogin;

    @BindView(R.id.editText_password)
    TextInputEditText editTextPassword;

    @OnClick(R.id.textView_registration)
    void onRegistrationClick()
    {
       MainRegistrationFragment fragment = (MainRegistrationFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.content);
       fragment.switchFragment(1);
    }

    @OnClick(R.id.button)
    void onLoginClick()
    {
        String login = editTextLogin.getText().toString().toLowerCase().trim();
        String password =  editTextPassword.getText().toString().toLowerCase().trim();
        password = MD5.encrypt(password);
        RegistrationRequest request = new RegistrationRequest();

        request.setLogin(login);
        request.setPassword(password);

        ((RegistrationActivity)getActivity()).isProgress(true);
        RequestManager.getInstance().getPersonByLogin(request,this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login,container,false);
        ButterKnife.bind(this,v);
        return v;
    }

    @Override
    public void success(Person object) {
        if (object != null) {
            ((RegistrationActivity)getActivity()).goToMain(object);
        }
        else
        {
            Util.showSnackBar(getView(),"Login or password incorrect");
        }
        ((RegistrationActivity)getActivity()).isProgress(false);

    }

    @Override
    public void error(String msg) {
        ((RegistrationActivity)getActivity()).isProgress(false);
        Util.showSnackBar(getView(),msg);
    }
}
