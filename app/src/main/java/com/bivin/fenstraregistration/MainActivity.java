package com.bivin.fenstraregistration;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WebView webView;
        webView = findViewById(R.id.webview);

        final String deviceId = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        final String url = "http://172.16.80.10/fenreg/fenregM/index.php?mac=" + deviceId;
        Toast.makeText(getApplicationContext(), "Please Register", Toast.LENGTH_LONG).show();



        if (!DetectConnection.checkInternetConnection(this)) {
            Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
        } else {

            CustomWebViewClient c = new CustomWebViewClient();
            webView.setWebViewClient(c);
            webView.clearCache(true);
            webView.clearHistory();
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            //webView.getSettings().setBuiltInZoomControls(true);
            webView.loadUrl(url);
        }


        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                webView.loadUrl("file:///android_asset/errorhtml.html");

            }
        });
















        ///////////////////////////////////////////////////////////////////////////////////////////////////////
       /* ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (mWifi.isConnected()) {

            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.setWebViewClient(new CustomWebViewClient());
            webView.loadUrl(url);
        }
        else {
           Toast.makeText(getApplicationContext(),"No Internet ! please contact FENSTRA TEAM",Toast.LENGTH_LONG).show();
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        class CustomWebViewClient extends WebViewClient {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!DetectConnection.checkInternetConnection(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
                } else {
                    view.loadUrl(url);
                }
                return true;
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                view.loadData("<html>OMG! SOMETHING WENT WRONG! <br> Please " +
                        "contact FENSTRA TEAM </html>", "", "");

            }
        }
        if (!DetectConnection.checkInternetConnection(this)) {
            Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
        } else {

            CustomWebViewClient c = new CustomWebViewClient();
            webView.setWebViewClient(c);
            webView.clearCache(true);
            webView.clearHistory();
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.loadUrl(url);
        }*/

















        SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipeToRefresh);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Insert your code here
                webView.loadUrl(url); // refreshes the WebView
            }
        });

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewDetails.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        // your code.
        AlertDialog.Builder adb = new AlertDialog.Builder(this);


        //adb.setView(alertDialogView);


        adb.setTitle("Fenstra 2k18");
        adb.setMessage("Are You sure to Exit?");


        //adb.setIcon(android.R.drawable.ic_dialog_alert);


        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                android.os.Process.killProcess(android.os.Process.myPid());
            } });

        adb.show();
    }

    // Function to load all URLs in same webview
    class CustomWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!DetectConnection.checkInternetConnection(getApplicationContext())) {
                Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
                view.loadData("<html>OMG! SOMETHING WENT WRONG! <br> Please " +
                        "contact FENSTRA TEAM </html>", "", "");
            } else {
                view.loadUrl(url);
            }
            return true;
        }
    }



}