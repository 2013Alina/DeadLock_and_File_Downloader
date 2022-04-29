package org.example;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Downloader {

    public void downloadFile(final String url) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    File dir = new File("downloads");
                    if(!dir.exists()){
                        dir.mkdir();
                    }
                    InputStream inputStream = new URL(url).openStream();
                    String newFileName = dir.getName() + File.separator + getDownloadedFileName(url);
                    File file = new File(newFileName);
                    if (file.exists()) {
                        System.out.println("File " + newFileName + " already exists!");
                    } else {
                        Files.copy(inputStream, Paths.get(newFileName));
                        file = new File(newFileName);
                        if (file.exists()) {
                            System.out.println("The file has been saved here: ");
                            System.out.println(file.getParentFile().getAbsolutePath());
                            System.out.println("Saved file name: ");
                            System.out.println(file.getName());
                            System.out.println("File size: " + Files.size(file.toPath()) + " bytes");
                        } else {
                            System.out.println("File " + newFileName + " not found!!!!!");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }

    @NotNull
    private String getDownloadedFileName(String url) {
        return url.substring(url.lastIndexOf("/"));
    }

//    https://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4
}
