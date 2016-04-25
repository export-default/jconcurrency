package cn.mlworks.jconcurrency;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by Ryan on 2016-04-21.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@Fork(1)
public class VolatileLoadStoreBenchmark {

    private static volatile int x = 0;
    private static int y = 0;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(VolatileLoadStoreBenchmark.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public int measureNormalLoad() {
        return y;
    }

    @Benchmark
    public int measureVolatileLoad() {
        return x;
    }

    @Benchmark
    public void measureNormalStore() {
        y = 3;
    }

    @Benchmark
    public void measureVolatileStore() {
        x = 3;
    }

}
