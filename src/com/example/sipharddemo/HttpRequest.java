package com.example.sipharddemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * 功能：http协议请求
 * 
 * @author Administrator
 * 
 */
public class HttpRequest {

	 
	/**
	 *    get 请求
	 * @param url
	 * @param byteNum
	 * @return  应答内容 String
	 * 
	 * 
	
	public static String requestByGet(String url, int byteNum) {
		HttpGet request = null;
		request = new HttpGet(url);
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();
					String answer = null;
					byte[] tmp = new byte[byteNum];
					while ((instream.read(tmp)) != -1) {
						answer = tmp.toString().trim();
						Log.i("requestByGet--", "收到信息：" + answer);
					}
					instream.close();
					instream = null;
					return answer;
				}

			} else {
				return new Integer(response.getStatusLine().getStatusCode())
						.toString();
			}

		} catch (Exception e) {

		}
		return "";
	}
	public static InputStream requestByGetUrl(String url,int connecTime,int readTimeout) throws ClientProtocolException, IOException {
		HttpGet request = null;
		request = new HttpGet(url);
			HttpClient httpClient = new DefaultHttpClient();
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT	, connecTime);
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT	, readTimeout);
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				return entity.getContent();

			} else {
				return null;
			}
	}
	
	public static HttpEntity requestEntityByGetUrl(String url, int connectTime, int readTimeout)	throws ClientProtocolException, IOException {
		
		HttpGet request = null;
		request = new HttpGet(url);
			HttpClient httpClient = new DefaultHttpClient();
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT	, connectTime);
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT	, readTimeout);
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				//cntLen = entity.getContentLength();
				//strType = entity.getContentType().getValue();
				
				return entity;
			} else {
				return null;
			}
	}

	 */
	
	/**
	 *   get请求
	 * @param httpurl
	 * @return InputStream  应答内容  ，下一步解析
	 * @throws IOException
	 */
	public static InputStream requestByGet(String httpurl) throws IOException {
		URL url = new URL(httpurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5*1000);
		conn.setReadTimeout(6*1000);
		return conn.getInputStream();
		
	}
	
	/**
	 *   get请求
	 * @param httpurl
	 * @return InputStream  应答内容  ，下一步解析
	 * @throws IOException
	 
	public static InputStream requestByPostAndTimeOut(String httpurl,String param,int timeout) throws IOException {
		URL url = new URL(httpurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);  
        conn.getOutputStream().write(param.getBytes());
        conn.getOutputStream().flush();
        conn.getOutputStream().close();
		conn.setConnectTimeout(4*1000);
		conn.setReadTimeout(timeout);
		return conn.getInputStream();
		
	}
	

	
	
	/**
	 * post 请求
	 * @param url
	 * @param params
	 * @return InputStream
	 * @throws IOException 
	 
	public static  InputStream requestByPost(String url, String envelope,int timeout) throws IOException{
	
			HttpPost post = null;
			post = new HttpPost(url);
			StringEntity entity2 = new StringEntity(envelope);
//			HttpEntity entity2 = new UrlEncodedFormEntity(params, HTTP.UTF_8);
			post.setEntity(entity2);
			
			HttpClient httpClient = new DefaultHttpClient();
			
			
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT	, timeout);
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT	, timeout);
		
			
			HttpResponse response;
			response = httpClient.execute(post);
			
			
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();
					return instream;
				}
			} else {
				return null;
			}
		
		
		return null;
	}

	*/
}
