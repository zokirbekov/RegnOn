package uz.zokirbekov.registration.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uz.zokirbekov.registration.R;
import uz.zokirbekov.registration.adapters.RegistrationFragmentsAdapter;

public class MainRegistrationFragment extends Fragment {

    public static String TAG = "MainRegistrationFragment";

    @BindView(R.id.viewPager)
    ViewPager pager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_registration_main,container,false);
        ButterKnife.bind(this,v);
        initPager();
        return v;
    }
    private void initPager()
    {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add( new LoginFragment());
        fragments.add( new RegistrationFragment());
        RegistrationFragmentsAdapter adapter = new RegistrationFragmentsAdapter(fragments, getFragmentManager());
        pager.setAdapter(adapter);
    }

    public void switchFragment(int i)
    {
        pager.setCurrentItem(i);
    }

}
