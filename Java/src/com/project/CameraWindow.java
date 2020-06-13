package com.project;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;
import com.project.login.FaceMatch;
import com.project.test.TestCameraWindow;
import com.project.util.Base64Util;
import com.project.util.FileUtil;


public class CameraWindow extends JPanel implements ActionListener{
	public void startJPanel(CameraWindowStart cws, JButton jButton){
		
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		final JButton btnLogin=new JButton("识别登录");
		
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
 
		WebcamPanel panel = new WebcamPanel(webcam);
		panel.setFPSDisplayed(true);
		panel.setDisplayDebugInfo(true);
		panel.setImageSizeDisplayed(true);
		panel.setMirrored(true);
		
		this.setBackground(new Color(251,247,241));
		
		this.add(panel);
		this.setSize(580, 530);//设置游戏窗体的宽高：800*600
		//this.setVisible(true); //让窗体可见（默认是不可见）
		
		jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				btnLogin.setEnabled(false);  //设置按钮不可点击
				//实现拍照保存-------start
				String fileName = "D://" + System.currentTimeMillis();       //保存路径即图片名称（不用加后缀）
				WebcamUtils.capture(webcam, fileName, ImageUtils.FORMAT_PNG);
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run(){
						//JOptionPane.showMessageDialog(null, "是否确定人脸识别登录");
						System.out.println("图片地址："+fileName+".png");
						copyFile(fileName+".png","src/images/avatar/targetAvatar.png");//复制到指定文件夹
						
						byte[] bytes3=null;
						try {
							bytes3 = FileUtil.readFileByBytes("src/images/avatar/targetAvatar.png");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String image3 = Base64Util.encode(bytes3);
						
						String score=FaceMatch.match(image3);
						if(Double.parseDouble(score)>90) {
							webcam.close();
							cws.setVisible(false);
							System.out.println("登录成功！！！");
							WebViewWindow.start();
						} else {
							JOptionPane.showMessageDialog(null, "登录失败");
						}
						btnLogin.setEnabled(true);    //设置按钮可点击
						return;
					}
				});
			}
		});
		
	}
	
	
	static void copyFile(String src,String dec){
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		int temp = 0;
		try {
			fis = new FileInputStream(src);
			fos = new FileOutputStream(dec);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			while((temp=bis.read())!=-1){
				bos.write(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
            //增加处理流后，注意流的关闭顺序！“后开的先关闭！”
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("发送消息");
	}
}

