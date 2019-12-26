package org.eddy.car;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.eddy.car.proto.CarControlRequest;
import org.eddy.car.proto.CarRpcGrpc;
import org.eddy.car.proto.Direction;

public class MainActivity extends AppCompatActivity {

    private static final String content = "<html>\n" +
        "<title></title>\n" +
        "\n" +
        "<body>\n" +
        "    <img style=\"width: 380px;\" src=\"http://ip:8080/?action=stream\" />\n" +
        "</body>\n" +
        "\n" +
        "</html>";

    private static CarRpcGrpc.CarRpcBlockingStub stub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ManagedChannel managedChannel = (ManagedChannel) stub.getChannel();
        managedChannel.shutdown();
    }

    @Override
    protected void onStart() {
        super.onStart();

        String ip = getIntent().getStringExtra("ip");
        ManagedChannel channel = ManagedChannelBuilder.forAddress(ip, 18901).usePlaintext().build();
        stub = CarRpcGrpc.newBlockingStub(channel);

        WebView webView = findViewById(R.id.webvies);
        webView.loadData(content.replace("ip", ip), "text/html", "UTF-8");

        findViewById(R.id.front).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "向前", Toast.LENGTH_LONG).show();
                stub.command(CarControlRequest.newBuilder().setDirection(Direction.FRONT).build());
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "向后", Toast.LENGTH_LONG).show();
                stub.command(CarControlRequest.newBuilder().setDirection(Direction.BACK).build());
            }
        });

        findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "向右", Toast.LENGTH_LONG).show();
                stub.command(CarControlRequest.newBuilder().setDirection(Direction.RIGHT).build());
            }
        });

        findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "向左", Toast.LENGTH_LONG).show();
                stub.command(CarControlRequest.newBuilder().setDirection(Direction.LEFT).build());
            }
        });
    }
}
