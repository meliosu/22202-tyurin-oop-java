package org.nsu.oop.task4.threadpool;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    private int numWorkers;
    private ArrayList<Thread> workers;
    private LinkedBlockingQueue<Runnable> queue;

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
            worker.interrupt();
        }
    }

    public void resize(int newSize) {
        if (newSize == numWorkers) {
            return;
        } else if (newSize > numWorkers) {
            for (int i = 0; i < newSize - numWorkers; i++) {
                workers.add(new Thread(() -> {
                    while (true) {
                        try {
                            queue.take().run();
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                }));
            }
        } else {
            for (int i = newSize; i < numWorkers; i++) {
                workers.get(i).interrupt();
            }
        }

        numWorkers = newSize;
    }

    public int getSize() {
        return numWorkers;
    }
}
