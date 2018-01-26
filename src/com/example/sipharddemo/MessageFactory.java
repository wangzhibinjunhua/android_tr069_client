package com.example.sipharddemo;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;
import org.apache.http.impl.cookie.DateUtils;
import org.xmlpull.v1.XmlSerializer;
import android.util.Log;
import android.util.Xml;

public class MessageFactory {
	public enum MessageType {
		Inform, GetParameterNamesResponse, GetParameterValuesResponse, DownloadResponse, RebootResponse,
		GetRPCMethodsReponse,
	}

	private static String createId() {
		return DateUtils.formatDate(new Date(), "MMddHHmmssSSS");
	}

	public static String createXml(MessageType type, Map<String, String> info) {
		String xml = null;
		switch (type) {
		case Inform:

			xml = wcreateInformXml(info);
			break;
		case GetParameterNamesResponse:
			xml = wcreateGetParameterNamesResponseXml(info);
			break;
		case GetParameterValuesResponse:
			xml = wcreateGetParameterValuesResponseXml(info);
			break;
		case DownloadResponse:
			xml = wcreateDownloadResponseXml(info);
			break;
		case RebootResponse:
			xml = wcreateRebootResponseXml(info);
			break;
		case GetRPCMethodsReponse:
			xml=wcreateGetRPCMethodsResponseXml(info);
			break;
		default:
			break;

		}

		return xml;

	}

