package com.example.sipharddemo;

import java.io.UnsupportedEncodingException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;
public class DownXmlHandler extends DefaultHandler {


		
		private String preTag=null;
		private String downUrl=null;

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
			if("URL".equals(preTag)){
				try {
					downUrl = new String(new String(ch, start, length).getBytes(),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.endDocument();
			}
			
			Log.i("AAA", "DownXmlHandler :"+preTag+"=="+new String(ch,start,length));	
		
		}





		public String getDownUrl() {
			return downUrl;
		}




	
		
}
