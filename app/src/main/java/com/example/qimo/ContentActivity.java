package com.example.qimo;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ContentActivity extends AppCompatActivity {
    WebView wv_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        wv_content=findViewById(R.id.wv_content);
        wv_content.getSettings().setJavaScriptEnabled(true);
        wv_content.setWebViewClient(new WebViewClient());//对于新的超链接在原窗口里面显示
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        wv_content.loadUrl(url);
    }
}
