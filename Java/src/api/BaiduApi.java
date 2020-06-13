package api;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class BaiduApi {
	// 百度语音平台
	public static final String APP_ID = "20282022";
	public static final String API_KEY2 = "2svtsoaGuGBB3oKgqf4OIWXg";
	public static final String SECRET_KEY = "oGtigNpLbTWbLOZpO9YjGslCDspyUpAA";
	
	//百度语音AipSpeech客户端对象
	static AipSpeech client = null;
	
	
	
	/**
	 * 获取百度语音AipSpeech客户端对象
	 * @return
	 */
	public static AipSpeech getInstance(){
        if (client==null){
            synchronized (AipSpeech.class){
                if (client==null) {
                    client = new AipSpeech(APP_ID, API_KEY2, SECRET_KEY);
                }
            }
        }
        return client;
    }
	
	/**
	 * 根据文字合成语音，并生成语音文件到本地
	 * @param msg
	 * @param outputPath
	 * @return
	 */
	public boolean getRecorderBytes(String msg){
		int maxLength = 1024;
        if (msg.getBytes().length >= maxLength) {
            return false;
        }
        // 初始化一个AipSpeech
        client = getInstance(); 
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);  
        // 调用接口
        TtsResponse res = client.synthesis(msg, "zh", 1, null);
        byte[] data = res.getData();
        org.json.JSONObject res1 = res.getResult();
        if (data != null) {
            try {
            	String outputPath = this.processRecorderPath(); //生成文件的输出路径
                Util.writeBytesToFileSystem(data, outputPath);//生成语音文件
                //播放语音
                playWav(outputPath);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        if (res1 != null) {
        	System.out.println(" result : " + res1.toString());
        }
        return false;

    }
	
	public String processRecorderPath() {
		long time = System.currentTimeMillis();
		File filePath = new File("D:/AudioFile");
    	String filename =filePath+"\\"+ time+".wav";
    	return filename;
	}
	
	/**
	 * 播放生成的音频文件
	 * @param filename
	 */
	private void playWav(String filename) {
		// TODO Auto-generated method stub
		try {
			BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename));
			Player player;
			try {
				player = new Player(buffer); 
				player.play();
			} catch (JavaLayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
