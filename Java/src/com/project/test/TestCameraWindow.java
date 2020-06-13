package com.project.test;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.project.CameraWindow;
import com.project.GameWindow;
import com.project.GameWindowLeft;
import com.project.GameWindowRight;
import com.project.WebViewWindow;


public class TestCameraWindow extends JFrame{
	
	CameraWindow cameraWindow = new CameraWindow();//对话框
	double width;
	double height;
	
	public static void main(String[] args) {
		TestCameraWindow testCameraWindow=new TestCameraWindow();
		testCameraWindow.startFrame();
	}
	
	public static void start() {
		TestCameraWindow testCameraWindow=new TestCameraWindow();
		testCameraWindow.startFrame();
	}
	
	public void startFrame(){
		/*
		 * this.setLayout(new BorderLayout()); 
		 * this.add(left,BorderLayout.WEST);
		 * this.add(right,BorderLayout.EAST);
		 */
		/*
		Toolkit tk = Toolkit.getDefaultToolkit();
		//this.setIconImage(new ImageIcon("图标路径").getImage());
		Dimension screensize = tk.getScreenSize();
		width = screensize.getWidth();
		height= screensize.getHeight();
		this.setBounds(0, 0, (int)width, (int)height);//设置成屏幕一样大
		*/
		this.setLayout(null);
		
		cameraWindow.setBounds(0, 0, 650, 530);
		
		this.add(cameraWindow);
	
		
		this.setSize(900, 530);//设置游戏窗体的宽高：800*600
		this.setVisible(true); //让窗体可见（默认是不可见）
		this.setLocationRelativeTo(null);
//		TestCameraWindow that = this;
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					Thread.sleep(3000);
//					WebViewWindow.start();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				// TODO Auto-generated method stub
//				that.setVisible(false);
//			}
//		}).start();
	}
}
