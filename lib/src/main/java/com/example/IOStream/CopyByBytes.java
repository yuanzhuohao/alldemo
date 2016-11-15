package com.example.IOStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Jess Yuan on 04/11/2016.
 */

public class CopyByBytes {

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = null;
        File file = new File(".");
        System.out.println(file.getAbsolutePath());
        try {
            inputStream = new FileInputStream(".");

            int c;
//            while ((c = inputStream.read()) != -1) {
//                System.out.println(c);
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
    }

}
