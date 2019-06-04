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
import uz.zokirbekov.registration.R;

public class AddUserFragment extends DialogFragment {

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
}
