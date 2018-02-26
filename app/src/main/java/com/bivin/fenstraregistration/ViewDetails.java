package com.bivin.fenstraregistration;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

public class ViewDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        final WebView webView;
        webView=findViewById(R.id.webview1);
        final String deviceId = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        final String url="http://172.16.80.10/fenreg/fenregM/index.php?d=3&mac="+deviceId;
        Toast.makeText(getApplicationContext(),"View Details",Toast.LENGTH_LONG).show();








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












        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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




        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////



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
        */

        SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipeToRefresh1);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Insert your code here
                webView.loadUrl(url); // refreshes the WebView
            }
        });


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



    @Override
    public void onBackPressed() {
        // your code.
        finish();
    }

}
