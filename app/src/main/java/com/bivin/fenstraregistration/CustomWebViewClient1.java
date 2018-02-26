package com.bivin.fenstraregistration;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by bivin on 2/22/2018.
 */

public class CustomWebViewClient1 extends WebViewClient {
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        view.loadData("<html>OMG! SOMETHING WENT WRONG! <br> Please " +
                "contact FENSTRA TEAM </html>", "", "");

    }

}
