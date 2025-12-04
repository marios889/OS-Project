package org.example.FirstComeFirstServe;

import org.example.Process;
import org.example.SchedulingInterface;
import org.example.Statistics;

import java.util.LinkedList;
import java.util.List;

public class FirstComeFirstServeScheduling implements SchedulingInterface {
    private final List<Process> list;
    private int COUNT_OF_PROCESSES;
    private LinkedList<Process> queue = new LinkedList<>();
    private int time = 0;
    private Statistics[] statistics;
    

    public FirstComeFirstServeScheduling(List<Process> list) {
        this.list = list;
        this.COUNT_OF_PROCESSES = list.size();
        statistics = new Statistics[COUNT_OF_PROCESSES];
        for (int i = 0; i < COUNT_OF_PROCESSES; i++) {
            statistics[i] = new Statistics();
            statistics[i].setOriginalTime(list.get(i).getProcessTime());
        }
    }

    @Override
    public Process getNextProcess(int currentProcessId) {
        enqueueProcessesUpToCurrentTime();
        if (COUNT_OF_PROCESSES <= 0) return null;

        while (queue.isEmpty()) {
            time++;
            enqueueProcessesUpToCurrentTime();
        }

        if (!queue.isEmpty()) {
            Process nextProcess = queue.poll();
            return nextProcess;
        }
        return null;
    }

    private void enqueueProcessesUpToCurrentTime() {
        for (Process process : list) {
            if (process.getStartTime() <= time && !queue.contains(process) && !process.isFinished()) {
                queue.add(process);
            }
        }
    }




    @Override
    public boolean deleteProcess(Process process) {
        process.setFinished();
        COUNT_OF_PROCESSES--;
        return true;
    }

    @Override
    public void updateDuration(Process process) {
        time += process.getProcessTime();
        deleteProcess(process);
        statistics[process.getId()].setProcess(process);
        statistics[process.getId()].setEndingTime(time);
        statistics[process.getId()].setWaitingTime(time - process.getStartTime() - statistics[process.getId()].getOriginalTime());
    }


    public int getCurrentTime() {
        return time;
    }

    public Statistics[] getStatistics() {
        return statistics;
    }
}
