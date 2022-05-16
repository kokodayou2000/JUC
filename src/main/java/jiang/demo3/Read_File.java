package jiang.demo3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class Read_File {

    static ConcurrentHashMap<Character, Integer> map = new ConcurrentHashMap<>();

    //创建10个线程读取10个文件的字符串，并存放到HashMap中

    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    new Read_File().read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(1);

        ConcurrentHashMap.KeySetView<Character, Integer> keySet = map.keySet();
        Integer sum = 0;

        for (Character character : keySet) {
            System.out.println(character+" - "+map.get(character));
            sum += map.get(character);
        }


        System.out.println(sum);
    }

    public synchronized void read() throws IOException {
        String path = "C:\\Users\\03062\\Desktop\\多线程\\JUC\\src\\main\\java\\jiang\\demo3\\file";
        File file = new File(path);
        String[] list = file.list();

        //读取文件流
        FileReader reader = null;
        byte[] bytes = new byte[1024];
        int l;
        for (String s : list) {
            reader = new FileReader(new File(path+"//"+s));
            while (( l= reader.read()) != -1){
                char ch = (char)l;
                synchronized (this){
                    map.put(ch,map.getOrDefault(ch,0)+1);
                }
            }
        }
        reader.close();


    }
}
