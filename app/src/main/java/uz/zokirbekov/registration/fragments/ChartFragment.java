package uz.zokirbekov.registration.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uz.zokirbekov.registration.MainActivity;
import uz.zokirbekov.registration.R;
import uz.zokirbekov.registration.managers.RequestManager;
import uz.zokirbekov.registration.models.StatisticModel;

public class ChartFragment extends Fragment implements RequestManager.IResponse<List<StatisticModel>> {

    private int type;

    public static final int YEAR = 1;
    public static final int MONTH = 2;
    public static final int WEAK = 3;
    public static final int DAY = 4;
    public static final int ALL = 5;

    @BindView(R.id.text_title)
    TextView textTitle;

    @BindView(R.id.chart)
    AnyChartView chartView;

    public static ChartFragment newInstance(int type)
    {
        ChartFragment chartFragment = new ChartFragment();
        chartFragment.setType(type);
        return chartFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chart,container,false);
        ButterKnife.bind(this,v);
        ((MainActivity)getActivity()).isProgress(true);
        getStatistic();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void getStatistic()
    {
        RequestManager.getInstance().getStatistic(type,this);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private void drawChart(List<StatisticModel> object)
    {
        Pie pie = AnyChart.pie();

        List<DataEntry> datas = new ArrayList<>();

        for (StatisticModel i : object) {
            datas.add(new ValueDataEntry(i.getPerson().getName(),i.getCount()));
        }

        pie.data(datas);
        chartView.setChart(pie);
    }

    @Override
    public void success(List<StatisticModel> object) {
        ((MainActivity)getActivity()).isProgress(false);
        drawChart(object);
    }

    @Override
    public void error(String msg) {
        ((MainActivity)getActivity()).isProgress(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity)getActivity()).isProgress(false);
    }
}
