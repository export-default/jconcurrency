package cn.mlworks.jconcurrency.counter;

/**
 * Created by Ryan on 2016-04-21.
 */
public class SyncCounter implements Counter {
    private long value;

    @Override
    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    @Override
    public long get() {
        return value;
    }
}
