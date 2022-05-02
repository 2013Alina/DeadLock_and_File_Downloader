package org.example;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Semaphore;

public class Downloader extends Thread {
    Semaphore sem;
    String url;

    public Downloader(Semaphore semaphore, String url) {
        this.sem = semaphore;
        this.url = url;
    }

    @Override
    public void run() {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        downloadFile(url);
        sem.release();
    }

    private void downloadFile(final String url) {
        try {
            File dir = new File("downloads");
            if (!dir.exists()) {
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

    @NotNull
    private String getDownloadedFileName(String url) {
        return url.substring(url.lastIndexOf("/"));
    }

//    https://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4
}
