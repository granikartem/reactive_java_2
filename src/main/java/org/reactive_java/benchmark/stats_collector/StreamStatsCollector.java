package org.reactive_java.benchmark.stats_collector;

import org.reactive_java.model.Task;
import org.reactive_java.model.User;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamStatsCollector {
    public static Map<User, Map<Task, Boolean>> collectStats(List<Task> tasks, boolean useDelay) {
        return tasks.stream().collect(
                Collectors.toMap(
                        task -> {
                            try {
                                return task.getUser(useDelay);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        task -> {
                            boolean taskCompletedOnTime = task.getStatuses()
                                    .stream()
                                    .noneMatch(
                                            taskStatus -> Duration.between(taskStatus.startTime(), taskStatus.finishTime())
                                                    .compareTo(task.getEvaluation().getStatusDurationMap().get(taskStatus.status())) > 0
                                    );

                            var result = new HashMap<Task, Boolean>();
                            result.put(task, taskCompletedOnTime);

                            return new HashMap<>(result);
                        },
                        (map1, map2) -> {
                            map1.putAll(map2);
                            return map1;
                        }
                )
        );
    }
}
