package org.reactive_java.benchmark.stats_collector;

import org.reactive_java.model.Task;
import org.reactive_java.model.TaskStatus;
import org.reactive_java.model.User;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IteratorStatsCollector {
    public static Map<User, Map<Task, Boolean>> collectStats(List<Task> tasks, boolean useDelay) throws InterruptedException {
        var userTasksCompletionMap = new HashMap<User, Map<Task, Boolean>>();
        for (Task task : tasks) {
            User user = task.getUser(useDelay);

            if (!userTasksCompletionMap.containsKey(user)) {
                userTasksCompletionMap.put(user, new HashMap<>());
            }

            var taskStatuses = task.getStatuses();
            boolean taskCompletedOnTime = true;

            for (TaskStatus taskStatus : taskStatuses) {
                taskCompletedOnTime &= Duration.between(taskStatus.startTime(), taskStatus.finishTime())
                        .compareTo(task.getEvaluation().getStatusDurationMap().get(taskStatus.status())) > 0;
            }

            userTasksCompletionMap.get(user).put(task, taskCompletedOnTime);
        }
        return userTasksCompletionMap;
    }
}
