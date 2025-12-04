package org.example.Priority;


import org.example.Process;
import org.example.SchedulingInterface;
import org.example.Statistics;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;




public class PriorityScheduling implements SchedulingInterface {
    private final List<Process> processesList;
    private LinkedList<Process> queue;
    private int time = 0;
    private Statistics[] statistics;
    private int countOfProcesses;

    // ----------------------------------------------------------------------------------
    // Constructor
    public PriorityScheduling(List<Process> list) {
        // init variables
        this.processesList = list;
        this.countOfProcesses = list.size();
        statistics = new Statistics[countOfProcesses];
        queue = new LinkedList<>();
        
        // init statistics
        for (int i = 0; i < countOfProcesses; i++) {
            statistics[i] = new Statistics();
            statistics[i].setOriginalTime(list.get(i).getProcessTime());
        }
    }

    // ----------------------------------------------------------------------------------
    // Get Next Process
    
    @Override
    public Process getNextProcess(int currentProcessId) {
        enqueueProcessesUpToCurrentTime();
        if (countOfProcesses <= 0) return null;
        
        // All queues are empty
        if (queue.isEmpty()) {waitForNextProcess();}
        
        if (!queue.isEmpty()) {return queue.poll();} 
        
        return null;
    }

    public void enqueueProcessesUpToCurrentTime() {
        for (Process process : processesList) {
            if (process.getStartTime() <= time && !queue.contains(process) && !process.isFinished()) {
                queue.add(process);
            }

            Collections.sort(queue, new Comparator<Process>() {
                @Override
                public int compare(Process p1, Process p2) {
                    return Integer.compare(p1.getPriority(), p2.getPriority());
                }
            });
        }
    }
    
    private void waitForNextProcess() {
        while (queue.isEmpty() && countOfProcesses > 0) {
            time++;
            enqueueProcessesUpToCurrentTime();
        }
    }

    // ----------------------------------------------------------------------------------

    // Deleting a Process
    @Override
    public boolean deleteProcess(Process process) {
        process.setFinished();
        countOfProcesses --;
        return true;
    }
    
    // ----------------------------------------------------------------------------------

    // Updating a Process Duration 
    @Override 
    public void updateDuration(Process process) {
        int processDuration = process.getProcessTime();
        time += processDuration;
        deleteProcess(process);
        update_statistics(process, time);
    }


    
    private void update_statistics(Process process, int time) {
        statistics[process.getId()].setProcess(process);
        statistics[process.getId()].setEndingTime(time);
        statistics[process.getId()].setWaitingTime(time - statistics[process.getId()].getOriginalTime() - process.getStartTime());
    }

    // ----------------------------------------------------------------------------------------

    // Utility Functions
    public int getCurrentTime() {
        return time;
    }

    public Statistics[] getStatistics() {
        return statistics;
    }

}
