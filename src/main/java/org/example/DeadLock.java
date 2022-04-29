package org.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
//    Перше завдання.
//    static Object Lock1 = new Object();
//    static Object Lock2 = new Object();

    static final Lock lock1 = new ReentrantLock();
    static final Lock lock2 = new ReentrantLock();

    private static class Thread1 extends Thread {
        public void run() {
            try {
                if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
                    System.out.println("Thread 1: Has Lock1");
                    System.out.println("Thread 1: Waiting for Lock 2");
                }
                if (!lock2.tryLock()) {
                    System.out.println("Thread 1: No DeadLock");
                } else {
                    lock1.unlock();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static class Thread2 extends Thread {
        public void run() {

            try {
                if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
                    System.out.println("Thread 2: Has Lock2");
                    System.out.println("Thread 2: Waiting for Lock 1");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!lock1.tryLock()) {
                System.out.println("Thread 2: No DeadLock");
            } else {
                lock2.unlock();
            }
        }
    }


    public static void main(String args[]) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }
}
