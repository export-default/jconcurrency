package cn.mlworks.jconcurrency.counter;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by Ryan on 2016-04-21.
 */
public class BackOffCASCounter implements Counter {
    private AtomicLong value = new AtomicLong();

    @Override
    public void increment() {
        for (; ; ) {
            long current = value.get();
            long next = current + 1;
            if (value.compareAndSet(current, next)) {
                return;
            } else {
                LockSupport.parkNanos(1);
            }
        }
    }

    @Override
    public long get() {
        return value.get();
    }
}
