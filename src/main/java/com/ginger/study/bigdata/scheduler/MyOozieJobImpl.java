package com.ginger.study.bigdata.scheduler;


import java.util.Properties;

/**
 * Created by Ginger on 17-11-27
 */
public class MyOozieJobImpl implements Job{

    Properties conf;

    String jobId;

    public void setConf(Properties conf) {
        this.conf = conf;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Override
    public Properties getConf() {
        return null;
    }
}
