package com.project.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.project.util.Base64Util;
import com.project.util.FileUtil;
import com.project.util.GsonUtils;
import com.project.util.HttpUtil;


public class FaceMatch {
	public static String match(String image3) {
		// 请求url
	        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
	        try {
	        	
	            byte[] bytes1 = FileUtil.readFileByBytes("src/images/avatar/sourcesAvatar.png");
	            String image1 = Base64Util.encode(bytes1);

	            List<Map<String, Object>> images = new ArrayList<>();

	            Map<String, Object> map1 = new HashMap<>();
	            map1.put("image", image1);
	            map1.put("image_type", "BASE64");
	            map1.put("face_type", "LIVE");
	            map1.put("quality_control", "LOW");
	           // map1.put("liveness_control", "NORMAL");
	            
	            
	            Map<String, Object> map2 = new HashMap<>();
	            map2.put("image", image3);
	            map2.put("image_type", "BASE64");
	            map2.put("face_type", "LIVE");
	            map2.put("quality_control", "LOW");

	            images.add(map1);
	            images.add(map2);

	            String param = GsonUtils.toJson(images);
	            AuthService auth = new AuthService();
	            String accessToken = auth.getAuth();
	            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	            

	            String result = HttpUtil.post(url, accessToken, "application/json", param);
	            
	            JSONObject jsonObject = new JSONObject(result);
	            String score = jsonObject.get("result").toString();
	            
	            System.out.println("人脸得分==="+score);
	            System.out.println("类型==="+score.getClass().getTypeName());
	            if (!score.equals("null")) {
	            	return score.split(",")[0].split(":")[1];
	            } else {
	            	return "0";
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return "0";
	    }
	 
	 public static void main(String[] args) throws IOException {
		 byte[] bytes3 = FileUtil.readFileByBytes("src/images/avatar/sourcesAvatar.png");
		 String image3 = Base64Util.encode(bytes3);
		 String score=match(image3);
		 if(Double.parseDouble(score)>90) {
			 System.out.println("登录成功！！！");
		 }
	 }
	

}
