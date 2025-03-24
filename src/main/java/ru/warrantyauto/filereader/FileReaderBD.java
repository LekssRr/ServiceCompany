package ru.warrantyauto.filereader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReaderBD {

    public String getStringInFileToIndex(int Index) {
        File bd = new File("/Users/aleksandrknazev/Documents/BD.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(bd);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String sc = null;
        for (int i = 0; i <= Index; i++) {
            sc = scanner.nextLine().toString();
        }
        return sc;
    }
}
