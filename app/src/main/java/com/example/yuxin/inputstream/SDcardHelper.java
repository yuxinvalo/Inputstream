package com.example.yuxin.inputstream;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Yuxin on 12/10/2015.
 */
public class SDcardHelper {
    private Context context;

    public SDcardHelper() {
    }

    public SDcardHelper(Context context)
    {
        super();
        this.context = context;
    }

    //写入文件
    public void writeSdFile(String filename, String filecontent) throws Exception
    {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            filename = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + filename;
            // 这里不用openfileoutput，因为那是往手机内存中写数据，这里不是
            FileOutputStream output = new FileOutputStream(filename);
            output.write(filecontent.getBytes());
            //将String字符串以字节流的形式写入输出流中
            output.close();
        } else Toast.makeText(context, "write in sd card failed", Toast.LENGTH_LONG).show();
    }

    public String readSdFile(String filename) throws IOException
    {
        StringBuilder sdsbuilder =  new StringBuilder("");
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            FileInputStream input = new FileInputStream(filename);
            byte[] temp = new byte[1024];
            int len = 0;

            while ((len = input.read(temp)) > 0)
            {
                sdsbuilder.append(new String(temp, 0 , len));
            }
            input.close();
        }
        return sdsbuilder.toString();
    }
}
