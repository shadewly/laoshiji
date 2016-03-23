package com.lsf.util;

/**
 * Created by ZY on 2016/3/15.
 */
public interface HttpCallbackListener {
    public void onFinish(String response);

    public void onError(Exception e);

}
