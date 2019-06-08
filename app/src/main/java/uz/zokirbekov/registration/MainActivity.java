package uz.zokirbekov.registration;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uz.zokirbekov.registration.fragments.AboutFragment;
import uz.zokirbekov.registration.fragments.AddUserFragment;
import uz.zokirbekov.registration.fragments.QrFragment;
import uz.zokirbekov.registration.fragments.StatisticsFragment;
import uz.zokirbekov.registration.fragments.UsersFragment;
import uz.zokirbekov.registration.models.Person;
import uz.zokirbekov.registration.ui.PersonImageText;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.main_content)
    FrameLayout content;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    PersonImageText personImageText;

    TextView textBall;
    TextView textFullName;

    QrFragment qrFragment = new QrFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View v = navigationView.getHeaderView(0);

        personImageText = v.findViewById(R.id.person_image);
        textBall = v.findViewById(R.id.textBall);
        textFullName = v.findViewById(R.id.textFullName);

        navigationView.setNavigationItemSelectedListener(this);
        ButterKnife.bind(this);
        App app = ((App)getApplicationContext());
        Person p = getIntent().getParcelableExtra("person");
        app.setPerson(p);
        setPersonInfos(p);
        progressBar.getIndeterminateDrawable().setColorFilter(ActivityCompat.getColor(this,R.color.primaryDark2), PorterDuff.Mode.MULTIPLY);
        switchFragment(new UsersFragment());
    }

    public void isProgress(boolean b)
    {
        if (b)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.INVISIBLE);
    }

    private void setPersonInfos(Person p)
    {
        personImageText.setText(String.valueOf(p.getName().charAt(0)));
        textBall.setText(String.valueOf(p.getBall()));
        String fullName = p.getName() + " " + p.getSurname();
        textFullName.setText(fullName);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.users) {
            switchFragment(new UsersFragment());
        } else if (id == R.id.add_user) {
            new AddUserFragment().show(getSupportFragmentManager(),AddUserFragment.TAG);
        } else if (id == R.id.statistics) {
            switchFragment(new StatisticsFragment());
        } else if (id == R.id.qr) {
            switchFragment(qrFragment);
        } else if (id == R.id.about) {
            switchFragment(new AboutFragment());
        } else if (id == R.id.logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void switchFragment(Fragment fragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.main_content,fragment);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        qrFragment.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
