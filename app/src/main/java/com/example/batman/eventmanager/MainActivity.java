package com.example.batman.eventmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.login.LoginManager;

import java.util.ArrayList;
import java.util.List;

import dbo.Domain;
import model.ListViewModel;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton fab;
    private Toolbar toolbar;
    private EditText eventName, eventDesc;
    private ListView eventListView;
    private Button eventDate;
    private DatePickerFragment datePickerFragment;
    private Domain domain;
    private List<ListViewModel> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationMenu();

        //Floating Button
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 .setAction("Action", null).show();*/
                setContentView(R.layout.activity_main_create_event);
                navigationMenu();
            }
        });

        //List View in Home Screen
        eventListView = (ListView) findViewById(R.id.homeListView);
        modelList = new ArrayList<ListViewModel>();
        modelList = getEvents(modelList);
        eventListView.setAdapter(new CustomListViewAdaptor(this,modelList));
    }

    private void navigationMenu() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Date Pick Button Action
     *
     * @param view
     */
    public void showDatePickerDialog(View view) {

        datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "date_pick");
    }

    /**
     * Save Button Action - Save Events to Db
     *
     * @param view
     */
    public void saveEvent(View view) {
        Toast.makeText(this, "Saving...", Toast.LENGTH_SHORT).show();
        eventName = (EditText) findViewById(R.id.eventName);
        eventDesc = (EditText) findViewById(R.id.eventDescription);
        //eventDate = (Button) findViewById(R.id.eventDate);
        String selectedDate = datePickerFragment.getSelectedDate();
        Log.d("SelectedDate : ", selectedDate);
        domain = new Domain(this);
        domain.getWritableDatabase();
        domain.saveEvent(domain, eventName.getText().toString(), selectedDate.toString());
        Toast.makeText(this, "Event Created!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Get all Events from Database
     * @param modelList
     * @return
     */
    public List<ListViewModel> getEvents(List<ListViewModel> modelList) {
        domain = new Domain(this);
        Cursor cursor = domain.getEvents(domain);
        ListViewModel listViewModel;
        if (cursor.moveToFirst()) {
            do {
                listViewModel = new ListViewModel();
                listViewModel.setEventName(cursor.getString(cursor.getColumnIndex("event_name")));
                listViewModel.setEventDate(cursor.getString(cursor.getColumnIndex("event_date")));
                modelList.add(listViewModel);
            } while (cursor.moveToNext());
        }
        return modelList;
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {
            PrefUtils.clearCurrentUser(MainActivity.this);
            // We can logout from facebook by calling following method
            LoginManager.getInstance().logOut();

            Intent i= new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
