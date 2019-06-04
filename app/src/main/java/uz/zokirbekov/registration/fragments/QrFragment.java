package uz.zokirbekov.registration.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import uz.zokirbekov.registration.R;
import uz.zokirbekov.registration.models.Person;
import uz.zokirbekov.registration.utils.QR;

public class QrFragment extends Fragment {



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_qr,container,false);
        ButterKnife.bind(this,v);
        QR.getInstance(getActivity()).scanQr();
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        int p = QR.getInstance(getActivity()).getContent(requestCode,resultCode,data);
        System.out.println();
    }

}
