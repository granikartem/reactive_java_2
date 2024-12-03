package org.reactive_java.benchmark.stats_collector;

import org.reactive_java.collector.UserTasksCompletetionCollector;
import org.reactive_java.model.Task;
import org.reactive_java.model.User;

import java.util.List;
import java.util.Map;

public class CustomStatsCollector {
    public static Map<User, Map<Task, Boolean>> collectStats(List<Task> tasks, boolean useDelay) {
        return tasks.stream().collect(UserTasksCompletetionCollector.toTaskStatusStatsMap(useDelay));
    }
}
