package com.example.khalid.CoPilot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import static android.os.SystemClock.sleep;


public class MainPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LogoutListener {


    private DrawerLayout drawer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp x = (( MyApp ) getApplication ());
        x.registerSessionListener ( this );
        x.startUserSession ();

        privSingleton var= privSingleton.getInstance();
        int priv = Integer.parseInt(var.getValue());
        setContentView(R.layout.activity_main_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Log.d("privtest",String.valueOf(priv));
        if(priv < 1) {
            //BackgroundTask backgroundTask = new BackgroundTask(this);
            //backgroundTask.execute("lecs", var.getIDValue());

            hideItem(R.id.nav_Dashboard_inst);
            hideItem(R.id.nav_inst_Lecs);
            hideItem(R.id.nav_inst_Entrance);
            hideItem(R.id.nav_notifications_inst);
        }else if(priv ==1){
            hideItem(R.id.nav_Dashboard_stu);
            hideItem(R.id.nav_stu_Entrance);
            hideItem(R.id.nav_stu_LecSchedule);
            hideItem(R.id.nav_notifications);
        }

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            switch (priv){
                case 0:
                    BackgroundTask backgroundTask = new BackgroundTask(this);
                    backgroundTask.execute("lecs", var.getIDValue());
                    checkNotif();
                    sleep(1500);
                    navigationView.setCheckedItem(R.id.nav_stu_LecSchedule);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new StuLecsFragment()).commit();
                    break;
                case 1:
                    navigationView.setCheckedItem(R.id.nav_inst_Lecs);
                    checkNotif();
                    var = privSingleton.getInstance();
                    backgroundTask = new BackgroundTask(this);
                    backgroundTask.execute("instlec",var.getIDValue()," ");
                    sleep(1200);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new InstLecsFragment()).commit();
                    break;
                default:
                    navigationView.setCheckedItem(R.id.nav_settings);
                    checkNotif();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new SettingsFragment()).commit();
            }

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        checkNotif();

        switch (item.getItemId()){
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                break;
            case R.id.nav_stu_Entrance:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StuEntFragment()).commit();
                break;
            case R.id.nav_stu_LecSchedule:
                privSingleton var = privSingleton.getInstance();
                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute("lecs", var.getIDValue());
                sleep(1500);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StuLecsFragment()).commit();
                break;
            case R.id.nav_notifications:
                privSingleton var2 = privSingleton.getInstance();
                BackgroundTask backgroundTask2 = new BackgroundTask(this);
                backgroundTask2.execute("check", var2.getIDValue(),"1");

               NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);
               Menu menu =navigationView.getMenu();
               MenuItem target = menu.findItem(R.id.nav_notifications);
                target.setIcon(ContextCompat.getDrawable(this,R.drawable.ic_notifications_black_24dp));

                sleep(1500);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NotificationsFragment()).commit();
                break;
            case R.id.nav_notifications_inst:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NotificationsFragment_inst()).commit();
                break;
            case R.id.nav_inst_Lecs:
                var = privSingleton.getInstance();
                backgroundTask = new BackgroundTask(this);
                backgroundTask.execute("instlec",var.getIDValue()," ");
                sleep(600);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InstLecsFragment()).commit();
                break;
            case R.id.nav_inst_Entrance:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InstEntFragment()).commit();
                break;
            case R.id.nav_logout:
                BaseActivity.getInstance ( this ).logout ();
                finish ();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void hideItem(int nav_target)
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu =navigationView.getMenu();
        MenuItem target = menu.findItem(nav_target);
        target.setVisible(false);

    }

    private void checkNotif(){
        privSingleton var = privSingleton.getInstance();
        BackgroundTask backgroundTask2 = new BackgroundTask(this);
        backgroundTask2.execute("check", var.getIDValue(),"0");
        //sleep(300);
      //  String teststring = var2.getResponse(BaseActivity);
       // Log.d("notiftest2",teststring);
        try {
            if (var.getResponse().contains("yes")) {
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);
                Menu menu = navigationView.getMenu();
                MenuItem target = menu.findItem(R.id.nav_notifications);
                target.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_notifications_active_blue_24dp));
            }
        } catch(Exception e){

        }

    }

    @Override
    public void onUserInteraction() {
        (( MyApp ) getApplication ()).onUserInteracted ();
    }
    // On Movement Only Recalculate the session

    @Override
    public void onSessionLogout() {
        finish ();
        Intent i = new Intent ( MainPageActivity.this, MainActivity.class );//BaseActivity.this
        startActivity ( i );// startActivity (new Intent (this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}