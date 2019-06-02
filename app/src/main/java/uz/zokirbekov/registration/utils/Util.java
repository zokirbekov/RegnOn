package uz.zokirbekov.registration.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

public class Util {
    public static void showSnackBar(View mainView, String msg)
    {
        Snackbar.make(mainView,msg,Snackbar.LENGTH_LONG).show();
    }
}
