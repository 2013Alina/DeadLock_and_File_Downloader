package org.example;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDownloader extends Thread {
    private final String url;

    public FileDownloader(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = new URL(url).openStream();
            String loadName = Thread.currentThread().getName() + "copy file" + ".mp4";
            File currentFile = new File(loadName);
            if (currentFile.exists()) {
                System.out.println("Such file already exists!");
            } else {
                Files.copy(inputStream, Paths.get(loadName));
                currentFile = new File(loadName);
                if(currentFile.exists()){
                    System.out.println("The generated file is called: " + currentFile.getName());
                    System.out.println("File:" + currentFile.getName() + "Absolute file path: "
                            + currentFile.getAbsolutePath() + "File size: " + currentFile.length());
                }else {
                    System.out.println("File " + loadName + " not found!!!!!");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

