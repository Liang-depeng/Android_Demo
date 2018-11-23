package ldp.example.com.android_demo.dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import ldp.example.com.android_demo.R;

public class DialogsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn_alertdialog;
    private Button mBtn_progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);

        mBtn_alertdialog = (Button) findViewById(R.id.AlertDialog);
        mBtn_progressdialog = (Button) findViewById(R.id.ProgressDialog);
        mBtn_alertdialog.setOnClickListener(this);
        mBtn_progressdialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.AlertDialog:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("alert_dialog");
                alertDialog.setMessage("hello");
                alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
                break;
            case R.id.ProgressDialog:
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("progress_dialog");
                progressDialog.setMessage("hello");
                progressDialog.show();
                break;
        }
    }
}
