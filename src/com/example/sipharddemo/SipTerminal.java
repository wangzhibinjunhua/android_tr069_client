package com.example.sipharddemo;

public class SipTerminal {
	public static SipTerminal instance;
	//private MessageThread thread;
	private MessageThreadNew thread;
	public static SipTerminal getInstance (){
		if(instance==null){
			instance = new SipTerminal();
		}
		return instance;
	}

	public void startConnectionWithACS(){
		if(thread!=null&&thread.isAlive()){
			return ;
		}
		//thread = new MessageThread();
		thread = new MessageThreadNew();
		thread.start();
	}	
	
	
	public void reConnectionWithACS(){
		if(thread!=null&&thread.isAlive()){
			thread.destroy();
		}
		//thread = new MessageThread();
		thread = new MessageThreadNew();
		thread.start();
	}	

}
