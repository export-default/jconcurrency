package cn.mlworks.jconcurrency.counter;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by Ryan on 2016-04-21.
 */
@Threads(16)
@Fork(1)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.Throughput)
public class CounterBenchmark {

    private final static Counter syncCounter = new SyncCounter();
    private final static Counter fairLockCounter = new LockCounter(true);
    private final static Counter nonFairLockCounter = new LockCounter(false);
    private final static Counter casCounter = new CASCounter();
    private final static Counter backOffCounter = new BackOffCASCounter();
    private final static Counter atomicCounter = new AtomicCounter();

    @Benchmark
    public void measureSyncCounter() {
        syncCounter.increment();
    }

    @Benchmark
    public void measureFairLockCounter() {
        fairLockCounter.increment();
    }

    @Benchmark
    public void measureNonFairLockCounter() {
        nonFairLockCounter.increment();
    }

    @Benchmark
    public void measureCASCounter() {
        casCounter.increment();
    }

    @Benchmark
    public void measureAtomicCounter() {
        atomicCounter.increment();
    }

    @Benchmark
    public void measureBackOffCASCounter() {
        backOffCounter.increment();
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(CounterBenchmark.class.getSimpleName())
                .build();
        new Runner(options).run();
    }
}
