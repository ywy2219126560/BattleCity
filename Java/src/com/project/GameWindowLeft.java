package com.project;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.project.util.AudioUtils;

/**
 * 游戏的窗体界面
 * 
 * @author
 *
 */
public class GameWindowLeft extends JPanel {
	
	
	// 背景图片 (地图)
	static ImageIcon icon;
	static Image img;
	// 关卡按钮
	static JButton fastPassBtn = new JButton("LAST PASS");
	
	static JButton secondPassBtn = new JButton("SEACOND PASS");
	static JButton threePassBtn = new JButton("THREE PASS");

	
	// 坦克数组，多态的条件之一：向上转型
	static Tank[] tanks = new Tank[] { };
	// 子弹数组
	static Bullet[] bullets = new Bullet[100];
	// 爆炸数组
	static Bomb[] bombs = new Bomb[50];
	static int countBullets = 0;
	static int countBombs = 0;
	// 加入Score对象
	static Score score = new Score();
//	static Image brickWallImg = Toolkit.getDefaultToolkit().getImage("src/images/brickWall.jpg");
//	static Image ironWallImg  = Toolkit.getDefaultToolkit().getImage("src/images/ironWall.jpg");
	
	
	// 加入障碍物(type==1为砖墙，type==2为铁墙)，布局地形
	static Wall[] walls = new Wall[] { };
	
	public void startJPanel() {
		this.setSize(800, 700);// 设置游戏窗体的宽高：800*600
		// this.setVisible(true); //让窗体可见（默认是不可见）
		icon = new ImageIcon(getClass().getResource("/map/map.jpg"));
		img = icon.getImage();
		
		new PaintThread().start();// 启动画图（动画）线程
		this.addKeyListener(new KeyboardListener());
	}

	// 游戏是否结束
	static boolean gameover = false;
	// 游戏开始,用来控制关卡
	static boolean gamestart = false;

