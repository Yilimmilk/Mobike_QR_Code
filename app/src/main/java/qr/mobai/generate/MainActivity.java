package qr.mobai.generate;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText tv_qrCode_content;//用来生成二维码图片内包含的内容

    private FloatingActionButton tv_click;//按钮

    private ImageView iv_qr_code;//显示二维码的ImageView

    private Button btn;

    String After_code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_qrCode_content= (EditText) findViewById(R.id.tv_qrCode_content);
        btn = (Button) this.findViewById(R.id.button);
        //修改button的名字
        btn.setText("清除");
        //绑定点击事件监听（这里用的是匿名内部类创建监听）
        btn.setOnClickListener(new View.OnClickListener(){
            int i = 0;
            public void onClick(View v) {
                tv_qrCode_content.getText().clear();

            }
        });

        findView();
        inIt();
    }

    private void findView() {
        tv_qrCode_content= (EditText) findViewById(R.id.tv_qrCode_content);
        tv_click= (FloatingActionButton) findViewById(R.id.fab);
        iv_qr_code= (ImageView) findViewById(R.id.iv_qr_code);
        After_code = tv_qrCode_content.getText().toString();
        final TextView Act = (TextView) findViewById(R.id.After_code_tv);
        tv_qrCode_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Act.setText("http://www.mobike.com/download/app.html?b=" + tv_qrCode_content.getText().toString() + "_1");
            }
        });
    }

    private void inIt() {
        tv_click.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final TextView Act = (TextView) findViewById(R.id.After_code_tv);
        switch (view.getId()) {
            case R.id.fab:
                String content = Act.getText().toString().trim();
                String input = tv_qrCode_content.getText().toString().trim();
                if(input.isEmpty()){
                    Snackbar.make(view,"请先输入车号 ",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                Bitmap bitmap = ZXingUtils.createQRImage(content, iv_qr_code.getWidth(), iv_qr_code.getHeight());
                iv_qr_code.setImageBitmap(bitmap);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("关于");
            builder.setMessage("仅供学习\n开发者:酷安@瑞吉尔加德纳\n版本号1.2\n谢谢使用。");
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
