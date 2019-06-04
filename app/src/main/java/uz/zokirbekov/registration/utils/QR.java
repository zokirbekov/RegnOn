package uz.zokirbekov.registration.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import uz.zokirbekov.registration.models.Person;

public class QR {
    private static QR instance;
    private Activity act;

    public static QR getInstance(Activity ac) {
        if (instance == null)
            instance = new QR();
        instance.act = ac;
        return instance;
    }

    public void scanQr()
    {
        IntentIntegrator integrator = new IntentIntegrator(act);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scan a QR code");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    public int getContent(int requestCode,int resultCode, Intent data)
    {
        int person = 0;
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null)
        {
            if (result.getContents() != null)
            {
                String content = result.getContents();
                person = getContent(content);
            }
        }
        return person;
    }

    public Bitmap generateQrCode(Person p) throws WriterException
    {
        BarcodeEncoder encoder = new BarcodeEncoder();
        Bitmap bitmap = encoder.encodeBitmap(String.valueOf(p.getId()), BarcodeFormat.QR_CODE, 200,200);
        return bitmap;
    }

    private int getContent(String content)
    {
        return Integer.valueOf(content);
    }

}
