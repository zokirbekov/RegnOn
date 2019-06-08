package uz.zokirbekov.registration.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uz.zokirbekov.registration.App;
import uz.zokirbekov.registration.MainActivity;
import uz.zokirbekov.registration.R;
import uz.zokirbekov.registration.managers.RequestManager;
import uz.zokirbekov.registration.models.PersonUser;
import uz.zokirbekov.registration.utils.MD5;

public class AddUserFragment extends DialogFragment implements RequestManager.IResponse<String> {

    public static String TAG = "AddUserFragment";

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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_user,container,false);
        ButterKnife.bind(this,v);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.border_primary);
        return v;
    }

    @OnClick(R.id.button)
    void addClick()
    {
        PersonUser user = new PersonUser();
        String passport = editTextPassSery.getText().toString().trim() + editTextPassNumb.getText().toString().trim();
        int id = ((App)getActivity().getApplicationContext()).getPerson().getId();
        user.setName(editTextName.getText().toString().trim());
        user.setSurname(editTextSurname.getText().toString().trim());
        user.setLogin(editTextLogin.getText().toString().toLowerCase().trim());
        user.setPassword(MD5.encrypt(editTextName.getText().toString().trim()));
        user.setPassport(passport);
        user.setWhoAdd(id);

        ((MainActivity)getActivity()).isProgress(true);

        RequestManager.getInstance().insertPersonUser(user,this);
    }

    @Override
    public void success(String object) {
        ((MainActivity)getActivity()).isProgress(false);
        dismiss();
    }

    @Override
    public void error(String msg) {
        ((MainActivity)getActivity()).isProgress(false);
        dismiss();
    }
}
