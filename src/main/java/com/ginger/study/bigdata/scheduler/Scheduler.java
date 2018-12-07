package com.ginger.study.bigdata.scheduler;

import java.util.Properties;

/**
 * Created by Ginger on 17-11-9
 * 单例
 */
public interface Scheduler {

    public String submitJob(Job job);

    public String getJobInfo(String JobId);

    public void JobOperation(String jobId, OperationType type);

    public void JobOperation(String jobId, OperationType type, Properties jobConf);

    public String JobOperation(OperationType type, Properties jobConf);

    public void JobOperation(OperationType type);

}
