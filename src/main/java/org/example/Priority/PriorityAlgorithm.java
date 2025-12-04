package org.example.Priority;

import org.example.Process;
import org.example.SchedulingAlgorithm;
import org.example.Statistics;

import java.util.ArrayList;
import java.util.List;

public class PriorityAlgorithm extends SchedulingAlgorithm {

    PriorityScheduling PriorityScheduling;
    public PriorityAlgorithm(List<Process> list) {
        this.PriorityScheduling =  new PriorityScheduling(list);
    }

    @Override
    public List<String> run(List<Process> list) {
        List<String> result = new ArrayList<>();
        result.add("-- Starting Priority Algorithm --");

        Process process = PriorityScheduling.getNextProcess(0);

        while(process != null) {
            // If another process came while this is running
            // PriorityScheduling.enqueueProcessesUpToCurrentTime();


            // Update Output
            int priority = process.getPriority();
            String outputLine = String.format(
                "\t\t" + "[current time = %d] process %d is running, process type: %s, with Priority: %d",
                PriorityScheduling.getCurrentTime(),
                process.getId(),
                process.getProcessType(),
                priority
            );

            result.add(outputLine);

            PriorityScheduling.updateDuration(process);
            process = PriorityScheduling.getNextProcess(0);
            if (process == null) {
                result.add("-- Ending Priority Algorithm --");
                break;
            }
        }
        return result;
    }

    public void printStatistics(){
        Statistics[] arr = PriorityScheduling.getStatistics();
        System.out.println("--- Priority Algorithm Statistics ---");

        for (Statistics ele:arr){
            System.out.println("\t\t" + ele.toString());
        }
        System.out.println("-- Ending Priority Algorithm Statistics --");
    }
}
