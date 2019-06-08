package uz.zokirbekov.registration.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uz.zokirbekov.registration.MainActivity;
import uz.zokirbekov.registration.R;
import uz.zokirbekov.registration.managers.RequestManager;
import uz.zokirbekov.registration.models.Person;
import uz.zokirbekov.registration.ui.PersonImageText;
import uz.zokirbekov.registration.utils.QR;
import uz.zokirbekov.registration.utils.Util;

public class QrFragment extends Fragment implements RequestManager.IResponse<Person> {

    @BindView(R.id.text_name)
    TextView textName;

    @BindView(R.id.text_surname)
    TextView textSurname;

    @BindView(R.id.text_passport)
    TextView textPassport;

    @BindView(R.id.image_qr)
    ImageView imageQr;

    @BindView(R.id.person_image)
    PersonImageText personImageText;

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
        getPerson(p);
    }

    private void getPerson(int id)
    {
        ((MainActivity)getActivity()).isProgress(true);
        RequestManager.getInstance().getPerson(id,this);
    }

    private void personToView(Person person)
    {
        textName.setText(person.getName());
        textSurname.setText(person.getSurname());
        textPassport.setText(person.getPassport());
        personImageText.setText(String.valueOf(person.getName().charAt(0)));
        try {
            Bitmap image =  QR.getInstance(getActivity()).generateQrCode(person, 100, 100);
            imageQr.setImageBitmap(image);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.qrScan)
    void scanClick()
    {
        QR.getInstance(getActivity()).scanQr();
    }

    @Override
    public void success(Person object) {
        if (object != null)
            personToView(object);
        ((MainActivity)getActivity()).isProgress(false);
    }

    @Override
    public void error(String msg) {
        ((MainActivity)getActivity()).isProgress(false);
        Util.showSnackBar(getView(),msg);
    }
}
