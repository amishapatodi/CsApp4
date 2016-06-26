package com.csapp.csapp.student;

/**
 * Created by Shubhi on 5/11/2016.
 */
import java.util.HashMap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.widget.Toolbar;

import com.csapp.csapp.MainActivity;
import com.csapp.csapp.R;
import com.csapp.csapp.app.AppConfig;
import com.csapp.csapp.helper.SQLiteHandler;
import com.csapp.csapp.helper.SessionManager;

public class StudentMain extends AppCompatActivity implements com.csapp.csapp.student.FragmentDrawer.FragmentDrawerListener {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private TextView txtName;
    private TextView txtEnroll;
    private Button btnLogout;
    private String enroll;
    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentmain);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (com.csapp.csapp.student.FragmentDrawer)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        onNavigationDrawerItemSelected(0);

        txtName = (TextView) findViewById(R.id.name);
        txtEnroll = (TextView) findViewById(R.id.enroll);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getStudentDetails();

        String name = user.get("name");
        enroll = user.get("enroll");
        //Toast.makeText(getApplicationContext(),name+" "+enroll,Toast.LENGTH_LONG).show();
        // Displaying the user details on the screen
        txtName.setText(name);
        txtEnroll.setText(enroll);

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

        db.deleteStudents();

        // Launching the login activity
        Intent intent = new Intent(StudentMain.this, MainActivity.class);
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
                Intent i=new Intent(this,studentAssignment.studentattendance.class);
                i.putExtra("rollno",enroll);
                startActivity(i);
                break;
            case 2:
                Intent in=new Intent(this,studentAssignment.class);
                in.putExtra("rollno",enroll);
                startActivity(in);
                break;
            case 3:
                startActivity(new Intent(this,News.class));
                break;
            case 4:
                Intent ii=new Intent(this,studentAssignment.studentMarks.class);
                ii.putExtra("rollno",enroll);
                startActivity(ii);
                break;
            case 5:
                Intent iii=new Intent(this,studentAssignment.studentTimetable.class);
                iii.putExtra("rollno",enroll);
                startActivity(iii);
                break;
            case 6:
                Intent iiii = new Intent(Intent.ACTION_VIEW, Uri.parse(AppConfig.URL_START+"submitassignment.php?rollno="+enroll));
                startActivity(iiii);
                break;
             case 7:
                Intent message = getPackageManager().getLaunchIntentForPackage("info.androidhive.gcm");
                startActivity(message);
                break;
            case 8:
                Intent chat = getPackageManager().getLaunchIntentForPackage("com.quickblox.sample.groupchatwebrtc");
                startActivity(chat);
                break;
            default:
                break;
        }
    }
}
