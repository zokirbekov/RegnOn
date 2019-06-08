package uz.zokirbekov.registration.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uz.zokirbekov.registration.App;
import uz.zokirbekov.registration.MainActivity;
import uz.zokirbekov.registration.R;
import uz.zokirbekov.registration.adapters.UsersAdapter;
import uz.zokirbekov.registration.managers.RequestManager;
import uz.zokirbekov.registration.models.Person;
import uz.zokirbekov.registration.utils.Util;

public class UsersFragment extends Fragment implements RequestManager.IResponse<List<Person>> {

    public static String TAG = "UsersFragment";

    @BindView(R.id.listView)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_users,container,false);
        ButterKnife.bind(this,v);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getUsers();
        return v;
    }

    @OnClick(R.id.add_button)
    void addClick()
    {
        AddUserFragment fragment = new AddUserFragment();
        fragment.show(getFragmentManager(),AddUserFragment.TAG);
    }

    private void initListView(List<Person> people)
    {
        if (people != null) {
            UsersAdapter adapter = new UsersAdapter(getContext(), people);
            recyclerView.setAdapter(adapter);
        }
    }

    private void getUsers()
    {
        Person p = ((App)getContext().getApplicationContext()).getPerson();
        int id = p.getId();
        ((MainActivity)getActivity()).isProgress(true);
        RequestManager.getInstance().getPersonUser(id,this);
    }

    @Override
    public void success(List<Person> object) {
        initListView(object);
        ((MainActivity)getActivity()).isProgress(false);
    }

    @Override
    public void error(String msg) {
        Util.showSnackBar(getView(),msg);
        ((MainActivity)getActivity()).isProgress(false);
    }
}
