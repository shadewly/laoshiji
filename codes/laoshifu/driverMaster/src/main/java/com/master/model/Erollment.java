package com.master.model;

import com.master.model.enumeration.StudyStatus;

import java.util.Date;

/**
 * 报名表
 */
public class Erollment {
    private Integer id; //id
    private String scheduleNo;   //发布课程号
    private StudyStatus status;//学习状态：报名0，开始上课1，结束2
    private Date trainStartTime; //学员这一次学习开始时间
    private Date trainEndTime;   //学员这一次学习结束时间
    private Account account;//账户

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScheduleNo() {
        return scheduleNo;
    }

    public void setScheduleNo(String scheduleNo) {
        this.scheduleNo = scheduleNo;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public void setStatus(StudyStatus status) {
        this.status = status;
    }

    public Date getTrainStartTime() {
        return trainStartTime;
    }

    public void setTrainStartTime(Date trainStartTime) {
        this.trainStartTime = trainStartTime;
    }

    public Date getTrainEndTime() {
        return trainEndTime;
    }

    public void setTrainEndTime(Date trainEndTime) {
        this.trainEndTime = trainEndTime;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}