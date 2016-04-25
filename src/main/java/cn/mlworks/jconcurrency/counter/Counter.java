package cn.mlworks.jconcurrency.counter;

/**
 * Created by Ryan on 2016-04-21.
 */
public interface Counter {
    void increment();

    long get();
}
