package org.eddy.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class EnterActivity extends AppCompatActivity {

    private static final Pattern pattern = Pattern.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        findViewById(R.id.ipConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView ipView = findViewById(R.id.ipView);
                String ip = ipView.getText().toString();
                boolean flag = pattern.matcher(ip).matches();
                if (! flag) {
                    Toast.makeText(EnterActivity.this, "IP输入错误，请检查", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(EnterActivity.this, MainActivity.class);
                intent.putExtra("ip", ip);
                startActivity(intent);
            }
        });
    }
}
