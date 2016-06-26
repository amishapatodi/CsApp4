package com.csapp.csapp.student;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Toast;

import com.csapp.csapp.R;
import com.csapp.csapp.app.AppConfig;

/**
 * Created by Shubhi on 5/12/2016.
 */
public class studentAssignment extends AppCompatActivity {
    public static final String TAG = studentAssignment.class.getSimpleName();
    private WebView webView;
    private static String url = AppConfig.URL_START;
    private static String rollno;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_addstudent);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rollno = extras.getString("rollno");
        }
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        Toast.makeText(studentAssignment.this, rollno, Toast.LENGTH_SHORT).show();
        webView.loadUrl(url + "fetchstudentassignment.php?rollno=" + rollno);
    }

    /**
     * Created by Shubhi on 5/12/2016.
     */
    public static class studentattendance extends AppCompatActivity {
        //public static final String TAG=studentattendance.class.getSimpleName();
        private WebView webView;
        private static String url = AppConfig.URL_START;
        private static String rollno = "a";

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.teacher_addstudent);

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                rollno = extras.getString("rollno");
            }
            webView = (WebView) findViewById(R.id.webView1);
            webView.getSettings().setJavaScriptEnabled(true);
            Toast.makeText(studentattendance.this, rollno, Toast.LENGTH_SHORT).show();
            webView.loadUrl(url + "fetchstudentattendance.php?rollno=" + rollno);
        }

    }

    public static class studentMarks extends AppCompatActivity {
        // public static final String TAG=studentMarks.class.getSimpleName();
        private WebView webView;
        private static String url = AppConfig.URL_START;
        private static String rollno = "a";

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.teacher_addstudent);

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                rollno = extras.getString("rollno");
            }
            webView = (WebView) findViewById(R.id.webView1);
            webView.getSettings().setJavaScriptEnabled(true);
            //Toast.makeText(studentattendance.this,rollno,Toast.LENGTH_SHORT).show();
            webView.loadUrl(url + "viewmarks.php?rollno=" + rollno);
        }

    }

 public static class studentTimetable extends AppCompatActivity {
        // public static final String TAG=studentMarks.class.getSimpleName();
        private WebView webView;
        private static String url = AppConfig.URL_START;
        private static String rollno = "a";

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.teacher_addstudent);

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                rollno = extras.getString("rollno");
            }
            webView = (WebView) findViewById(R.id.webView1);
            webView.getSettings().setJavaScriptEnabled(true);
            //Toast.makeText(studentattendance.this,rollno,Toast.LENGTH_SHORT).show();
            webView.loadUrl(url + "timetable.php?rollno=" + rollno);
        }

    }
}