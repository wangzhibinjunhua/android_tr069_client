package com.example.sipharddemo;

import java.io.UnsupportedEncodingException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class SipXmlHandler extends DefaultHandler  {
	private String preTag=null;
	private SipObject sipObject;

	public void endDocument() throws SAXException {
		super.endDocument();
	}
	
	
	
	

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		preTag = "";
		
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		sipObject = new SipObject();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		preTag = localName;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if("SIP_Account_Username".equals(preTag)){
			sipObject.setSip_Account_UserName(new String(ch,start,length));
		}else if("SIP_Account_Password".equals(preTag)){
			sipObject.setSip_Account_Password(new String(ch,start,length));
		}else if("User_Domain".equals(preTag)){
			sipObject.setSip_User_Domain(new String(ch,start,length));
		}else if("SIP_Proxy".equals(preTag)){
			sipObject.setSip_Proxy(new String(ch,start,length));
		}else if("Proxy_Port".equals(preTag)){
			sipObject.setSip_Proxy_Port(new String(ch,start,length));
		}else if("SIP_Backup_Proxy".equals(preTag)){
			sipObject.setSip_Backup_Proxy(new String(ch,start,length));
		}else if("Backup_Proxy_Port".equals(preTag)){
			sipObject.setSip_Backup_Proxy_Port(new String(ch,start,length));
		}else if("Info_Portal".equals(preTag)){
			sipObject.setInfo_Portal(new String(ch,start,length));
		}else if("TEL_Portal".equals(preTag)){
			sipObject.setTel_Portal(new String(ch,start,length));
		}else if("Best_Portal".equals(preTag)){
			sipObject.setBest_Poratl(new String(ch,start,length));
		}else if("Update_Server".equals(preTag)){
			sipObject.setUpdate_Server(new String(ch,start,length));
		}else if("App_Store".equals(preTag)){
			sipObject.setApp_Store(new String(ch,start,length));
		}else if("HTTP_Proxy".equals(preTag)){
			sipObject.setHttp_Proxy(new String(ch,start,length));
		}else if("HTTP_Proxy_Port".equals(preTag)){
			sipObject.setHttp_Proxy_Port(new String(ch,start,length));
		}else if("iTV_Portal".equals(preTag)){
			sipObject.setiTv_Portal(new String(ch,start,length));
		}else if("Conf_Portal".equals(preTag)){
			sipObject.setConf_Portal(new String(ch,start,length));
		}else if("NTP_Server".equals(preTag)){
			sipObject.setNtp_Server(new  String(ch,start,length));
		}
		
		Log.i("AAA", "DownXmlHandler :"+preTag+"=="+new String(ch,start,length));	
	
	}





	public SipObject getSipObject() {
		return sipObject;
	}





	public void setSipObject(SipObject sipObject) {
		this.sipObject = sipObject;
	}
	
	
}
