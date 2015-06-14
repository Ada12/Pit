package com.example.zeducation;

import android.app.Application;

public class ApplicationTrans extends Application {
	private String username;
	private String flag;
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String email){
		this.username = email;
	}
	
	public String getFlag(){
		return flag;
	}
	
	public void setFlag(String _flag){
		this.flag = _flag;
	}
}
