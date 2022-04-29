package org.example;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Downloader {
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    public void downloadFile(final String url) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    InputStream inputStream = new URL(url).openStream();
                    String fileName = "downloads" + File.separator + getFileName(url);
                    File file = new File(fileName);
                    if (file.exists()) {
                        System.out.println("File " + fileName + " already exists!");
                    } else {
                        Files.copy(inputStream, Paths.get(fileName));
                        file = new File(fileName);
                        if (file.exists()) {
                            System.out.println("The generated file is called: " + file.getName());
                            System.out.println("File:" + file.getName() + "Absolute file path: "
                                    + file.getAbsolutePath() + "File size: " + file.length());
                        } else {
                            System.out.println("File " + fileName + " not found!!!!!");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

    private String getFileName(String url) {
        return url.substring(url.lastIndexOf("/", url.length()));
    }

//    https://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4
}
