package com.ginger.study.bigdata.scheduler;

/**
 * Created by Ginger on 17-11-9
 */
public abstract class OozieJob implements Job{

    private String namenode = "hdfs://X.X.X.X:9000";

    private String jobTracker = "X.X.X.X:8032";

    private String queueName = "default";

    private String inputDir;

    private String outputDir;

    private String appPath;

    private String jobName;


    public String getJobTracker() {
        return jobTracker;
    }

    public void setJobTracker(String jobTracker) {
        this.jobTracker = jobTracker;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getInputDir() {
        return inputDir;
    }

    public void setInputDir(String inputDir) {
        this.inputDir = inputDir;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getNamenode() {

        return namenode;
    }

    public void setNamenode(String namenode) {
        this.namenode = namenode;
    }


}
