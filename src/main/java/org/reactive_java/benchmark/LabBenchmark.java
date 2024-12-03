package org.reactive_java.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.annotations.Mode;
import org.reactive_java.benchmark.stats_collector.*;
import org.reactive_java.generator.TaskGenerator;
import org.reactive_java.model.Task;

import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class LabBenchmark {
    @Param({"false", "true"})
    private boolean useDelay;
    @Param({"10", "100", "1000", "10000", "25000"})
//    @Param({"10", "100", "1000", "10000", "25000", "50000"})
//    @Param({"10", "100", "1000"})
    private int tasksAmount;

    private List<Task> tasks;

    @Setup(Level.Invocation)
    public void setUp() {
        tasks = TaskGenerator.generateTasks(tasksAmount);
    }

    @Benchmark
    public void iteratorStatsBenchmark() throws InterruptedException {
        IteratorStatsCollector.collectStats(tasks, useDelay);
    }

    @Benchmark
    public void streamStatsBenchmark() {
        StreamStatsCollector.collectStats(tasks, useDelay);
    }

    @Benchmark
    public void customCollectorStatsBenchmark() {
        CustomStatsCollector.collectStats(tasks, useDelay);
    }

    @Benchmark
    public void concurrentCollectorStatsBenchmark() {
        ConcurrentStatsCollector.collectStats(tasks, useDelay);
    }

    @Benchmark
    public void improvedConcurrentCollectorStatsBenchmark() {
        ImprovedConcurrentStatsCollector.collectStats(tasks, useDelay);
    }
}
