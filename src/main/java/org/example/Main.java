package org.example;

import org.example.FirstComeFirstServe.FirstComeFirstServeAlgorithm;
import org.example.roundrobin.RoundRobinAlgorithm;
import org.example.sjf.SJFAlgorithm;
import org.example.ShortestRemainingTime.ShortestRemainingTimeAlgorithm;
import org.example.MultiLevel.MultiLevelAlgorithm;
import org.example.MultiLevelFeedback.MultiLevelFeedbackAlgorithm;
import org.example.Priority.PriorityAlgorithm;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // cfg
        Generator generator = new Generator();
        System.out.println("1.FCFS\n2.SJF\n3.SRT\n4.Round Robin\n5.Priority scheduling\n6.Multi level scheduling\n7.Multi level Feedback scheduling");
        Scanner input = new Scanner(System.in);
        boolean NO_PRIORITY = false;
        boolean PRIORITY = true;
        boolean NO_MULTI_LEVEL = false;
        boolean MULTI_LEVEL = true;

        // define Processes & Algorithm

        List<Process> processList;
        SchedulingAlgorithm schedulingAlgorithm;
        List<String> result;
        System.out.println("-".repeat(100));
        switch (input.nextInt()) {
            case 1:
                ///  put your code here.
                ///  Naming Convention -> (Algorithm name)List, (Algorithm name)Result
                
                processList = generator.getProcessList(false, NO_PRIORITY);
                schedulingAlgorithm = new FirstComeFirstServeAlgorithm(processList);
                result = schedulingAlgorithm.run(processList);
                break;
            case 2:
                processList = generator.getProcessList(false, NO_PRIORITY);
                schedulingAlgorithm = new SJFAlgorithm(processList);
                result = schedulingAlgorithm.run(processList);
                break;
            case 3:
                processList = generator.getProcessList(NO_MULTI_LEVEL, NO_PRIORITY);
                schedulingAlgorithm = new ShortestRemainingTimeAlgorithm(processList);
                result = schedulingAlgorithm.run(processList);
                break;
            case 4:
                processList = generator.getProcessList(NO_MULTI_LEVEL, NO_PRIORITY);
                schedulingAlgorithm = new RoundRobinAlgorithm(processList);
                result = schedulingAlgorithm.run(processList);
                break;
                
            case 5:
                processList = generator.getProcessList(NO_MULTI_LEVEL, PRIORITY);
                schedulingAlgorithm = new PriorityAlgorithm(processList);
                result = schedulingAlgorithm.run(processList);
                break;

            case 6:
                processList = generator.getProcessList(MULTI_LEVEL, NO_PRIORITY);
                schedulingAlgorithm = new MultiLevelAlgorithm(processList);
                result = schedulingAlgorithm.run(processList);
                break;


            case 7:
                processList = generator.getProcessList(MULTI_LEVEL, NO_PRIORITY);
                schedulingAlgorithm = new MultiLevelFeedbackAlgorithm(processList);
                result = schedulingAlgorithm.run(processList);
                break;

            default:
                System.out.println("Please, Enter a valid input");
                return;
        }

        ///  Print Result here
        schedulingAlgorithm.print(result);
        schedulingAlgorithm.printStatistics();
    }
}
