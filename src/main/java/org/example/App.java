package org.example;

import org.apache.commons.validator.routines.UrlValidator;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

      /*  Задание 2 (Дополнительное)

        Создать программу для загрузки файлов из сети интернет.
        Реализовать работу в многопоточном режиме (одновременная загрузка до 5 файлов)
        По окончании загрузки вывести информацию о загрузке (где был сохранен файл, его имя, размер файла)
        Для загрузки файлов использовать класс URL или HTTPClient (начиная с Java 9).
        Запускать потоки через Thread */

//        Scanner scanner = new Scanner(System.in);
//        FileDownloader[] fileDownloader = new FileDownloader[5];
//        String input;
//
//        for (int i = 0; i < fileDownloader.length; i++) {
//            System.out.println("Enter name of web site or copy the connection " + (i + 1) + ":");
//            input = scanner.next();
//
//            if (!checkURL(input)) {
//                System.out.println("Invalid input!");
//            } else {
//                fileDownloader[i] = new FileDownloader(input);
//            }
//        }
//        for (FileDownloader thread : fileDownloader) {
//            thread.start();
//        }
        Downloader downloader = new Downloader();
        while (true) {
            System.out.println("Enter your URL or q to exit");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            if ("q".equalsIgnoreCase(input)) {
                break;
            } else {
                if (!checkURL(input)) {
                    System.out.println("Incorrect URL, please try again.");
                    continue;
                }
                System.out.println("downloading has started");
                downloader.downloadFile(input);
            }
        }
    }

    private static boolean checkURL(String url) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }
}