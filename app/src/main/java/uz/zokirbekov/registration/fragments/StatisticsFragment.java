package uz.zokirbekov.registration.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anychart.core.Chart;

import butterknife.BindView;
import butterknife.ButterKnife;
import uz.zokirbekov.registration.R;

public class StatisticsFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottom_nav_bar)
    BottomNavigationView navBar;

    @BindView(R.id.text_title)
    TextView textTitle;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_statistics,container,false);
        ButterKnife.bind(this,v);
        navBar.setOnNavigationItemSelectedListener(this);
        return v;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ChartFragment fragment = null;
        switch (item.getItemId())
        {
            case R.id.statistics_year : fragment = ChartFragment.newInstance(ChartFragment.YEAR); textTitle.setText("Year"); break;
            case R.id.statistics_month: fragment = ChartFragment.newInstance(ChartFragment.MONTH); textTitle.setText("Month"); break;
            case R.id.statistics_weak : fragment = ChartFragment.newInstance(ChartFragment.WEAK); textTitle.setText("Weak"); break;
            case R.id.statistics_day :  fragment = ChartFragment.newInstance(ChartFragment.DAY); textTitle.setText("Day"); break;
            case R.id.statistics_all :  fragment = ChartFragment.newInstance(ChartFragment.ALL); textTitle.setText("All"); break;
        }
        if (fragment != null) {
            ft.replace(R.id.content, fragment);
            ft.commit();
        }
        return true;
    }
}
