package org.example;


public class Process {
    
    private static int counter = 0;
    private int id;
    private int duration;
    private int startTime;
    private boolean finished;

    private String processType;  // For multilevel scheduling
    public static final String SYSTEM = "system";
    public static final String INTERACTIVE = "interactive";

    // For multiLevelFeedback (MLF)
    private int priorityMLF = 0;
    private static final int NO_MLF_PROCESS = -1;
    
    
    // For Priority Scheduling [Lower -> Higher Priority]
    private int priority;
    private static final int NO_PRIORITY = -1;
    
 
    public Process(int time, int startTime) {
        this.id = counter++;
        this.duration = time;
        this.startTime = startTime;
        this.finished = false;
        this.processType = "general";
        this.priorityMLF = NO_MLF_PROCESS;
        this.priority = NO_PRIORITY; 
    }

    public Process(int time, int startTime, String type) {
        this(time, startTime);
        this.processType = type;
    }

    public Process(int time, int startTime, int priority) {
        this(time, startTime);
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }

    public int getProcessTime() {
        return this.duration;
    }

    public int getId() {
        return this.id;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public void setFinished() {
        this.finished = true;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public void setProcessType(String type) {
        this.processType = type;
    }

    public String getProcessType() {
        return this.processType;
    }

    // For MultiLevelFeedback (MLF)
    public int getPriorityMLF() {
        return this.priorityMLF;
    }

    public void setPriorityMLF(int p) {
        this.priorityMLF = p;
    } 

    public String toString() {
        String result = "id = " + this.id + ", duration = " + this.duration + ", startTime = " + this.startTime;
        if (!this.processType.equals("general")) {
            result += ", processType: " + this.processType;
        }

        else if (this.priority != NO_PRIORITY) {
            result += ", processPriority: " + this.priority;
        }

        return result;
    }
    
}
