package org.example.roundrobin;

import org.example.Process;
import org.example.SchedulingInterface;

import java.util.List;

public class RoundRobinScheduling implements SchedulingInterface {

    private final List<Process> list;
    private int COUNT_OF_PROCESSES;

    public RoundRobinScheduling(List<Process> list) {
        this.list = list;
        this.COUNT_OF_PROCESSES = list.size();
    }

    @Override
    public Process getNextProcess(int currentProcessId) {
        if (COUNT_OF_PROCESSES <= 0) return null;
        int size = list.size();
        int start = (currentProcessId + 1) % size;
        for (int checked = 0; checked < size; checked++) {
            int index = (start + checked) % size;
            Process process = list.get(index);
            if (!process.isFinished()) return process;
        }
        return null;
    }

    @Override
    public boolean deleteProcess(Process process) {
        process.setFinished();
        this.COUNT_OF_PROCESSES--;
        return true;
    }

    @Override
    public void updateDuration(Process process) {
        int newTime = process.getProcessTime() - ROUND;
        if (newTime <= 0) {
            deleteProcess(process);
        } else {
            process.setDuration(newTime);
        }
    }

    public int getCOUNT_OF_PROCESSES() { return COUNT_OF_PROCESSES; }
}
