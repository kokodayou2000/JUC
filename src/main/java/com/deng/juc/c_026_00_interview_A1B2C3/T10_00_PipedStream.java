package com.deng.juc.c_026_00_interview_A1B2C3;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;

public class T10_00_PipedStream {
    public static void main(String[] args) throws IOException {
        char[] aC = "ABCDE".toCharArray();
        char[] aI = "12345".toCharArray();
        PipedInputStream inputStream1 = new PipedInputStream();
        PipedInputStream inputStream2 = new PipedInputStream();
        PipedOutputStream outputStream1 = new PipedOutputStream();
        PipedOutputStream outputStream2 = new PipedOutputStream();
        inputStream1.connect(outputStream2);
        inputStream2.connect(outputStream1);

        String msg = "Your Turn";
        new Thread(()->{
            byte[] buffer = new byte[9];
            try{
                for (char c : aC) {
                    inputStream1.read(buffer);
                    if (new String(buffer).equals(msg)){
                        System.out.print(c);
                    }
                    outputStream1.write(msg.getBytes(StandardCharsets.UTF_8));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        },"t1").start();
        new Thread(()->{
            byte[] buffer = new byte[9];
            try{
                for (char c : aI) {
                    System.out.print(c);
                    outputStream2.write(msg.getBytes(StandardCharsets.UTF_8));
                    inputStream2.read(buffer);
                    if (new String(buffer).equals(msg)){
                        continue;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        },"t2").start();




    }
}
