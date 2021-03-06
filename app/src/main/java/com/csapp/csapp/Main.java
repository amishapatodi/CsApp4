package com.csapp.csapp;

/**
 * Created by Shubhi on 4/12/2016.
 */

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.widget.Toolbar;
import com.csapp.csapp.R;
import com.csapp.csapp.app.AppConfig;
import com.csapp.csapp.app.AppController;
import com.csapp.csapp.helper.SQLiteHandler;
import com.csapp.csapp.helper.SessionManager;

public class Main extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        onNavigationDrawerItemSelected(0);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getTeacherDetails();

        String name = user.get("name");
        String email = user.get("email");
        //Toast.makeText(getApplicationContext(),name+" "+email,Toast.LENGTH_LONG).show();
        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteTeachers();

        // Launching the login activity
        Intent intent = new Intent(Main.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        onNavigationDrawerItemSelected(position);
    }


    public void onNavigationDrawerItemSelected(int position) {
        switch(position) {
            case 0:
                break;
            case 1:
                Intent addclass = new Intent(Intent.ACTION_VIEW, Uri.parse(AppConfig.URL_START+"uploadclass.php"));
                startActivity(addclass);
                break;
            case 2:
                startActivity(new Intent(this,Teacher_Attendance.class));
                break;
            case 3:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(AppConfig.URL_START+"uploadassignment.php"));
                startActivity(browserIntent);
                break;
            case 4:
                startActivity(new Intent(this,News.class));
                break;
            case 5:
                startActivity(new Intent(this,ViewAttendance.class));
                break;
            case 6:
                startActivity(new Intent(this,ViewAssignment.class));
                break;
            case 7:
                Intent ii = new Intent(Intent.ACTION_VIEW, Uri.parse(AppConfig.URL_START+"uploadmarks.php"));
                startActivity(ii);
                break;
            case 8:
                Intent iii = new Intent(Intent.ACTION_VIEW, Uri.parse(AppConfig.URL_START+"uploadtimetable.php"));
                startActivity(iii);
                break;
            case 9:
                startActivity(new Intent(this,ViewAssignment.ViewStudentAssignment.class));
                break;

            case 10:
                Intent message = getPackageManager().getLaunchIntentForPackage("info.androidhive.gcm");
                startActivity(message);
                break;
            case 11:
                Intent chat = getPackageManager().getLaunchIntentForPackage("com.quickblox.sample.groupchatwebrtc");
                startActivity(chat);
                break;
              default:
                  break;
        }
    }
}
