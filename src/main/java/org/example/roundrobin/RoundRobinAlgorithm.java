package org.example.roundrobin;

import org.example.Process;
import org.example.SchedulingAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class RoundRobinAlgorithm extends SchedulingAlgorithm {
    RoundRobinScheduling roundRobinScheduling;
    public RoundRobinAlgorithm(List<Process> list) {this.roundRobinScheduling = new RoundRobinScheduling(list);}

    @Override
    public List<String> run(List<Process> list) {
        List<String> result = new ArrayList<>();
        result.add("-- Starting Round Robin Algorithm --");
        Process process = list.get(0);
        while(roundRobinScheduling.getCOUNT_OF_PROCESSES() > 0){
            String s = "\t\tprocess " + process.getId() + " is running";
            result.add(s);
            roundRobinScheduling.updateDuration(process);
            process = roundRobinScheduling.getNextProcess(process.getId());
            if (process == null) {result.add("-- Ending Round Robin Algorithm --");break;}
        }
        return result;
    }
}
