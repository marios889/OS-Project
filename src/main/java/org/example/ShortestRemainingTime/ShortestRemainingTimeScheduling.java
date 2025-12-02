package org.example.ShortestRemainingTime;

import org.example.Process;
import org.example.SchedulingInterface;
import org.example.Statistics;

import java.util.LinkedList;
import java.util.List;

public class ShortestRemainingTimeScheduling implements SchedulingInterface {

    public ShortestRemainingTimeScheduling() {

    }

    @Override
    public Process getNextProcess(int currentProcessId) {
        return null;
    }

    @Override
    public boolean deleteProcess(Process process) {
        return false;
    }

    @Override
    public void updateDuration(Process process) {
        return;
    }

}
