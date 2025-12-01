package org.example.FirstComeFirstServe;

import org.example.Process;
import org.example.SchedulingAlgorithm;
import org.example.Statistics;


import java.util.ArrayList;
import java.util.List;

public class FirstComeFirstServeAlgorithm extends SchedulingAlgorithm {
    FirstComeFirstServeScheduling firstComeFirstServeScheduling;
    public FirstComeFirstServeAlgorithm(List<Process> list) {this.firstComeFirstServeScheduling = new FirstComeFirstServeScheduling(list);}

    @Override
    public List<String> run(List<Process> processes) {
        List<String> result = new ArrayList<>();
        result.add("-- Starting First Come First Serve Algorithm --");
        Process nextProcess = firstComeFirstServeScheduling.getNextProcess(0);
        while(nextProcess!=null){
            String outputLine = String.format("\t\t[currentTime=%d] Process %s is running", firstComeFirstServeScheduling.getCurrentTime(), nextProcess.getId());
            result.add(outputLine);
            firstComeFirstServeScheduling.updateDuration(nextProcess);
            nextProcess = firstComeFirstServeScheduling.getNextProcess(0);
            if (nextProcess == null) {
                result.add("-- Ending First Come First Serve Algorithm --");
                break;
            }
        }
        return result;
    }

    @Override
    public void printStatistics() {
        Statistics[] statistics = firstComeFirstServeScheduling.getStatistics();
        System.out.println("--- First Come First Serve Statistics ---");
        for (Statistics firstComeFirstServeProcessClass: statistics){
            System.out.println("\t\t" + firstComeFirstServeProcessClass.toString());
        }
        System.out.println("-- First Come First Serve Statistics --");
    }
}
