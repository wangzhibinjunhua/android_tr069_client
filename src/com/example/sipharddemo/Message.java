package com.example.sipharddemo;



public class Message
{
  protected String id;
  protected String action;
  protected String content;

  public Message()
  {
  }

  public Message(String id, String action)
  {
    this.id = id;
    this.action = action;
 
  }

  public String getAction() {
    return this.action;
  }
  public void setAction(String action) {
    this.action = action;
  }


  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}
  
  
  
  
}