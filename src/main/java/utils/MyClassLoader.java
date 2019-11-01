package utils;

import org.junit.Test;

import java.io.*;

/**
 * @Auther: Administrator
 * @Date: 2018-12-07 09:27
 * @Description:
 */
public class MyClassLoader extends ClassLoader {

  /*  @Test
    public void test() throws Exception {
        *//*File file = new File(MyClassLoader.class.getResource("/").getPath());
        findFiles(file);*//*
    }*/

    public void loadClass() throws Exception {
        File file = new File(MyClassLoader.class.getResource("/").getPath());
        findFiles(file);
    }


    public void findFiles(File file) throws Exception {
        if(file == null){
            return;
        }
        File[] files = file.listFiles();
        for(File fi:files){
            if(fi.isDirectory()){
                //是目录就递归调用
                findFiles(fi);
            }else {
                //加载字节码
                Class clazz = loadClass(fi);
            }
        }
    }

    //将class文件加载为class对象
    private Class loadClass(File file) throws Exception {
        //判断文件是否合法
        boolean isClass = isClassFile(file);
        if(!isClass){
            return null;
        }
        FileInputStream fis;
        byte[] bytes = null;
        try {
            //File file = new File(DRIVER, name + FILE_TYEP);
            System.out.println(file.getAbsolutePath());
            fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int ch;
            while ((ch = fis.read()) != -1) {
                baos.write(ch);
            }
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] split = file.getName().split("\\.");
        Class<?> aClass = this.defineClass(split[0], bytes, 0, bytes.length);
        return aClass;
    }

    private boolean isClassFile(File file) {
        if(file == null){
            return false;
        }
        String name = file.getName();
        String[] split = name.split("\\.");
        if(split.length == 0){
            return false;
        }
        if(split[split.length-1].equals("class")){
            return true;
        }
        return true;
    }

    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader();
        try {
            myClassLoader.loadClass();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
