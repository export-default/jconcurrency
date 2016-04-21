package cn.mlworks.jconcurrency;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

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

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(VolatileLoadStoreBenchmark.class.getSimpleName())
            .build();
        new Runner(options).run();
    }

}