	public static String wcreateInformXml(Map<String, String> info) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			// <?xml version=��1.0�� encoding=��UTF-8��>
			serializer.startDocument(null, null);// Write <?xml declaration with
													// encoding (if encoding not
													// null) and standalone flag
													// (if stan dalone not null)
													// This method can only be
													// called just after
													// setOutput.
			serializer.startTag("", "soap:Envelope");
			serializer.attribute("", "xmlns:soap", "http://schemas.xmlsoap.org/soap/envelope/");
			serializer.attribute("", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema/instance/");
			serializer.attribute("", "xmlns:xsd", "http://www.w3.org/2001/XMLSchema/");

			serializer.attribute("", "xmlns:cwmp", "urn:dslforum-org:cwmp-1-0");
			serializer.attribute("", "soap:encodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
			// head

			/**
			 * serializer.startTag("", "cwmp:Inform");
			 * 
			 * serializer.endTag("", "cwmp:Inform");
			 */

			// body
			serializer.startTag("", "soap:Body");
			// inform
			serializer.startTag("", "cwmp:Inform");
			// deviceId
			serializer.startTag("", "DeviceId");

			serializer.startTag("", "Manufacturer");
			serializer.text("ZYKX");
			serializer.endTag("", "Manufacturer");

			serializer.startTag("", "OUI");// OUI
			serializer.text("001E10");
			// serializer.text(info.get("OUI"));
			serializer.endTag("", "OUI");

			serializer.startTag("", "ProductClass");// ProductClass
			serializer.text("G52");
			// serializer.text(info.get("ProductClass"));
			serializer.endTag("", "ProductClass");

			serializer.startTag("", "SerialNumber");// SerialNumber
			serializer.text("0123456789ABCDEF");
			// serializer.text(info.get("SerialNumber"));
			serializer.endTag("", "SerialNumber");

			serializer.startTag("", "FirmwareVersion");// FirmwareVersion
			serializer.text("2.2.1");
			// serializer.text(info.get("FirmwareVersion"));
			serializer.endTag("", "FirmwareVersion");

			serializer.startTag("", "DeviceNo");// DeviceNo
			serializer.text("0320121009001341");
			// serializer.text(info.get("DeviceNo"));
			serializer.endTag("", "DeviceNo");

			serializer.endTag("", "DeviceId");

			// Envent
			serializer.startTag("", "Event");
			serializer.attribute("", "soap:arrayType", "cwmp:EventStruct[1]");

			serializer.startTag("", "EventStruct");
			serializer.startTag("", "EventCode");
			serializer.text("1 BOOT");
			serializer.endTag("", "EventCode");
			serializer.startTag("", "CommandKey");
			serializer.endTag("", "CommandKey");
			serializer.endTag("", "EventStruct");

			serializer.endTag("", "Event");

			serializer.startTag("", "MaxEnvelopes");
			serializer.text("1");
			serializer.endTag("", "MaxEnvelopes");
			serializer.startTag("", "CurrentTime");
			serializer.endTag("", "CurrentTime");
			serializer.startTag("", "RetryCount");
			serializer.text("0");
			serializer.endTag("", "RetryCount");

			serializer.startTag("", "ParameterList");
			serializer.attribute("", "soap:arrayType", "cwmp:ParameterValueStruct[4]");
			// serializer.startTag("","");
			// serializer.text("");
			// serializer.endTag("","");

			serializer.startTag("", "ParameterValueStruct");// ParameterValueStruct1
			serializer.startTag("", "Name");
			serializer.text("Device.ManagementServer.URL");
			serializer.endTag("", "Name");

			serializer.startTag("", "Value");
			serializer.attribute("", "xsi:type", "xsd:string");
			serializer.text("http://183.62.12.22:8078/ims_ws/TerminalServlet");
			serializer.endTag("", "Value");
			serializer.endTag("", "ParameterValueStruct");

			serializer.startTag("", "ParameterValueStruct");// ParameterValueStruct2
			serializer.startTag("", "Name");
			serializer.text("Device.ManagementServer.ConnectionRequestURL");
			serializer.endTag("", "Name");

			serializer.startTag("", "Value");
			serializer.attribute("", "xsi:type", "xsd:string");
			serializer.text("http://193.168.1.228:8080");
			serializer.endTag("", "Value");
			serializer.endTag("", "ParameterValueStruct");

			serializer.startTag("", "ParameterValueStruct");// ParameterValueStruct3
			serializer.startTag("", "Name");
			serializer.text("Device.DeviceInfo.HardwareVersion");
			serializer.endTag("", "Name");

			serializer.startTag("", "Value");
			serializer.attribute("", "xsi:type", "xsd:string");
			serializer.text("2.2.1");
			serializer.endTag("", "Value");
			serializer.endTag("", "ParameterValueStruct");

			serializer.startTag("", "ParameterValueStruct");// ParameterValueStruct4
			serializer.startTag("", "Name");
			serializer.text("Device.DeviceInfo.SoftwareVersion");
			serializer.endTag("", "Name");

			serializer.startTag("", "Value");
			serializer.attribute("", "xsi:type", "xsd:string");
			serializer.text("2.2.1");
			serializer.endTag("", "Value");
			serializer.endTag("", "ParameterValueStruct");

			serializer.endTag("", "ParameterList");

			serializer.endTag("", "cwmp:Inform");

			serializer.endTag("", "soap:Body");

			serializer.endTag("", "soap:Envelope");

			serializer.endDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Log.d("wzb", "wzb CwmpMessageFactory---" + writer.toString());
		// return null;
		return writer.toString();
	}
	
	public static String wcreateGetRPCMethodsResponseXml(Map<String, String> info){
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument(null, null);
			serializer.startTag("", "soap:Envelope");
			serializer.attribute("", "xmlns:soap", "http://schemas.xmlsoap.org/soap/envelope/");
			serializer.attribute("", "xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
			serializer.attribute("", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			serializer.attribute("", "xmlns:cwmp", "urn:dslforum-org:cwmp-1-0");
			serializer.startTag("", "soap:Body");
			serializer.startTag("", "cwmp:GetRPCMethodsResponse");
			serializer.startTag("", "MethodList");
			serializer.attribute("", "soap:arrayType", "xsd:string[64][8]");
			
			serializer.startTag("", "Method");
			serializer.text("GetRPCMethods");
			serializer.endTag("", "Method");
			
			serializer.startTag("", "Method");
			serializer.text("SetParameterValues");
			serializer.endTag("", "Method");
			
			serializer.startTag("", "Method");
			serializer.text("GetParameterValues");
			serializer.endTag("", "Method");
			
			serializer.startTag("", "Method");
			serializer.text("GetParameterNames");
			serializer.endTag("", "Method");
			
			serializer.startTag("", "Method");
			serializer.text("Download");
			serializer.endTag("", "Method");
			
			serializer.startTag("", "Method");
			serializer.text("Upload");
			serializer.endTag("", "Method");
			
			serializer.startTag("", "Method");
			serializer.text("Reboot");
			serializer.endTag("", "Method");
			
			serializer.startTag("", "Method");
			serializer.text("testtest");
			serializer.endTag("", "Method");
			
			serializer.endTag("", "MethodList");
			serializer.endTag("", "cwmp:GetRPCMethodsResponse");
			serializer.endTag("", "soap:Body");
			serializer.endTag("", "soap:Envelope");
			serializer.endDocument();

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.d("wzb", "wCwmpMessageFactory---" + writer.toString());

		return writer.toString();
	}
	
	public static String wcreateRebootResponseXml(Map<String, String> info){
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument(null, null);
			serializer.startTag("", "soap:Envelope");
			serializer.attribute("", "xmlns:soap", "http://schemas.xmlsoap.org/soap/envelope/");
			serializer.attribute("", "xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
			serializer.attribute("", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			serializer.attribute("", "xmlns:cwmp", "urn:dslforum-org:cwmp-1-0");
			serializer.startTag("", "soap:Body");
			serializer.startTag("", "cwmp:RebootResponse");

			
			serializer.endTag("", "cwmp:RebootResponse");
			serializer.endTag("", "soap:Body");
			serializer.endTag("", "soap:Envelope");
			serializer.endDocument();

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.d("wzb", "wCwmpMessageFactory---" + writer.toString());

		return writer.toString();
	}

	public static String wcreateGetParameterNamesResponseXml(Map<String, String> info) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument(null, null);
			serializer.startTag("", "soap:Envelope");
			serializer.attribute("", "xmlns:soap", "http://schemas.xmlsoap.org/soap/envelope/");
			serializer.attribute("", "xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
			serializer.attribute("", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			serializer.attribute("", "xmlns:cwmp", "urn:dslforum-org:cwmp-1-0");
			serializer.startTag("", "soap:Body");
			serializer.startTag("", "cwmp:GetParameterNamesResponse");

			serializer.startTag("", "ParameterList");
			serializer.attribute("", "soap:arrayType", "cwmp:ParameterValueStruct[2]");
			serializer.startTag("", "ParameterInfoStruct");// ParameterValueStruct1
															// Username
			serializer.startTag("", "Name");
			serializer.text("Device.ManagementServer.Username");
			serializer.endTag("", "Name");
			serializer.startTag("", "Writable");
			serializer.text("true");
			serializer.endTag("", "Writable");
			serializer.endTag("", "ParameterInfoStruct");

			serializer.startTag("", "ParameterInfoStruct");// ParameterValueStruct2
			// password
			serializer.startTag("", "Name");
			serializer.text("Device.ManagementServer.Password");// password
			serializer.endTag("", "Name");
			serializer.startTag("", "Writable");
			serializer.text("true");
			serializer.endTag("", "Writable");
			serializer.endTag("", "ParameterInfoStruct");

			serializer.endTag("", "ParameterList");
			serializer.endTag("", "cwmp:GetParameterNamesResponse");
			serializer.endTag("", "soap:Body");
			serializer.endTag("", "soap:Envelope");
			serializer.endDocument();

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.d("wzb", "wCwmpMessageFactory---" + writer.toString());

		return writer.toString();
	}

	private static String wcreateGetParameterValuesResponseXml(Map<String, String> info) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			// <?xml version=��1.0�� encoding=��UTF-8��>
			serializer.startDocument(null, null);// Write <?xml declaration with
													// encoding (if encoding not
													// null) and standalone flag
													// (if stan dalone not null)
													// This method can only be
													// called just after
													// setOutput.
			serializer.startTag("", "soap:Envelope");
			serializer.attribute("", "xmlns:soap", "http://schemas.xmlsoap.org/soap/envelope/");
			serializer.attribute("", "xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
			serializer.attribute("", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			serializer.attribute("", "xmlns:cwmp", "urn:dslforum-org:cwmp-1-0");

			// head

			/**
			 * serializer.startTag("", "cwmp:Inform");
			 * 
			 * serializer.endTag("", "cwmp:Inform");
			 */

			// body
			serializer.startTag("", "soap:Body");
			// inform
			serializer.startTag("", "cwmp:GetParameterValuesResponse");
			// deviceId
			serializer.startTag("", "ParameterList");
			serializer.attribute("", "soap:arrayType", "cwmp:ParameterValueStruct[2]");
			serializer.startTag("", "ParameterValueStruct");// ParameterValueStruct1
															// Username
			serializer.startTag("", "Name");
			serializer.text("Device.ManagementServer.Username");
			serializer.endTag("", "Name");
			serializer.startTag("", "Value");
			serializer.attribute("", "xsi:type", "xsd:string");
			// serializer.text("0814000015100000003");
			serializer.text("5936046");
			serializer.endTag("", "Value");
			serializer.endTag("", "ParameterValueStruct");

			serializer.startTag("", "ParameterValueStruct");// ParameterValueStruct2
															// password
			serializer.startTag("", "Name");
			serializer.text("Device.ManagementServer.Password");// password
			// serializer.text(info.get("Password"));
			serializer.endTag("", "Name");
			serializer.startTag("", "Value");
			serializer.attribute("", "xsi:type", "xsd:string");
			// serializer.text("123456");
			serializer.text("123");

			serializer.endTag("", "Value");
			serializer.endTag("", "ParameterValueStruct");
			serializer.endTag("", "ParameterList");
			serializer.endTag("", "cwmp:GetParameterValuesResponse");
			serializer.endTag("", "soap:Body");
			serializer.endTag("", "soap:Envelope");
			serializer.endDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.d("wzb", "wCwmpMessageFactory---" + writer.toString());
		// return null;
		return writer.toString();
	}

	private static String wcreateDownloadResponseXml(Map<String, String> info) {
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			// <?xml version=��1.0�� encoding=��UTF-8��>
			serializer.startDocument(null, null);// Write <?xml declaration with
													// encoding (if encoding not
													// null) and standalone flag
													// (if stan dalone not null)
													// This method can only be
													// called just after
													// setOutput.
			serializer.startTag("", "soap:Envelope");
			serializer.attribute("", "xmlns:soap", "http://schemas.xmlsoap.org/soap/envelope/");
			serializer.attribute("", "xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
			serializer.attribute("", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			serializer.attribute("", "xmlns:cwmp", "urn:dslforum-org:cwmp-1-0");

			// head

			/**
			 * serializer.startTag("", "cwmp:Inform");
			 * 
			 * serializer.endTag("", "cwmp:Inform");
			 */

			// body
			serializer.startTag("", "soap:Body");
			// inform
			serializer.startTag("", "cwmp:DownloadResponse");

			serializer.startTag("", "Status");
			serializer.attribute("", "xsi:type", "xsd:int");
			serializer.text("0");
			// serializer.text(info.get("UserName"));
			serializer.endTag("", "Status");

			serializer.endTag("", "cwmp:DownloadResponse");
			serializer.endTag("", "soap:Body");
			serializer.endTag("", "soap:Envelope");
			serializer.endDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.d("wzb", "CwmpMessageFactory---" + writer.toString());
		// return null;
		return writer.toString();
	}

}
