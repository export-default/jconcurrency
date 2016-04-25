package cn.mlworks.jconcurrency.counter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Ryan on 2016-04-21.
 */
public class CASCounter implements Counter {
    private AtomicLong value = new AtomicLong();

    @Override
    public void increment() {
        for (; ; ) {
            long current = value.get();
            long next = current + 1;
            if (value.compareAndSet(current, next))
                return;
        }
    }

    @Override
    public long get() {
        return value.get();
    }
}