	/**
	 * 画图的方法
	 * 
	 * @param g 相当于画笔
	 */
	@Override
	public void paint(Graphics g) {
		
		

		super.paint(g);
		
		// 结束时在游戏界面上提示GameOver
		if (gameover) {
			/**
			 * 开启一个线程来对数据进行保存,保持游戏的继续运行
			 * 防止再次进入这个方法，导致程序因为JOptionPane.showInputDialog("请输入玩家名称：")卡死
			 * 这个方法会导致线程堵塞
			 */
			if (gamestart) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						AudioUtils.failAudio();
						String player  = JOptionPane.showInputDialog("请输入本次成绩名称：");
						if (player != null) {
							score.setPalyer(player);
							// 将玩家本次信息写入文件
							FileReaderWriter.FileWriter(score);
						}
						// 从文件中读取玩家历史数据并打印最高分和最高纪录者
						FileReaderWriter.FileReader();
					}
				}).start();
			}
			// 改变游戏状态,让他画图关卡按钮
			gamestart = false;
			
			
			g.drawImage(img, -10, -30,1100, 800, this);
			gameObjectDrawing(g);
			// 绘画背景
			
			g.setColor(Color.RED);
			g.setFont(new Font("宋体", Font.BOLD, 50)); // 改变字体大小);
			g.drawString("GameOver", 300, 300);
		}
		

		// 游戏未开始，显示:第一关，第二关，第三关，按钮
		if (!gamestart) {
			fastPassBtn.setBounds(300, 100, 200, 60);
			this.add(fastPassBtn);
			secondPassBtn.setBounds(300, 200, 200, 60);
			this.add(secondPassBtn);
			threePassBtn.setBounds(300, 300, 200, 60);
			this.add(threePassBtn);
		}
		// 游戏开始，清除按钮
		if (gamestart) {
			fastPassBtn.setVisible(false);
			secondPassBtn.setVisible(false);
			threePassBtn.setVisible(false);
			g.drawImage(img, -10, -30,1100, 800, this);
			// 绘画背景
			// 绘画关卡中的游戏对象
			gameObjectDrawing(g);
			// 把焦点从按钮转移到JPanel上，否则监控不到键盘
			this.requestFocus();
		}
		
		

		// 为第一关按钮注册点击监听
		fastPassBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!gamestart) {
					AudioUtils.startAudio();
				}
				// 设置第一关坦克
				tanks = new CustomsPass().tanks01;
				// 设置第一关地图
				walls = new CustomsPass().walls01;
				// 将游戏状态改为以开始
				gamestart = true;
			}
		});
		// 为第二关按钮注册点击监听
		secondPassBtn.addActionListener(new ActionListener() {
			// 设置关卡
			@Override
			public void actionPerformed(ActionEvent e) {
				tanks = new CustomsPass().tanks02;
				walls = new CustomsPass().walls02;
				gamestart = true;
			}
		});
		// 为第三关按钮注册点击监听
		threePassBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tanks = new CustomsPass().tanks03;
				walls = new CustomsPass().walls03;
				gamestart = true;
			}
		});

	}

	// 游戏关卡内的子弹，爆炸物，坦克的绘画
	public void gameObjectDrawing(Graphics g) {

		// 将坦克在窗体上画出来
		for (int i = 0; i < tanks.length; i++) {
			// 坦克元素不为空
			if (tanks[i] != null) {
				// 坦克没有死亡时画出
				if (tanks[i].life) {
					tanks[i].paint(g);
				} else {
					// 我方坦克死亡时
					if (tanks[i] instanceof MyTank) {
						gameover = true;
					}
					// 死亡的坦克清空
					tanks[i] = null;
				}
			}
		}
		// 遍历画出子弹
		for (int i = 0; i < bullets.length; i++) {
			if (bullets[i] != null) {
				// 子弹没有击中画出，击中了则从子弹数组中清除
				if (bullets[i].isLife()) {
					bullets[i].paint(g);
				} else {
					bullets[i] = null;
				}
			}
		}
		// 遍历画出爆炸
		for (int i = 0; i < bombs.length; i++) {
			if (bombs[i] != null) {
				// 爆炸动画播放一次后则清除
				if (bombs[i].isLife()) {
					bombs[i].paint(g);
				} else {
					bombs[i] = null;
				}
			}

		}
		// 遍历画出障碍物
		for (int i = 0; i < walls.length; i++) {
			if (walls[i] != null) {
				// 子弹没有击中画出，击中了则从子弹数组中清除
				if (walls[i].isLife()) {
					walls[i].paint(g);
				} else {
					walls[i] = null;
				}
			}
		}
	}

	// 绘制动画时需开启的线程（具体内容参考第十单元:多线程）
	class PaintThread extends Thread {
		@Override
		public void run() {
			while (true) {
				repaint();// 更新（重绘）画面
				try {
					Thread.sleep(40);// 每隔40毫秒更新（重绘）一次画面
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 加入监听器，程序可以监听键盘事件，即可实现键盘控制我方坦克
	class KeyboardListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			/**
			 * 取出tanks里面的最后一个元素，就是我们的坦克
			 */
			tanks[tanks.length-1].move(e);// 键盘按下，我方坦克移动，方向由KeyEvent控制（）
		}
	}

	// 发射子弹时将生成的子弹对象加入到子弹数组中
	public static void addBullet(Bullet b) {
		bullets[countBullets] = b;
		countBullets++;
		if (countBullets >= 99) {
			countBullets = 0;
		}
	}

	// 子弹碰撞后将生产爆炸对象并加入到爆炸数组中
	public static void addBomb(Bomb bomb) {
		bombs[countBombs] = bomb;
		countBombs++;
		if (countBombs >= 49) {
			countBombs = 0;
		}
	}

	// 用于绘图双缓冲
	private Image iBuffer = null;
	private Graphics gBuffer = null;

	// 重写update实现双缓冲
	public void update(final Graphics scr) {
		iBuffer = createImage(this.getSize().width, this.getSize().height);
		gBuffer = iBuffer.getGraphics();
		paint(gBuffer);
		gBuffer.dispose();
		scr.drawImage(iBuffer, 0, 0, this);
	}
}
