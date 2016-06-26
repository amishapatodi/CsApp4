package com.csapp.csapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.csapp.csapp.app.AppConfig;

/**
 * Created by Shubhi on 5/11/2016.
 */
public class News extends AppCompatActivity{
    private WebView webView;
    private static String url= AppConfig.URL_START;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_addstudent);
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url+"news.php");
    }

}
