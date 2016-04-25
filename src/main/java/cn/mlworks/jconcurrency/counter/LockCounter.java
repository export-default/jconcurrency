package cn.mlworks.jconcurrency.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Ryan on 2016-04-21.
 */
public class LockCounter implements Counter {
    private final Lock lock;
    private long value;

    public LockCounter(boolean fair) {
        lock = new ReentrantLock(fair);
    }

    @Override
    public void increment() {
        lock.lock();
        value++;
        lock.unlock();
    }

    @Override
    public long get() {
        return value;
    }
}
