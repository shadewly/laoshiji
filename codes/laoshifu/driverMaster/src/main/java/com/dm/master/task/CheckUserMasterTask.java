package com.dm.master.task;

import com.dm.master.model.UserMaster;


public class CheckUserMasterTask implements Runnable {

	private boolean result = false;
	private static final UserMaster userMaster;
	
	private  CheckUserMasterTask(UserMaster userMaster){
		this.userMaster=userMaster;
	}

	@Override
	public void run() {
		if (result == false) {
			 Thread.currentThread()
		}

	}

}
