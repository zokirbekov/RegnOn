package uz.zokirbekov.registration.fragments;

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

import com.google.zxing.WriterException;

import butterknife.BindView;
import butterknife.ButterKnife;
import uz.zokirbekov.registration.App;
import uz.zokirbekov.registration.R;
import uz.zokirbekov.registration.models.Person;
import uz.zokirbekov.registration.ui.PersonImageText;
import uz.zokirbekov.registration.utils.QR;

public class AboutFragment extends Fragment {

    @BindView(R.id.person_image)
    PersonImageText personImageText;

    @BindView(R.id.text_name)
    TextView textName;

    @BindView(R.id.text_surname)
    TextView textSurname;

    @BindView(R.id.text_passport)
    TextView textPassport;

    @BindView(R.id.text_login)
    TextView textLogin;

    @BindView(R.id.text_ball)
    TextView textBall;

    @BindView(R.id.image_qr)
    ImageView imageQr;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about,container,false);
        ButterKnife.bind(this,v);
        setPersonInfo();
        return v;
    }

    private void setPersonInfo()
    {
        Person p = ((App)getActivity().getApplicationContext()).getPerson();

        personImageText.setText(String.valueOf(p.getName().charAt(0)));
        textName.setText(p.getName());
        textSurname.setText(p.getSurname());
        textLogin.setText(p.getLogin());
        textBall.setText(String.valueOf(p.getBall()));
        textPassport.setText(p.getPassport());

        try {
            Bitmap image = QR.getInstance(getActivity()).generateQrCode(p,150,150);
            imageQr.setImageBitmap(image);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
