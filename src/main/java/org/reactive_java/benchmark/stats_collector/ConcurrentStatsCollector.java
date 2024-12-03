package org.reactive_java.benchmark.stats_collector;

import org.reactive_java.collector.ConcurrentUserTasksCompletionCollector;
import org.reactive_java.model.Task;
import org.reactive_java.model.User;

import java.util.List;
import java.util.Map;

public class ConcurrentStatsCollector {
    public static Map<User, Map<Task, Boolean>> collectStats(List<Task> tasks, boolean useDelay) {
        //        var mapSize = map.values().stream().mapToInt(Map::size).sum();
//        if (mapSize > 5000) {
//            System.out.println("Concurrent Stats Collector total tasks in map: " + mapSize);
//        }
        return tasks.parallelStream().collect(ConcurrentUserTasksCompletionCollector.toTaskStatusStatsMap(useDelay));
    }
}
