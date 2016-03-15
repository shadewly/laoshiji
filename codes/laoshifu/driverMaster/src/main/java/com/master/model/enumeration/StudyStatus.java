package com.master.model.enumeration;

/**
 * Created by Wang Linyong on 2016/3/15.
 */
public enum StudyStatus {
    ENROLLED("E", "已报名"), LEARNING("L", "学习中"), FINISHED("F", "已完成");
    private String code;
    private String desc;

    StudyStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
