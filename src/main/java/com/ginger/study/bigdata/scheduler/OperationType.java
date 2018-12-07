package com.ginger.study.bigdata.scheduler;

/**
 * Created by Ginger on 17-11-27
 */
public enum OperationType {

    Submit("提交"),
    Start("首次开始任务"),
    ReRun("再次运行"),
    Kill("杀死任务"),
    GetJobInfo("获取任务信息")
    ;

    private String description;

    OperationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
