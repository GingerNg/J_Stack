package com.ginger.study.bigdata.scheduler;

import java.util.Properties;

/**
 */
public class StudyOozieJob extends OozieJob{

    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    private Properties conf = new Properties();

    public Properties getConf() {
        if (conf.isEmpty()){
            // 对成员变量放入conf
            conf.setProperty("nameNode", super.getNamenode());
            conf.setProperty("queueName", super.getQueueName());
            conf.setProperty("oozie.wf.application.path", super.getAppPath());
            conf.setProperty("jobTracker", super.getJobTracker());
            conf.setProperty("inputDir", super.getInputDir());
            conf.setProperty("outputDir", super.getOutputDir());
            conf.setProperty("jobName", super.getJobName());
            conf.setProperty("groupId",getGroupId());
        }

        return conf;
    }

    public void setConf(Properties conf) {
        this.conf = conf;
    }
}
