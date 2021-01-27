package com.diomun.learn.javademo.util;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author DIOMUN dmq1212@qq.com
 * @date created on 2021/1/27
 * @desc
 */
public class fileUtil {
    /**
     * 拷贝文件
     *@param srcPath 绝对路径
     * @param destDir 目标文件所在目录
     * @return boolean true拷贝成功
     */
    public static boolean copyFile(String srcPath, String destDir){
        boolean flag = false;
        File srcFile = new File(srcPath); // 源文件
        // TODO:文件存在的判断
        // if (!srcFile.exists()){
        //     Log.i("FileUtils is copyFile ","源文件不存在");
        //     return false;
        // }
        // 获取待复制文件的文件名
        String fileName = srcPath.substring(srcPath.lastIndexOf(File.separator));
        String destPath = destDir + fileName;
        if (destPath.equals(srcPath)){
            Log.i("FileUtils is copyFile ","源文件路径和目标文件路径重复");
            return false;
        }
        File destFile = new File(destPath); // 目标文件
        if (destFile.exists() && destFile.isFile()){
            Log.i("FileUtils is copyFile ","该路径下已经有一个同名文件");
            return false;
        }
        File destFileDir = new File(destDir);
        destFileDir.mkdirs();
        try{
            FileInputStream fis = new FileInputStream(srcPath);
            FileOutputStream fos = new FileOutputStream(destFile);
            byte[] buf = new byte[1024];
            int c;
            while ((c = fis.read(buf)) != -1) {
                fos.write(buf, 0, c);
            }
            fis.close();
            fos.close();
            flag = true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return flag;
    }
}
