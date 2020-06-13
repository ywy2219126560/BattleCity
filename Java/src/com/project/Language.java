package com.project;

import api.BaiduApi;
import api.TuLing;

public class Language {
	TuLing tuling = new TuLing();
	BaiduApi baiduapi = new BaiduApi();
	public void change(String msg) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				baiduapi.getRecorderBytes(msg);
			}
		}).start();
	}
	
}
