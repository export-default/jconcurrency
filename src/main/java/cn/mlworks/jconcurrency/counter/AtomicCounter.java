package cn.mlworks.jconcurrency.counter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Ryan on 2016-04-21.
 */
public class AtomicCounter implements Counter {
    private AtomicLong value = new AtomicLong();

    @Override
    public void increment() {
        value.incrementAndGet();
    }

    @Override
    public long get() {
        return value.get();
    }
}
