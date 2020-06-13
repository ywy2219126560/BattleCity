package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class TestTuling {
	// 图灵平台
	public static final String API_KEY = "974ae2b1c6894a1396a881106f4eb420";
	public static final String API_URL = "http://www.tuling123.com/openapi/api";
	public static final String USER_ID = "632080";

	public TestTuling() {
		super();
	}
	

	/**
	 * 获取可以传输的正确的json格式的请求字符串
	 * 
	 * @param reqMes 输入内容
	 * @return
	 */
	public String getReqMes(String reqMes) {
		// 请求json，里面包含reqType，perception，userInfo
		JSONObject reqJson = new JSONObject();
		// 输入类型:0-文本(默认)、1-图片、2-音频
		int reqType = 0;
		reqJson.put("reqType", reqType);

		// 输入信息,里面包含inputText，inputImage，selfInfo
		JSONObject perception = new JSONObject();
		// 输入的文本信息
		JSONObject inputText = new JSONObject();
		inputText.put("text", reqMes);
		perception.put("inputText", inputText);

		// 用户信息
		JSONObject userInfo = new JSONObject();
		userInfo.put("apiKey", API_KEY);
		userInfo.put("userId", USER_ID);

		reqJson.put("perception", perception);
		reqJson.put("userInfo", userInfo);
		return reqJson.toString();
	}

	/**
	 * 获取图灵返回的字符串信息
	 * 
	 * @param msg
	 * @return
	 */
	public String getMessage(String msg) {
		String str=null;
		try {
			str = setParameter(msg);
			System.out.println(str);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String status = "";
		String responseStr = "";
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			URL realUrl = new URL(API_URL);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) conn;
			// 设置请求属性
			httpUrlConnection.setRequestMethod("POST");// 提交模式
			httpUrlConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            // 发送POST请求必须设置如下两行
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setDoInput(true);
 
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(httpUrlConnection.getOutputStream());
			// 发送请求参数
			out.write(str);
			// flush输出流的缓冲
			out.flush();
			httpUrlConnection.connect();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				responseStr += line;
			}
			status = new Integer(httpUrlConnection.getResponseCode()).toString();
			System.out.println("status==============" + status);
			System.out.println("response==============" + responseStr);
			JSONObject jsonObject = new JSONObject(responseStr);
			String s =  jsonObject.get("text").toString();
			 return s;

 
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return "ok";

	}

	/**
	 * 获取完整的API地址
	 * 
	 * @param msg
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws JSONException 
	 */
	private String setParameter(String msg) throws JSONException, UnsupportedEncodingException {
//		try {
//			String u = API_URL + "?key=" + API_KEY + "&info=" + URLEncoder.encode(msg, "utf-8") + "&userid=" + USER_ID;
//			System.out.println(u);
//			return u;
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return null;
		// 请求json，里面包含reqType，perception，userInfo
		 
		// 用户信息
		JSONObject userInfo = new JSONObject();
		userInfo.put("key", API_KEY);
		userInfo.put("userid", USER_ID);
		userInfo.put("info", msg); 
		return userInfo.toString();

	}
}
