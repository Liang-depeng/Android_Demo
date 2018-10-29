package ldp.example.com.android_demo.callphone;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import ldp.example.com.android_demo.R;
import ldp.example.com.android_demo.utils.BaseActivity;

public class CallphoneActivity extends BaseActivity {

    @ViewInject(R.id.btn_call_phone)
    private Button call_phone;
    @ViewInject(R.id.phone_number)
    private EditText number;

    private String mPhone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callphone);
        x.view().inject(this);

        call_phone.setOnClickListener(new MycallphoneOnClickListener());
    }


    private class MycallphoneOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            mPhone_number = number.getText().toString().trim();
            if ("".equals(mPhone_number)) {
                Toast.makeText(CallphoneActivity.this, "电话号码不能为空", Toast.LENGTH_LONG).show();
            }else {
                /**
                 * 运行时权限
                 */
                performCodeWithPermission("打电话权限", new PermissionCallback() {
                    @Override
                    public void hasPermission() {
                        call_phone();
                    }
                    @Override
                    public void noPermission() {

                    }
                }, Manifest.permission.CALL_PHONE);
            }

        }
    }

    public void call_phone() {
        //创建一个意图对象
        Intent intent = new Intent();
        //设置动作
        intent.setAction(Intent.ACTION_CALL);
        //指定的动作的数据
        intent.setData(Uri.parse("tel://"+mPhone_number));
        //开启打电话的界面
        startActivity(intent);
    }
}
