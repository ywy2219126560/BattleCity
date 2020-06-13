package com.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.sarxos.webcam.Webcam;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;


public class GameWindow extends JFrame{
	static GameWindowLeft left = new GameWindowLeft();//
	GameWindowRight right = new GameWindowRight();
	public static double width;
	double height;
	
	public static void main(String[] args) {
		GameWindow myWindow=new GameWindow();
		myWindow.startFrame();
	}
	
	public static void start() {
		GameWindow myWindow=new GameWindow();
		myWindow.startFrame();
	}
	
	public void startFrame(){
		this.setIconImage(new ImageIcon("src/images/tankeimg.jpg").getImage());
		this.setTitle("智能以行——Java坦克游戏AI项目实战");
		this.setLayout(new BorderLayout());
		this.add(right, BorderLayout.EAST);
		this.add(left, BorderLayout.CENTER);
		

		Toolkit tk = Toolkit.getDefaultToolkit();
		// this.setIconImage(new ImageIcon("图标路径").getImage());
		Dimension screensize = tk.getScreenSize();
		width = screensize.getWidth();
		height = screensize.getHeight();
		// this.setBounds(0, 0, (int)width, (int)height);//设置成屏幕一样大

		// this.setLayout(new BorderLayout());

		// left.setBounds(0, 0, 700, 700);
		// right.setBounds(800, 0, 400, 700);
		// this.add(left);
		// this.add(right);
		left.startJPanel();
		right.startJPanel();
		right.setPreferredSize(new Dimension((int) (width * 0.2), 0));
		left.setPreferredSize(new Dimension((int) (width * 0.8), 0));
		// this.setSize(1000, 700);//设置游戏窗体的宽高：800*600
		this.setVisible(true); // 让窗体可见（默认是不可见）
		GameWindow.left.requestFocus();// 左边游戏窗体获得焦点
		this.addWindowListener(new MyAdapter());
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				// write you code here
				width = screensize.getWidth();
				height = screensize.getHeight();

				resetSize();

			}
		});
		this.setLocationRelativeTo(null);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setLocation(0, 0);
	}
	public void resetSize() {
		right.setPreferredSize(new Dimension((int) (width * 0.2), 0));
		left.setPreferredSize(new Dimension((int) (width * 0.8), 0));
	}
}
class MyAdapter extends WindowAdapter{
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("窗口关闭，游戏退出");
		System.exit(0);
	}
}
