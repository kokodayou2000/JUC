package jiang.demo3;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;

public class Write_File {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\03062\\Desktop\\多线程\\JUC\\src\\main\\java\\jiang\\demo3\\file";
        File file = new File(path);

        String[] list = file.list();

        FileOutputStream writer = null;

        //only need write
        Random r = new Random();

        for (String s : list) {
            writer = new FileOutputStream(new File(file.getPath()+"\\"+s));
            for (int i = 0; i < 20; i++) {
                int i1 = r.nextInt(26);
                writer.write(i1+97);
            }
        }

        assert writer != null;
        writer.close();
    }
}
