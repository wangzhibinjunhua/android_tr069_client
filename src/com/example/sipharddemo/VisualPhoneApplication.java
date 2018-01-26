package com.example.sipharddemo;

import android.app.Application;

public class VisualPhoneApplication extends Application {
	
	private static VisualPhoneApplication sInstance;
	
    public VisualPhoneApplication() {
    	
    	sInstance = this;
    }
	
	public static VisualPhoneApplication getInstance() {
		
		return sInstance;
	}
}
