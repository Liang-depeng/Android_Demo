package ldp.example.com.android_demo.qqlogin;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import ldp.example.com.android_demo.R;

public class QQLogin_Activity extends AppCompatActivity implements View.OnClickListener {

    @ViewInject(R.id.user_id)
    private EditText et_user_id;
    @ViewInject(R.id.user_mm)
    private EditText et_user_mm;
    @ViewInject(R.id.user_check)
    private CheckBox ck_user_check;
    @ViewInject(R.id.user_login)
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqlogin_);
        x.view().inject(this);

        readFile();
        btn_login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String user_name = et_user_id.getText().toString().trim();
        String user_mm = et_user_mm.getText().toString().trim();

        if (TextUtils.isEmpty(user_name) || TextUtils.isEmpty(user_mm)) {
            Toast.makeText(QQLogin_Activity.this, "用户名或密码不能为空", Toast.LENGTH_LONG).show();
        } else {
            if (ck_user_check.isChecked()) {
                try {
                    /**
                     * 保存数据到文件
                     */
                    File file = new File(this.getFilesDir(), "info.txt");
                    FileOutputStream os = new FileOutputStream(file);
                    String info = user_name + "##" + user_mm;
                    os.write(info.getBytes());
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                /**
                 * 登录时若checkbox没有被选中，则删除保存的文件
                 */
                File file = new File(this.getFilesDir(), "info.txt");
                if (file.exists() && file.length() > 0){
                    file.delete();
                }
            }
            /**
             *模拟本地登陆
             */
            if ("ldp12580".equals(user_name) && "123456".equals(user_mm)) {
                Toast.makeText(QQLogin_Activity.this, "登录成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(QQLogin_Activity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void readFile() {
        File file = new File(this.getFilesDir(), "info.txt");
        if (file.exists() && file.length() > 0) {
            try {
                FileInputStream in = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String info = reader.readLine();//读取一行
                String user_mame2 = info.split("##")[0];//分隔符分割
                String user_mm2 = info.split("##")[1];

                et_user_id.setText(user_mame2);
                et_user_mm.setText(user_mm2);
                ck_user_check.setChecked(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
