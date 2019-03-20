package com.xzb.pluginizationdemo.utils;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: xiangzhenbiao
 * @Date: 2018-10-20 22:39
 * @Description:
 */

public class FileHelpUtil {
    private FileHelpUtil(){

    }

    public static String copyAssetFileToCacheDir(Context context, String fileName){
        File cacheDir = context.getCacheDir();
        if (!cacheDir.exists()){
            cacheDir.mkdirs();
        }
        File outFile = new File(cacheDir, fileName);
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        if(!outFile.exists()){
            try {
                boolean result = outFile.createNewFile();
                if(result){
                    inputStream = context.getAssets().open(fileName);
                    fileOutputStream = new FileOutputStream(outFile);
                    byte[] buffer = new byte[inputStream.available()];
                    int byteCount;
                    while ((byteCount = inputStream.read(buffer)) != -1){
                        fileOutputStream.write(buffer,0, byteCount);
                    }
                    fileOutputStream.flush();
                    Toast.makeText(context,"插件apk下载成功",Toast.LENGTH_SHORT).show();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(inputStream != null){
                        inputStream.close();
                    }
                    if(fileOutputStream != null){
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            Toast.makeText(context,"插件apk已存在",Toast.LENGTH_SHORT).show();
        }
        return outFile.getAbsolutePath();
    }
}
