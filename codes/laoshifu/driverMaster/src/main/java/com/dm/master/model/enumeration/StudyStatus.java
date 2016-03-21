package com.dm.master.model.enumeration;

/**
 * Created by Wang Linyong on 2016/3/15.
 */
public enum StudyStatus {
    ENROLLED("E", "�ѱ���"), LEARNING("L", "ѧϰ��"), FINISHED("F", "�����");
    private String code;
    private String desc;

    StudyStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
