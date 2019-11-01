package utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CustomClassLoader extends ClassLoader {

    private static final String DRIVER = "D:\\qrcode\\DemoApplication\\DemoApplication";

    private static final String FILE_TYEP = ".class";

    @Override
    public Class findClass(String name) {
        byte[] data = loadClassData(name);
        return defineClass(name, data, 0, data.length);
    }

    private byte[] loadClassData(String name) {
        FileInputStream fis;
        byte[] data = null;
        try {
            File file = new File(DRIVER, name + FILE_TYEP);
            System.out.println(file.getAbsolutePath());
            fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int ch;
            while ((ch = fis.read()) != -1) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        Class clazz = customClassLoader.findClass("BeforeAdvice");
        System.out.println(clazz);
    }
}
