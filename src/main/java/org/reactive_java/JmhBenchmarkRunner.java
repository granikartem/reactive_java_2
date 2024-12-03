package org.reactive_java;


import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.reactive_java.benchmark.LabBenchmark;

public final class JmhBenchmarkRunner {

    private JmhBenchmarkRunner() {}

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(LabBenchmark.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .result("benchmark-result4.csv")
                .forks(1)
                .build();
        new Runner(options).run();
    }
}
