package org.nsu.oop.task4.threadpool;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    private final int numWorkers;
    private final ArrayList<Thread> workers;
    private final LinkedBlockingQueue<Runnable> queue;

    public ThreadPool(int size) {
        numWorkers = size;
        queue = new LinkedBlockingQueue<>();
        workers = new ArrayList<>();

        for (int i = 0; i < numWorkers; i++) {
            workers.add(new Thread(() -> {
                while (true) {
                    try {
                        queue.take().run();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }));

            workers.get(i).start();
        }
    }

    public ThreadPool() {
        this(Runtime.getRuntime().availableProcessors());
    }

    public void enqueue(Runnable job) {
        try {
            queue.put(job);
        } catch (InterruptedException ignored) {}
    }

    public void stop() {
        for (Thread worker : workers) {
            try {
                worker.interrupt();
                worker.join();
            } catch (InterruptedException ignored) {}
        }
    }

    public int getSize() {
        return numWorkers;
    }
}
