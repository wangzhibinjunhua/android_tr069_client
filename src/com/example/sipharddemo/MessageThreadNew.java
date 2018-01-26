package com.example.sipharddemo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.example.sipharddemo.MessageFactory.MessageType;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Xml;
import android.os.Message;

public class MessageThreadNew extends Thread {
	private static String IPAndPORT_TerSer = "183.62.12.22:8078";
	// private static String URL_TerSer =
	// "http://"+IPAndPORT_TerSer+"/ims_ws/TerminalServlet";
	private DataOutputStream out = null;
	private BufferedReader reader = null;
	private static HttpURLConnection connection = null;
	private static String URL_TerSer = "http://61.154.125.26:8082/acs";
	private String cookie;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			Log.e("wzb", "handler msg.id=" + msg.what);
			switch (msg.what) {
			case 1000:
				String info = (String) msg.obj;
				parse_soap(info);
				break;
			case 1001:
				Log.e("wzb", "recv acs inform response");
				sendToAcsMessage("");

				try {
					readMessageNew(connection.getInputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 1002:
				Log.e("wzb", "recv acs GetParameterNames request");
				sendToAcsMessage(MessageFactory.createXml(MessageType.GetParameterNamesResponse, null));
				try {
					readMessageNew(connection.getInputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 1003:
				Log.e("wzb", "recv acs reboot request");
				sendToAcsMessage(MessageFactory.createXml(MessageType.RebootResponse, null));
				try {
					readMessageNew(connection.getInputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 1004:
				Log.e("wzb", "recv acs getRPCMethods request");
				sendToAcsMessage(MessageFactory.createXml(MessageType.GetRPCMethodsReponse, null));
				try {
					readMessageNew(connection.getInputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 9999://test
				String info_test = (String) msg.obj;
				parse_soap_test(info_test);
				break;
			default:
				break;
			}

		};
	};

	public Handler getHandler() {
		return handler;
	}

	private void parse_soap(String info) {
		Log.e("wzb", "parse_soap info=" + info);
		XmlPullParser parser = Xml.newPullParser();
		InputStream is = new ByteArrayInputStream(info.getBytes());
		try {
			parser.setInput(is, "utf-8");
			int eventType;
			eventType = parser.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {
				Log.e("wzb", "parse_soap eventType=" + eventType);
				switch (eventType) {
				case XmlPullParser.START_TAG:
					// 读取到根标签，创建数组
					Log.e("wzb", "parse_soap START_TAG=" + parser.getName());
					if (parser.getName().equals("InformResponse")) {
						handler.sendEmptyMessage(1001);
					} else if (parser.getName().equals("GetParameterNames")) {
						handler.sendEmptyMessage(1002);
					} else if (parser.getName().equals("Reboot")) {
						handler.sendEmptyMessage(1003);
					} else if (parser.getName().equals("GetRPCMethods")) {
						handler.sendEmptyMessage(1004);
					}

					break;

				case XmlPullParser.END_TAG:
					// 读到student,说明一个对象对去完毕添加到list集合中，重置stu
					if (parser.getName().equals("student")) {

					}

					break;
				default:
					break;
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	private void parse_soap_test(String info) {
		Log.e("wzb", "parse_soap test info=" + info);
		XmlPullParser parser = Xml.newPullParser();
		InputStream is = new ByteArrayInputStream(info.getBytes());
		try {
			parser.setInput(is, "utf-8");
			int eventType;
			eventType = parser.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {
				Log.e("wzb", "parse_soap eventType=" + eventType);
				switch (eventType) {
				case XmlPullParser.START_TAG:
					// 读取到根标签，创建数组
					Log.e("wzb", "parse_soap START_TAG=" + parser.getName());
					if (parser.getName().equals("InformResponse")) {
						//handler.sendEmptyMessage(1001);
					} else if (parser.getName().equals("GetParameterNames")) {
						//handler.sendEmptyMessage(1002);
					} else if (parser.getName().equals("Reboot")) {
						//handler.sendEmptyMessage(1003);
					} else if (parser.getName().equals("GetRPCMethods")) {
						//handler.sendEmptyMessage(1004);
					}

					break;

				case XmlPullParser.END_TAG:
					// 读到student,说明一个对象对去完毕添加到list集合中，重置stu
					if (parser.getName().equals("student")) {

					}

					break;
				default:
					break;
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		super.run();
		Log.e("wzb", "111");

		sendInform();

	}

	/**
	 * 
	 * �������� : sendInform �������� : ��IMs�ն˹���ƽ̨��Inform
	 * Message,AS����ֵ��Ӧ�Ĵ��� ����˵���� ����ֵ�� void �޸ļ�¼�� ���ڣ�2012-12-11
	 * ����4:47:40 �޸��ˣ�Administrator ���� ��
	 *
	 */
	private void sendInform() {
		try {
			initSocket();
			// readMessage();
			readMessageNew(connection.getInputStream());
			//readMessageNewTest(connection.getInputStream());

		} catch (IOException e) {
			Log.d("wzb", "3333333");
			e.printStackTrace();
		} finally {
			try {
				if (null != out)
					out.close();
				if (null != reader)
					reader.close();
				if (connection != null) {
					connection.disconnect();
					connection = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * �������� : checkAnserType �������� : ���ݲ�ͬ�� Ӧ������ ����ͬ�Ĵ���
	 * ����˵����
	 * 
	 * @param in
	 *            ����ֵ�� void �޸ļ�¼�� ���ڣ�2012-12-11 ����4:46:42 �޸��ˣ�wjb
	 *            ���� ��
	 *
	 */
	private void checkAnserType(InputStream in) {

		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));// ���ñ���,������������
			String line = "";
			Log.e("wzb", "checkAnserType----receiver");
			while ((line = reader.readLine()) != null) {
				// ���ش�ӡ��
				Log.e("wzb", "checkAnserType rcv:" + line);
			}
			reader.close();
			Log.e("wzb", "----checkAnserType receiver end");
		} catch (IOException e) {
			Log.e("wzb", "checkAnserType IOException");

		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void initSocket() throws IOException {
		// Post�����url����get��ͬ���ǲ���Ҫ������
		Log.e("wzb", "initSocket()");
		//String custom_terser_url = android.os.SystemProperties.get("tr_server_url", URL_TerSer);
		String custom_terser_url=WApplication.ACS_URL;
		Log.e("wzb", "custom_terser_url=" + custom_terser_url);
		// URL postUrl = new URL(URL_TerSer);
		URL postUrl = new URL(custom_terser_url);
		// ������

		connection = (HttpURLConnection) postUrl.openConnection();
		connection.setDoOutput(true);
		// Read from the connection. Default is true.
		connection.setDoInput(true);
		// Set the post method. Default is GET
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Connection", "Keep-Alive");
		// Post cannot use caches
		// Post ������ʹ�û���
		connection.setUseCaches(true);
		// This method takes effects to
		// every instances of this class.
		// URLConnection.setFollowRedirects��static���������������е�URLConnection����
		// connection.setFollowRedirects(true);

		// This methods only
		// takes effacts to this
		// instance.
		// URLConnection.setInstanceFollowRedirects�ǳ�Ա�������������ڵ�ǰ����
		connection.setInstanceFollowRedirects(true);
		// Set the content type to urlencoded,
		// because we will write
		// some URL-encoded content to the
		// connection. Settings above must be set before connect!
		// ���ñ������ӵ�Content-type������Ϊapplication/x-www-form-urlencoded��
		// ��˼��������urlencoded�������form�������������ǿ��Կ������Ƕ���������ʹ��URLEncoder.encode
		// ���б���
		// connection.setRequestProperty("Content-Type",
		// "application/x-www-form-urlencoded");
		// ���ӣ���postUrl.openConnection()���˵����ñ���Ҫ��connect֮ǰ��ɣ�
		// Ҫע�����connection.getOutputStream�������Ľ���connect��
		connection.connect();
		out = new DataOutputStream(connection.getOutputStream());
		out.writeBytes(MessageFactory.createXml(MessageType.Inform, null));
		out.flush();
		out.close();
		Log.e("wzb", "cpe inform request");
		Log.e("wzb", "init connection=" + connection);

	}

	private void readMessageNewTest(InputStream in) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String line = "";
			Log.e("wzb", "readMessageNew----receiver");
			while (true) {
				if(reader!=null)line = reader.readLine();
				if (line != null) {
					Log.e("wzb", "readMessageNew rcv:" + line);
					Message message = handler.obtainMessage();
					message.what = 9999;
					message.obj = line;
					handler.sendMessage(message);
				}else{
					if(reader!=null){
						reader.close();
						reader=null;
					}
					reader=new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				}
			}

			// reader.close();

			// Log.e("wzb", "----readMessageNew receiver end");
		} catch (IOException e) {
			Log.e("wzb", "readMessageNew IOException");

		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void readMessageNew(InputStream in) {
		try {
			checkAnserNew(connection.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void checkAnserNew(InputStream in) {
		Log.e("wzb", "4444444444444");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String line = "";
			Log.e("wzb", "readMessageNew----receiver");
			while ((line = reader.readLine()) != null) {
				Log.e("wzb", "readMessageNew rcv:" + line);
				Message message = handler.obtainMessage();
				message.what = 1000;
				message.obj = line;
				handler.sendMessage(message);
			}

			reader.close();

			Log.e("wzb", "----readMessageNew receiver end");
		} catch (IOException e) {
			Log.e("wzb", "readMessageNew IOException");

		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void readMessage() throws IOException {
		String line;
		Log.e("wzb", "acs inform response reciever----");
		// reader = new BufferedReader(new InputStreamReader(
		// connection.getInputStream()));
		// while((line=reader.readLine()) !=null){
		// Log.e("wzb","recv:"+line);
		// }
		checkAnserType(connection.getInputStream());
		Log.e("wzb", "acs inform response edn----");
		cookie = connection.getHeaderField("Set-Cookie");
		Log.e("wzb", "--cookie:" + cookie);
		Log.e("wzb", "cpe send empty");
		if (sendToAcsMessage("") != null) {// �ڶ���
			Log.e("wzb", "acs GetParameterNames request reciever");
			checkAnserType(connection.getInputStream());
			Log.e("wzb", "acs GetParameterNames request reciever end");

			Log.e("wzb", "cpe send GetParameterNamesResponse");
			sendToAcsMessage(MessageFactory.createXml(MessageType.GetParameterNamesResponse, null));

			Log.e("wzb", "acs SetParameterValues request reciever");
			checkAnserType(connection.getInputStream());
			Log.e("wzb", "acs SetParameterValues request reciever end");

			// String urlDown =
			// XmlUtils.handlerDownLoadXml(sendMessage(MessageFactory.createXml(MessageType.DownloadResponse,null)));
			// Log.e("wzb","DownUrl:"+urlDown);
			// SipObject sipObject = XmlUtils.handlerSipConfigFile(
			// HttpRequest.requestByGet(urlDown));
			// saveSipObject(sipObject);
			// if(sipObject!=null){//���Ĵ�
			// sendMessage(MessageFactory.createXml(MessageType.GetParameterValuesResponse,null));
			// Log.e("wzb","--------------TR fourth send recevier over");
			// }
		}
	}

	public InputStream sendToAcsMessage(String data) {

		Log.e("wzb", "--sendMessage:" + data);
		String line;
		try {

			//String custom_terser_url = android.os.SystemProperties.get("tr_server_url", URL_TerSer);
			String custom_terser_url=WApplication.ACS_URL;
			connection = (HttpURLConnection) new URL(custom_terser_url).openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setUseCaches(true);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Cookie", cookie);
			connection.connect();
			out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(data);
			out.flush();
			out.close();

			return connection.getInputStream();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * �������� : saveSipObject �������� : ����sip�ʺ� ����˵����
	 * 
	 * @param sipObject
	 *            ����ֵ�� void �޸ļ�¼�� ���ڣ�2012-12-13 ����7:19:34
	 *            �޸��ˣ�Administrator ���� ��
	 *
	 */
	private void saveSipObject(SipObject sipObject) {
		// Intent register = new Intent("com.fanvil.
		// Settings.intent.action.REGISTER");
		// register.putExtra("STATUS", 1);
		// register.putExtra("INDEX", 2);
		// register.putExtra("SERVER", sipObject.getSip_Proxy());
		// register.putExtra("NUMBER", "21991");
		// register.putExtra("ACCOUNT", sipObject.getSip_Account_UserName());
		// register.putExtra("PASSWORD", sipObject.getSip_Account_Password());
		// register.putExtra("DOMAIN", sipObject.getSip_User_Domain());
		// register.putExtra("DISPLAYNAME", "YK");
		// Log.i("Hello",
		// "Register---ACCOUNT:"+sipObject.getSip_Account_UserName());
		// VisualPhoneApplication.getInstance().sendBroadcast(register);
		Intent register = new Intent("com.fanvil.Settings.intent.action.REGISTER");
		register.putExtra("STATUS", 1);
		register.putExtra("INDEX", 2);
		register.putExtra("SERVER", "61.154.9.84");
		register.putExtra("NUMBER", "6046");
		register.putExtra("ACCOUNT", "6046");
		register.putExtra("PASSWORD", "123");
		register.putExtra("DOMAIN", "61.154.9.84");
		register.putExtra("DISPLAYNAME", "YK");
		Log.e("wzb", "Register---ACCOUNT aaa:" + "6046@61.154.9.84");
		Log.e("wzb", "Register---Password:" + "123");
		VisualPhoneApplication.getInstance().sendBroadcast(register);

	}

}
