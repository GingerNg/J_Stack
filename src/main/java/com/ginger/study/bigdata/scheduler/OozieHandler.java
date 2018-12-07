package com.ginger.study.bigdata.scheduler;

/**
 * Created by Ginger on 17-11-27
 */
public class OozieHandler {
    OozieScheduler oozieScheduler;

    MyOozieJobImpl myOozieJob;



    public void OozieJobHandler(){
        oozieScheduler.JobOperation(OperationType.Submit);
    }
}
