package ldp.example.com.android_demo.http;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import ldp.example.com.android_demo.R;

public class HttpActivity extends AppCompatActivity {

    private Button mBtn_http;
    private TextView mTxt_http;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        mBtn_http = (Button) findViewById(R.id.btn_http_btn);
        mTxt_http = (TextView) findViewById(R.id.txt_http);
        http_test();

    }

    private void http_test() {
        new Thread(new Runnable() {
            HttpURLConnection connection = null;
            @Override
            public void run() {
                try {
                    URL url = new URL("https://free-api.heweather.com/s6/weather?location=北京&key=e213b21d1a25491ba625ce3d3d5dfc2d ");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null){
                        result.append(line);
                    }
                    showresult(result.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    connection.disconnect();
                }
            }
        }).start();
    }

    private void showresult(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTxt_http.setText(s + "1111");
                Log.d("x",s);
            }
        });
    }
}
