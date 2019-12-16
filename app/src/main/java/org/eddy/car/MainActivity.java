package org.eddy.car;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String content = "<html>\n" +
            "<title></title>\n" +
            "\n" +
            "<body>\n" +
            "    hello webview\n" +
            "</body>\n" +
            "\n" +
            "</html>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        WebView webView = findViewById(R.id.webvies);
        webView.loadUrl("https://justice-love.com/");
        webView.loadData(content, "text/html", "UTF-8");
        Button front = findViewById(R.id.front);
        front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "向前", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "向后", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "向右", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "向左", Toast.LENGTH_LONG).show();
            }
        });
    }
}
