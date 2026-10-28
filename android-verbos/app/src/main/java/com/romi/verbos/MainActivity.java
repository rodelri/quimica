package com.romi.verbos;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public final class MainActivity extends Activity {
  private WebView webView;
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    try {
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                           WindowManager.LayoutParams.FLAG_FULLSCREEN);
      webView = new WebView(this);
      webView.setBackgroundColor(Color.rgb(238,243,250));
      WebSettings settings = webView.getSettings();
      settings.setJavaScriptEnabled(true);
      settings.setDomStorageEnabled(true);
      settings.setAllowFileAccess(true);
      settings.setTextZoom(100);
      webView.setWebChromeClient(new WebChromeClient());
      webView.setWebViewClient(new WebViewClient());
      setContentView(webView);
      webView.loadUrl("file:///android_asset/index.html");
    } catch (Throwable error) {
      TextView message = new TextView(this);
      message.setText("No se ha podido abrir Verbos irregulares.\n\n"
          + error.getClass().getSimpleName() + ": " + error.getMessage()
          + "\n\nEnvía una captura de esta pantalla.");
      message.setTextSize(18);
      message.setTextColor(Color.rgb(35,52,80));
      message.setPadding(32,48,32,32);
      setContentView(message,new ViewGroup.LayoutParams(-1,-1));
    }
  }
  @Override protected void onPause(){if(webView!=null)webView.onPause();super.onPause();}
  @Override protected void onResume(){super.onResume();if(webView!=null)webView.onResume();}
  @Override protected void onDestroy(){if(webView!=null){webView.destroy();webView=null;}super.onDestroy();}
}