package com.example.yuxin.inputstream;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Yuxin on 11/10/2015.
 */
public class FileHelper {
    private Context mContext;
    public FileHelper(){
    }

    public FileHelper(Context mContext)
    {
        super();
        this.mContext = mContext;
    }

    public void savefile(String filename, String filecontent) throws Exception {
        FileOutputStream output = mContext.openFileOutput(filename, Context.MODE_PRIVATE);
        //文件读取为私有方式，意思是被创造出来的文件只能被本应用访问
        output.write(filecontent.getBytes());//.write(int onebytes), filecontent 是string类型，继承
        //getbytes的方法， 通过output fileoutputstream来一个byte一个bytes地写入
        output.close();
    }

    public String readfile(String filename) throws IOException
    {
        //打开文件输出流
        FileInputStream input = mContext.openFileInput(filename);
        byte[] temp = new byte[1026];
        StringBuilder sbuilder = new StringBuilder("");
        //创建长度可变换的字符串
        int len = 0;

        //读取文件内容
        while ((len = input.read(temp)) > 0)
        {
            sbuilder.append(new String(temp, 0 , len));
            //使用追加模式将 temp的内容从第0个字节读到Len个字节。
        }
        input.close();
        return sbuilder.toString();
    }

}