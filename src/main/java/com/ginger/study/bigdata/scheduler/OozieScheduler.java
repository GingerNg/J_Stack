package com.ginger.study.bigdata.scheduler;

import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.OozieClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Properties;

/**
 */
public class OozieScheduler implements Scheduler{

    private static final Logger logger = LoggerFactory.getLogger(OozieScheduler.class);

    String OozieUrl = "http://X.X.X.X:11000/00zie";  // test

    String userName = "XXX";  //test

    OozieClient wc = null;

    Properties jobConf = new Properties();

    String jobId;

    public void init(){
        wc = new OozieClient(OozieUrl);
    }

    public String submitJob(Job job){

        Properties jobConf = job.getConf();

        jobConf.setProperty("user.name",userName);  // todo
        jobConf.setProperty("oozie.wf.rerun.failnodes", "true");

        String jobId = null;
        try {
//            wc.reRun("0000828-171012133832623-oozie-XXX-W",jobConf);
            jobId = wc.submit(jobConf);
        } catch (OozieClientException e) {
            logger.error("提交任务失败:{}",e.getMessage());
        }
        return jobId;
    }

    public void jobRerun(String jobId, Job job){
        Properties jobConf = job.getConf();

        jobConf.setProperty("user.name",userName);  // todo
        jobConf.setProperty("oozie.wf.rerun.failnodes", "true");

        String jobStatus = null;
        try{
            wc.start(jobId);
//            wc.reRun(jobId, jobConf);
        } catch (OozieClientException e){
            logger.error("查询任务失败:{}",e.getMessage());
            throw new RuntimeException(e);
        }
//        return jobStatus;
    }

    public String getJobInfo(String jobId){
        String jobStatus = null;
        try{
            wc.kill(jobId);
            jobStatus = wc.getJobInfo(jobId).getStatus().toString();  // WorkflowJob
//            System.out.println(wc.getJobInfo(jobId).getAppName());
//            System.out.println(wc.getJobInfo(jobId).getConf());   // 返回xml文件.内容与conf一直
//            xml2properties.xmlStr2pros(wc.getJobInfo(jobId).getConf());
//            wc.getJobsInfo("preCollision-map-reduce-wf"); //WorkflowJob
            return jobStatus;
        } catch (OozieClientException e){
            logger.error("查询任务失败:{}",e.getMessage());
            throw new RuntimeException(e);
        }
//        return jobStatus;
    }

    public void JobOperation(String jobId, OperationType type){
        try{
            switch (type){
                case Kill:wc.kill(jobId);break;
                case GetJobInfo:wc.getJobInfo(jobId);break;
                case Start:wc.start(jobId);break;
            }
        }catch (OozieClientException e){
            logger.error("查询任务失败:{}",e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public void JobOperation(String jobId, OperationType type, Properties jobConf){
        try{
            wc.reRun(jobId,jobConf);
        }catch (OozieClientException e){
            logger.error("查询任务失败:{}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String JobOperation(OperationType type, Properties jobConf){
        try{
                return wc.submit(jobConf);
        }catch (OozieClientException e){
            logger.error("查询任务失败:{}",e.getMessage());
            throw new RuntimeException(e);
        }

    }


    public void JobOperation(OperationType type){
        try{
            switch (type){
                case Submit:wc.submit(jobConf);break;
                case ReRun:wc.reRun(jobId, jobConf);break;
                case Kill:wc.kill(jobId);break;
                case GetJobInfo:wc.getJobInfo(jobId);break;
                case Start:wc.start(jobId);break;
            }
        }catch (OozieClientException e){
            logger.error("查询任务失败:{}",e.getMessage());
            throw new RuntimeException(e);
        }

    }


    public void setOozieUrl(String oozieUrl) {
        OozieUrl = oozieUrl;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static void main(String[] args){

        OozieScheduler scheduler = new OozieScheduler();
        StudyOozieJob studyOozieJob = new StudyOozieJob();

        scheduler.setOozieUrl("http://X.X.X.X:11000/oozie");
        scheduler.setUserName("oozie");

        scheduler.init();

        Properties conf = new Properties();
        conf.setProperty("nameNode", "hdfs://XX.X.X.X:8020");
        conf.setProperty("queueName", "default");
        conf.setProperty("examplesRoot", "examples");
        conf.setProperty("oozie.wf.application.path","${nameNode}/user/${user.name}/${examplesRoot}/apps/map-reduce/workflow.xml");
        conf.setProperty("outputDir", "map-reduce");
        conf.setProperty("jobTracker", "X.X.X.X:8021");
//    conf.setProperty("groupId","XXX");
        studyOozieJob.setConf(conf);

        //TODO: Test goes here...
        long startTime = new Date().getTime();
        System.out.println(scheduler.getJobInfo("XX-oozie-clou-W"));
        long endTime = new Date().getTime();
        logger.info("查询任务所用时间:{}",(double)(endTime-startTime));  //  0 ms


    }
}
