package com.example.yuxin.inputstream;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class InputStream extends AppCompatActivity implements View.OnClickListener{

    private EditText txtname;
    private EditText txtcontent;
    private Button bread;
    private Button bwrite;
    private Button bclean;
    private Button bwritesd;
    private Button breadsd;
    private Context mContext;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_stream);
        context = getApplicationContext();
        mContext = getApplicationContext();
        bindViews();
    }
    public void bindViews()
    {
        txtname = (EditText)findViewById(R.id.txtname);
        txtcontent = (EditText)findViewById(R.id.txtcontent);
        bread = (Button) findViewById(R.id.bread);
        bclean = (Button) findViewById(R.id.bclean);
        bwrite = (Button) findViewById(R.id.bwrite);
        bwritesd = (Button) findViewById(R.id.bwritesd);
        breadsd = (Button)findViewById(R.id.breadsd);

        bread.setOnClickListener(this);//监听它自己是否被点击
        bclean.setOnClickListener(this);
        bwrite.setOnClickListener(this);
        breadsd.setOnClickListener(this);
        bwritesd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bclean:
                txtname.setText("");
                txtcontent.setText("");
                break;

            case R.id.bwrite:
            // 辅助类文件 写入内容
                FileHelper fileHelper = new FileHelper(mContext);
                String filename = txtname.getText().toString();
                String filecontext = txtcontent.getText().toString();
                try
                {
                    fileHelper.savefile(filename, filecontext);
                    Toast.makeText(getApplicationContext(), "write file successful", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "write file failed ", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.bread:
                //辅助类 读取内容
                String content = "";
                FileHelper fileHelper1 = new FileHelper(mContext);
                String filename1 = txtname.getText().toString();
                try
                {
                    content = fileHelper1.readfile(filename1);
                } catch (IOException e){
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
                break;

            case  R.id.bwritesd:
                SDcardHelper sdfilehelper = new SDcardHelper();
                String filename2 = txtname.getText().toString();
                String filecontent2 = txtcontent.getText().toString();
                try
                {
                    sdfilehelper.writeSdFile(filename2, filecontent2);
                    Toast.makeText(getApplicationContext(),"write in sd card successful.", Toast.LENGTH_LONG).show();

                } catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "write in sd card failed. ", Toast.LENGTH_LONG).show();
                }

            case R.id.breadsd:
                String contentsd = "";
                SDcardHelper sdfilehelper2 = new SDcardHelper();
                try
                {
                    String filename3 = txtcontent.getText().toString();
                    contentsd = sdfilehelper2.readSdFile(filename3);
                } catch (IOException e){
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), contentsd, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
