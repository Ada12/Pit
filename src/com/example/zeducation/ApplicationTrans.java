package com.example.zeducation;

import android.app.Application;

public class ApplicationTrans extends Application {
	private String username;
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String email){
		this.username = email;
	}
}
